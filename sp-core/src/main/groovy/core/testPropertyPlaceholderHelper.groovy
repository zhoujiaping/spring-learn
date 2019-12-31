import org.springframework.util.PropertyPlaceholderHelper

def helper = new PropertyPlaceholderHelper('${','}')
def prop = new Properties()
prop.setProperty("name","kitty")
prop.setProperty("what",'${name}')
prop.setProperty("why",'${what}')
/**
 * 可以替换递归属性
 * 参考
 * https://www.jianshu.com/p/81a67516d7fc
 */
println helper.replacePlaceholders('diffEciqsCreditApplyReq ${name}! ${what}，${why}',prop)