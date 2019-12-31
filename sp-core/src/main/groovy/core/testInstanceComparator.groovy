import org.springframework.util.comparator.InstanceComparator

/**
 * 根据实例的类型顺序，比较顺序
 */
def cmp = new InstanceComparator(Integer,Long,String,Float,Double)
println cmp.compare("",3.14f)
println cmp.compare(3.14f,"")