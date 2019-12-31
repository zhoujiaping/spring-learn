package beans

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.parser.ParserConfig
import com.alibaba.fastjson.parser.deserializer.JavaObjectDeserializer
import groovy.transform.ToString
import org.springframework.beans.SimpleTypeConverter
import org.springframework.core.convert.support.DefaultConversionService

import java.nio.file.Path
def converter  = new SimpleTypeConverter()
assert !converter.conversionService
converter.conversionService = new DefaultConversionService()
assert converter.convertIfNecessary("1",Long) in Long

assert converter.convertIfNecessary("1",BigDecimal) in BigDecimal

assert converter.convertIfNecessary(3,String) in String

assert converter.convertIfNecessary("/home", Path) in Path
@ToString
class Person0{
    String phoneNo
}
@ToString
class Person1{
    Long phoneNo
}
assert converter.convertIfNecessary(3.14,String) in String
def p0 = new Person0(phoneNo:18820077637)

ParserConfig config = new ParserConfig()
def deserializer = new JavaObjectDeserializer()
//config.putDeserializer(Person1, deserializer)
def res = JSON.parseObject("""
{"phoneNo":""}
""",Person0,config)
println res