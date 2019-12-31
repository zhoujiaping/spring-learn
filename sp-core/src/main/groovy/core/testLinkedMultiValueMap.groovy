import org.springframework.util.LinkedMultiValueMap

/**
 * 一个key可以有多个值的 LinkedHashMap
 */
def map = new LinkedMultiValueMap()
map.add("xxx","kitty")
map.add("xxx","KITTY")
println map