import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.Resource
import org.springframework.core.io.support.PathMatchingResourcePatternResolver

//PathMatchingResourcePatternResolver https://www.jianshu.com/p/c714d1d0f533
def resolver = new PathMatchingResourcePatternResolver()
//有坑！既然声明实现了该接口，为什么不能调用它的某些方法？获取流、获取文件都不支持
def resources = resolver.getResources("org/springframework/core/io/support/PathMatchingResourcePatternResolverTests.class")
resources.each {
    DefaultResourceLoader.ClassPathContextResource src->
    println src.readable
}
//读不了资源内容？？？

println "end"
//println resolver.getResource("classpath*:core/Dog.class").file.size()
