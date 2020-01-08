import org.springframework.util.ResourceUtils

ResourceUtils.getFile("classpath:").eachFile {
    println it
}

def url = new URL("file:///D:/git-repo/mybatis-mapper/target/mybatis-mapper.jar!/META-INF/MANIFEST.MF")
//如何读取jar包里面某一个资源的内容？
def url2 = ResourceUtils.extractJarFileURL(url)
//file:///D:/git-repo/mybatis-mapper/target/mybatis-mapper.jar
println new File(url2.file).size()

