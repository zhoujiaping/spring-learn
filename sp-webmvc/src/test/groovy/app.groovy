import webmvc.DemoService
import org.springframework.context.support.GenericGroovyApplicationContext
import org.springframework.web.context.support.GroovyWebApplicationContext

def app = new GenericGroovyApplicationContext("classpath:applicationContext.groovy")
DemoService service = app.getBean("demoService")
println service.someProp
