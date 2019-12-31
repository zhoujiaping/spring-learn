import org.apache.jasper.runtime.ELContextImpl

import javax.el.BeanELResolver
import javax.el.ELContext
import javax.el.MapELResolver

/**
 * ElResolver
 * -ListElResolver
 * -StaticFieldElResolver
 * -ImplicitObjectElResolver
 * -StreamElResolver
 * -MapElResolver
 * and so on
 * 使用方式：
 * 有一个对象，我们要获取它的嵌套属性，
 * 想通过el表达式的方式。比如${map['a']}
 * 我们根据解析el表达式，根据访问方式（属性访问符-点号、还是索引访问-中括号、
 * 还是方法调用-圆括号），决定某一次访问使用那种el解析器。
 * 如果是点号，我们就用BeanElResolver，如果是[]，我们就用MapElResolver和ArrayElResolver，and so on。
 *
 */
def resolver = new BeanELResolver()
ELContext cxt = new ELContextImpl(new BeanELResolver())
Object property = "map"
def value = resolver.getValue(cxt, new Object() {
    String person = "test"
    def map = [
            a:'A'
    ]
    def list = ['a','b','c']
}, property)
println value
resolver = new MapELResolver()
value = resolver.getValue(cxt, value, "a")
println value