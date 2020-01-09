package web;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.net.ssl.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * 为什么用匹配后缀的方式？
 * 前缀方式，比如匹配/proxy和/proxy/**的就走代理。但是如果url是https://www.baidu.com/index.html这样的会有问题。
 * 一般web.xml中会配置*.html走default的servlet。这样会同时匹配两个规则，而*.html的规则比/proxy/**的优先级高，
 * 会造成404问题。而通过后缀匹配，可以解决这样的问题。
 *
 * eg：
 * 代理服务器地址为http://localhost:8989/sp-webmvc
 * 如果想请求https://www.baidu.com/index.html?v=1
 * 那客户端发送请求的地址应该为http://localhost:8989/sp-webmvc/index.html.proxy?v=1，并且添加头X-TARGET-HOST:https://www.baidu.com
 *
 * 当然，还有其他方案。比如代理服务的路径固定为/proxy，目标url通过头信息完整传递。
 * 比如 http://localhost:8989/sp-webmvc/proxy，并且添加头X-TARGET-HOST:https://www.baidu.com/index.html?v=1
 */
@Controller
@RequestMapping({"/**/*.proxy"})
public class HttpProxy {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final Set<String> plains = Sets.newHashSet("application/json", "application/xml", "text/plain",
			"application/x-www-form-urlencoded");
	private CloseableHttpClient httpClient;
	private static final String TARGET_HOST_HEADER = "X-TARGET-HOST";
	private static final Map<String,String> hostMapping = Maps.newHashMap();
	@PostConstruct
	public void init() throws Exception{
		//有些https证书和域名不匹配。可以添加映射。key为域名，value为证书路径。
		//可以在浏览器查看证书路径
		hostMapping.put("baidu.com", "*baidu.com");
		SSLContext sslcontext = createIgnoreVerifySSL();
		HostnameVerifier hostnameVerifier = createHostnameVerifier();
		ConnectionKeepAliveStrategy keepAliveStrategy = createKeepAliveStrategy();
		SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1.2","TLSv1" }, null,
				hostnameVerifier);
		// 设置协议http和https对应的处理socket链接工厂的对象
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", PlainConnectionSocketFactory.INSTANCE)
				.register("https", sf).build();
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		RequestConfig defaultRequestConfig = RequestConfig.custom()
				.setSocketTimeout(2000)
				.setConnectTimeout(2000)
				.setConnectionRequestTimeout(2000)
				.build();
		httpClient = HttpClients.custom().setConnectionManager(connManager)
		.setDefaultRequestConfig(defaultRequestConfig)
		.setMaxConnPerRoute(20)
		.setMaxConnTotal(100)
		.setKeepAliveStrategy(keepAliveStrategy)
		.build();
		//TODO 处理连接泄露
	}
	private ConnectionKeepAliveStrategy createKeepAliveStrategy() {
		DefaultConnectionKeepAliveStrategy strategy = DefaultConnectionKeepAliveStrategy.INSTANCE;
		return strategy;
	}
	@RequestMapping
	public void proxy(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			doProxy(request, response);
		} catch (Exception e) {
			logger.error("执行代理异常",e);
			throw e;
		}
	}

	private void doProxy(HttpServletRequest request, HttpServletResponse response)
			throws MalformedURLException, IOException, ClientProtocolException {
		String method = request.getMethod().toUpperCase();
		String url = request.getRequestURL().toString();
		String protocol = request.getProtocol();
		URL origURL;
		String queryString = request.getQueryString();
		if(queryString!=null){
			origURL = new URL(url + "?" + queryString);
		}else{
			origURL = new URL(url);
		}
		HttpUriRequest req = null;
		List<Header> headerList = new ArrayList<>();
		StringBuilder reqHeaderString = new StringBuilder();
		Enumeration<?> headerNames = request.getHeaderNames();
		boolean isPlainReq = false;
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement().toString();
			Enumeration<?> headers = request.getHeaders(headerName);
			while (headers.hasMoreElements()) {
				String headerValue = headers.nextElement().toString();
				Header header = new BasicHeader(headerName, headerValue);
				reqHeaderString.append(headerName).append(" :").append(headerValue).append("\n");
				if (headerName.equalsIgnoreCase("Content-Type")) {
					String[] values = headerValue.toLowerCase().split(";");
					for(int i=0;i<values.length;i++){
						if(plains.contains(values[i].trim())){
							isPlainReq = true;
							break;
						}
					}
				}
				if(!headerName.equalsIgnoreCase("connection")){
					headerList.add(header);
				}
			}
		}
		logger.info("请求行：{} {} {}", method, url, protocol);
		logger.info("请求头：{}", reqHeaderString);
		//将path部分所有连续的路径分隔符、windows风格的路径分隔符 替换为单个路径分隔符/
		String prefix = request.getContextPath();
		String targetHost  = request.getHeader(TARGET_HOST_HEADER);
		String path = origURL.getPath();
		path = path.substring(prefix.length(),path.length()-".proxy".length());
		String targetUrl;
		if(origURL.getQuery() != null){
			targetUrl = targetHost + path + "?" +origURL.getQuery();
		}else{
			targetUrl = targetHost + path;
		}

		URL targetURL = new URL(targetUrl);
		switch (method) {
		case "GET":
			req = new HttpGet(targetUrl);
			req.setHeaders(headerList.toArray(new Header[0]));
			req.removeHeaders(HTTP.TRANSFER_ENCODING);// 不允许手动设置该响应头
			req.removeHeaders(HTTP.DATE_HEADER);
			req.removeHeaders(HTTP.CONTENT_LEN);// 不允许手动设置该响应头
			req.removeHeaders(HTTP.SERVER_HEADER);
			req.setHeader("host", targetURL.getHost()+":"+targetURL.getPort());
			break;
		case "POST":
			HttpPost httpPost = new HttpPost(targetUrl);
			req = httpPost;
			req.setHeaders(headerList.toArray(new Header[0]));
			req.removeHeaders(HTTP.TRANSFER_ENCODING);// 不允许手动设置该响应头
			req.removeHeaders(HTTP.DATE_HEADER);
			req.removeHeaders(HTTP.CONTENT_LEN);// 不允许手动设置该响应头
			req.removeHeaders(HTTP.SERVER_HEADER);
			req.setHeader("host", targetURL.getHost()+":"+targetURL.getPort());
			try(InputStream in = request.getInputStream();){
				HttpEntity entity = null;
				if(isPlainReq){
					String body = StreamUtils.copyToString(in, StandardCharsets.UTF_8);
					logger.info("请求体：{}",body);
					entity = new StringEntity(body, "utf-8");
				}else{
					entity = new InputStreamEntity(in);
				}
				httpPost.setEntity(entity);
			}
			break;
		default:
			throw new RuntimeException("暂时不支持该http方法");
		}

		CloseableHttpResponse resp = httpClient.execute(req);
		//
		resp.removeHeaders(HTTP.TRANSFER_ENCODING);// 不允许手动设置该响应头
		resp.removeHeaders(HTTP.DATE_HEADER);
		resp.removeHeaders(HTTP.CONTENT_LEN);// 不允许手动设置该响应头
		resp.removeHeaders(HTTP.SERVER_HEADER);
		int statusCode = resp.getStatusLine().getStatusCode();
		if(statusCode!= HttpStatus.SC_OK){
			logger.error("http代理响应码：{}",statusCode);
		}
		response.setStatus(statusCode);
		Header[] respHeaders = resp.getAllHeaders();
		StringBuilder respHeaderString = new StringBuilder();
		boolean isPlainResp = false;
		for (Header header : respHeaders) {
			response.addHeader(header.getName(), header.getValue());
			respHeaderString.append(header.getName()).append(" :").append(header.getValue()).append("\n");
			if (header.getName().equalsIgnoreCase("Content-Type")) {
				String[] values = header.getValue().toLowerCase().split(";");
				for(int i=0;i<values.length;i++){
					if(plains.contains(values[i].trim())){
						isPlainResp = true;
						break;
					}
				}
			}
		}
		logger.info("状态行：{}", resp.getStatusLine());
		logger.info("响应头：{}", respHeaderString);
		HttpEntity entity = resp.getEntity();
		if (isPlainResp) {
			PrintWriter pw = response.getWriter();
			String body = EntityUtils.toString(entity, "utf-8");
			logger.info("响应体：{}", body);
			EntityUtils.consume(entity);
			pw.write(body);
			pw.flush();
		} else {
			OutputStream out = response.getOutputStream();
			entity.writeTo(out);
		}
	}
	private HostnameVerifier createHostnameVerifier() {
		return new HostnameVerifier() {
			@Override
			public boolean verify(String hostname, SSLSession session) {
				String host = hostMapping.get(hostname);
				if(host == null){
					return SSLConnectionSocketFactory.getDefaultHostnameVerifier().verify(hostname, session);
				}
				boolean res = SSLConnectionSocketFactory.getDefaultHostnameVerifier().verify(host, session);
				if(res){
					return true;
				}
				return SSLConnectionSocketFactory.getDefaultHostnameVerifier().verify(hostname, session);
			}
		};
	}

	private static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
		//SSLContext sc = SSLContext.getInstance("SSLv3");
		SSLContext sc = SSLContext.getInstance("TLS");
		// 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
		X509TrustManager trustManager = new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate,
					String paramString) throws CertificateException {
			}
			@Override
			public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate,
					String paramString) throws CertificateException {
			}
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		sc.init(null, new TrustManager[] { trustManager }, null);
		return sc;
	}
}
