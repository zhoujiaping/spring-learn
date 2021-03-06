package webmvc

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationContext
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.stereotype.Controller
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter

import javax.annotation.PostConstruct

/**
 * 为什么用
 */
@Controller
class InitController {
    @Autowired
    private ApplicationContext app
    //@Value("#{fastJsonHttpMessageConverter}")
    //HttpMessageConverter converter
    @Autowired
    RequestMappingHandlerAdapter handlerAdapter
    @PostConstruct
    void init(){
        //println converter
        println app.getBeanDefinitionNames()
        println handlerAdapter.messageConverters
        //RequestMappingHandlerAdapter handlerAdapter = app.getBean(RequestMappingHandlerAdapter)
        //handlerAdapter.messageConverters << app.getBean('fastJsonHttpMessageConverter')
        //handlerAdapter.messageConverters << converter
    }
}
