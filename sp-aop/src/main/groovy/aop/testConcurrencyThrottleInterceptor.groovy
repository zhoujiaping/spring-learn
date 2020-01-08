import org.springframework.aop.framework.AdvisedSupport
import org.springframework.aop.framework.AdvisorChainFactory
import org.springframework.aop.framework.CglibAopProxy
import org.springframework.aop.framework.DefaultAdvisorChainFactory
import org.springframework.aop.framework.DefaultAopProxyFactory
import org.springframework.aop.interceptor.ConcurrencyThrottleInterceptor

class Person {
    def sleep() {
        println "sleeping..."
    }

    def eat(food) {
        println "eating $food"
    }

    def static hi() {
        println 'hi'
    }
}

/**
 * 只能代理已经有的对象。
 *
 *
 */
def config = new AdvisedSupport()
config.target = new Person()
config.addAdvice(new ConcurrencyThrottleInterceptor())
def fac = new DefaultAopProxyFactory()
def aop = fac.createAopProxy(config)
def pxy = aop.proxy
pxy.eat('meet')


