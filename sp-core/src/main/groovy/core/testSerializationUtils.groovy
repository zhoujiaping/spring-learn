import org.springframework.util.SerializationUtils

def data = SerializationUtils.serialize([1,2,3])
println SerializationUtils.deserialize(data)
