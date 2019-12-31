import org.springframework.core.io.DefaultResourceLoader

def loader = new DefaultResourceLoader()
loader.getResource("classpath:").getFile().eachFileMatch({
    String name->
        name.endsWith('.groovy')
}){
    file->
        println file
}
println 'end'