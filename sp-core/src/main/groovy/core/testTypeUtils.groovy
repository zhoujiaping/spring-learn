import org.springframework.util.TypeUtils

println TypeUtils.isAssignable(new Object(){}.class,Object)

println TypeUtils.isAssignable(Object,new Object(){}.class)