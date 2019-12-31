import org.springframework.beans.BeanUtils
import org.springframework.util.ClassUtils

println BeanUtils.isSimpleProperty(int)
println BeanUtils.isSimpleProperty(int[])
println BeanUtils.isSimpleValueType(Integer)
//true

println BeanUtils.isSimpleProperty(int[][])
println BeanUtils.isSimpleValueType(Integer[])
//false

ClassUtils.isPrimitiveOrWrapper(Integer)
