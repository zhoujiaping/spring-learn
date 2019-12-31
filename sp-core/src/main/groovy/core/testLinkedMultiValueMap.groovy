import org.springframework.util.LinkedMultiValueMap

/**
 * 一个key可以有多个值的 LinkedHashMap
 */
def map = new LinkedMultiValueMap()
map.add("diffEciqsCreditApplyReq","kitty")
map.add("diffEciqsCreditApplyReq","KITTY")
println map