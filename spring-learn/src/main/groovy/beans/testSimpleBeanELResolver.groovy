import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.access.el.SimpleSpringBeanELResolver

import javax.el.ELContext

class Person{
    String name
    int age
    String[] nick
    List hobby
}

BeanFactory bf
def resolver = new SimpleSpringBeanELResolver(bf)
ELContext elContext
Object base
Object property
def value = resolver.getValue(elContext,base,property)
println value