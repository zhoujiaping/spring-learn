package web

import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients

def client = HttpClients.createDefault()
def method = new HttpGet("http://localhost:8989/sp-webmvc/proxy")
method.addHeader("X-TARGET-URL","https://www.baidu.com/index.html?v=1")
method.addHeader("Content-Type","text/html;charset=utf-8")
CloseableHttpResponse resp = client.execute(method)
println resp.statusLine
resp.allHeaders.each{
    println it
}
println resp.entity.content.getText("utf-8")
