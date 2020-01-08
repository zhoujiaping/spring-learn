import org.springframework.http.ResponseEntity
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.web.client.AsyncRestTemplate

import java.nio.charset.StandardCharsets

def rest = new AsyncRestTemplate()
// 删除所有的 StringHttpMessageConverter
rest.messageConverters = rest.messageConverters.findAll{
    !(it in StringHttpMessageConverter)
}

// 添加 UTF-8 的解析器
rest.messageConverters << new StringHttpMessageConverter(StandardCharsets.UTF_8)

def future = rest.getForEntity("https://www.baidu.com",String)
ResponseEntity resp = future.get()
println resp.body
println resp.headers.getFirst("content-type")