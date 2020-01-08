package webmvc

import org.springframework.context.ApplicationEvent
import org.springframework.context.event.SmartApplicationListener
import org.springframework.stereotype.Component
import org.springframework.web.context.WebApplicationContext

import javax.annotation.PostConstruct

/**
 * 用GroovyWebApplicationContext做上下文，和xml同样的配置，就是不创建@Component注解声明的组件
 */
@Component
class AppListener implements SmartApplicationListener{
    @PostConstruct
    void init(){
        println this.class.simpleName
    }
    @Override
    boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        //aClass == GroovyWebApplicationContext
        WebApplicationContext.isAssignableFrom(aClass)
    }

    @Override
    boolean supportsSourceType(Class<?> aClass) {
        return false
    }

    @Override
    void onApplicationEvent(ApplicationEvent applicationEvent) {
        println applicationEvent
    }

    @Override
    int getOrder() {
        return 0
    }
}
