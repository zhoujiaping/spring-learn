import org.springframework.core.io.DefaultResourceLoader
import org.springframework.util.ResourceUtils

def loader = new DefaultResourceLoader()
loader.getResource("classpath:").getFile().eachFileMatch({
    String name->
        name.endsWith('.groovy')
}){
    file->
        println file
}
println 'end'

/**
 * 从jar包中读取某个‘文件’的内容，有两种方式
 * 一种是基于URLClassLoader的getResourceAsStream方法。
 * 一种是基于URL的jar协议，比如URL url = new URL("jar:file:///xxxxxx/xxx/xxx/abc.jar/aa/bb/cc.txt")
 * 然后url.openStream()就可以打开流获取内容了。
 *
 * 推荐前者，因为前者不需要调用者知道用file还是jar协议,类加载器会帮我们处理好。
 */
def url = "file:///D:/git-repo/mybatis-mapper/target/mybatis-mapper.jar!/META-INF/MANIFEST.MF"
//url = "classpath:core/A.class"//可以获取内容
url = "classpath:org/slf4j/MDC.class"//可以获取内容
def src = loader.getResource(url)
println src.readable
println src.inputStream.bytes.length

def is = this.class.classLoader.getResourceAsStream("org/slf4j/MDC.class")
println is.bytes.length





