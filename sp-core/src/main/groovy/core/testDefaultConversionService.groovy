import org.springframework.core.convert.support.DefaultConversionService
import org.springframework.core.convert.support.StringToNumberConverterFactory

def cs = new DefaultConversionService()
def r = cs.convert([1,2,3],Integer[])

r = cs.convert("1",Integer)

r = cs.convert("",Integer)

println r

def cvtFac = new StringToNumberConverterFactory()
def cvt = cvtFac.getConverter(Integer)
println cvt.convert("314")
println cvt.convert("")
