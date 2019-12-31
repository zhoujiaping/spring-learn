import org.springframework.util.AlternativeJdkIdGenerator
import org.springframework.util.SimpleIdGenerator

/**
 * AlternativeJdkIdGenerator功能和UUID.randomUUID()一样，性能更好
 */
println new AlternativeJdkIdGenerator().generateId()
println UUID.randomUUID()
def idGenerator = new SimpleIdGenerator()
println idGenerator.generateId()
println idGenerator.generateId()
println idGenerator.generateId()