import com.alibaba.fastjson.JSON
import org.springframework.core.convert.TypeDescriptor
import org.springframework.core.convert.support.DefaultConversionService
import org.springframework.core.convert.support.IdToEntityConverter
class Person{
    String name
    private static def persons = [new Person([name:'jack']),new Person([name:'john'])]
    static Person findPerson(int id){
        persons[id%2]
    }
    String toString(){
        JSON.toJSONString(this)
    }
}
def cvt = new IdToEntityConverter(new DefaultConversionService())
println '*'*20
//将 arg[1]类型的arg[0]，转换成findPerson的入参类型，传给arg[2]类型（Person）的静态方法find[arg[2]](findPerson),得到一个arg[2]类型的(Person)对象。
println cvt.convert("1",
        TypeDescriptor.valueOf(String),
        TypeDescriptor.valueOf(Person))
