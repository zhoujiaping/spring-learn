import org.springframework.util.SystemPropertyUtils

println System.properties
println SystemPropertyUtils.resolvePlaceholders('java虚拟机名字：${java.runtime.name}')