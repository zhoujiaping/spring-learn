import org.springframework.http.client.support.ProxyFactoryBean

/**
 * http反向代理
 */
def fac = new ProxyFactoryBean()
fac.type = Proxy.Type.HTTP
fac.hostname = "www.baidu.com"
fac.port = 80
fac.afterPropertiesSet()
Proxy proxy = fac.object
def url = new URL("https://www.abc.com/index.html")
HttpURLConnection conn = url.openConnection(proxy)
println conn.responseCode
println conn.responseMessage

