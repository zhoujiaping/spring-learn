import org.apache.jasper.runtime.ELContextImpl
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.access.el.SimpleSpringBeanELResolver
import org.springframework.beans.factory.support.StaticListableBeanFactory

import javax.el.BeanELResolver
import javax.el.ELContext

BeanFactory bf = new StaticListableBeanFactory([
        person:new Object(){
            String name = "zhou"
        }
])
def resolver = new SimpleSpringBeanELResolver(bf)
ELContext elContext = new ELContextImpl(new BeanELResolver())
Object property = "person"
def value = resolver.getValue(elContext,null,property)
println value