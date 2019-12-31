package beans

import org.apache.jasper.runtime.ELContextImpl
import org.springframework.beans.factory.access.el.SimpleSpringBeanELResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.context.support.WebApplicationContextUtils
import org.springframework.web.servlet.HandlerAdapter
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter

import javax.annotation.PostConstruct
import javax.annotation.Resource
import javax.el.StandardELContext

@Controller
@RequestMapping
class EchoController {
    @Autowired
    ApplicationContext app
    String desc = 'this is echo controller'

    @RequestMapping("/echo")
    @ResponseBody
    def echo(){
        ['hello','你好']
    }
    @RequestMapping("/echo2")
    @ResponseBody
    def echo2(){
        ['hello','你好'].join(',')
    }
    @RequestMapping("/echo-jsp")
    def echoJsp(){
        'hello'
    }
    @RequestMapping("/test-el")
    @ResponseBody
    def testEl(){
        def resolver = new SimpleSpringBeanELResolver(app)
        def elContext = new ELContextImpl(resolver)
        def value = resolver.getValue(elContext,null,'echoController')
        println value
        value
    }
}
