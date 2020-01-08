import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import org.springframework.web.servlet.view.InternalResourceViewResolver

beans {
    xmlns mvc: 'http://www.springframework.org/schema/mvc'
    xmlns context: 'http://www.springframework.org/schema/context'

    fastJsonHttpMessageConverter(FastJsonHttpMessageConverter){
        supportedMediaTypes=['text/html;charset=UTF-8','application/json','application/xml;charset=UTF-8']
        features = []
        defaultCharset = 'utf-8'
    }
    /*
    handlerMapping(RequestMappingHandlerMapping){

    }
    这里是xml写法，没办法实现 messageConverters<<fastJsonHttpMessageConverter
    所以在InitController中实现了
    handlerAdapter(RequestMappingHandlerAdapter){
        messageConverters = fastJsonHttpMessageConverter
    }*/
    //annotation-driven实际上是配置了RequestMappingHandlerMapping和RequestMappingHandlerAdapter
    mvc.'annotation-driven'()

    context.'component-scan'('base-package':'mvc','use-default-filters':false){
        context.'include-filter'(type:'annotation',expression:'org.springframework.stereotype.Controller')
    }
    internalResourceViewResolver(InternalResourceViewResolver){
        prefix = '/WEB-INF/jsp/'
        suffix = '.jsp'
    }
    mvc.resources(location:'/static/',mapping:'/static/**')
/*    internalResourceViewResolver(InternalResourceViewResolver){
        prefix = '/WEB-INF/jsp/'
        suffix = '.jsp'
    }*/
}