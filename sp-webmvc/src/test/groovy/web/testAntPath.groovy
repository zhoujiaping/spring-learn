import org.springframework.util.AntPathMatcher

/**
 * 和web.xml中的路径匹配不是一回事。。。晕
 */
def matcher = new AntPathMatcher()
println matcher.match("/proxy/**/*","/proxy/static/html/index.html")
println matcher.match("/proxy/**/*","/proxy/static/index.html")
println matcher.match("/proxy/**/*","/proxy/index.html")
println matcher.match("/proxy/**","/proxy/index.html")
println matcher.match("/proxy/*","/proxy/index.html")