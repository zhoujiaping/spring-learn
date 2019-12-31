import org.springframework.core.convert.converter.ConvertingComparator
import org.springframework.core.convert.support.NumberToCharacterConverter
import org.springframework.core.convert.support.StringToNumberConverterFactory

def cvtFac = new StringToNumberConverterFactory()
def cvt = cvtFac.getConverter(Integer)
def cmp = new ConvertingComparator(cvt)
//内部会将两个对象都用converter转一下，然后compare
println cmp.compare("123","6")
