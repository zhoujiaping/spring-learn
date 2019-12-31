import com.sun.xml.internal.ws.api.pipe.ContentType
import org.springframework.objenesis.SpringObjenesis

import javax.annotation.Resource
import java.lang.annotation.RetentionPolicy
import java.lang.reflect.InvocationHandler

class Dog{
    String name
    Dog(String name){
        println "Dog($name)"
        this.name = name
    }
}
//创建对象，但是不调用构造器。所以，即使没有空参的构造器，没有public构造器都没关系
def so = new SpringObjenesis()
def dog = so.newInstance(Dog)
println dog.class
//没法创建 枚举、接口、抽象类 和 注解。哈哈
