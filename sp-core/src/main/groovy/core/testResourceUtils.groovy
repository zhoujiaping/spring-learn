import org.springframework.util.ResourceUtils

ResourceUtils.getFile("classpath:").eachFile {
    println it
}