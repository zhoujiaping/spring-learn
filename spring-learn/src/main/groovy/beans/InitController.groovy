package beans

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationContext
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.stereotype.Controller
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter

import javax.annotation.PostConstruct
@Controller
class InitController {
    @Autowired
    private ApplicationContext app
    @Value("#{root.fastJsonHttpMessageConverter}")
    HttpMessageConverter converter
    @PostConstruct
    void init(){
        println converter
        RequestMappingHandlerAdapter handlerAdapter = app.getBean(RequestMappingHandlerAdapter)
        handlerAdapter.messageConverters<<app.getBean('fastJsonHttpMessageConverter')
    }
}
