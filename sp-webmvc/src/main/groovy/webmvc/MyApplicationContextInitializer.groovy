package webmvc

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext

/**
 * 应用上下文初始化器
 * 貌似没啥用，写个InitController就行了
 */
class MyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private boolean initialized = false
    //spring两个上下文会各调用一次
    @Override
    void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        if(!initialized){
            println configurableApplicationContext
            initialized = true
        }
    }
}
