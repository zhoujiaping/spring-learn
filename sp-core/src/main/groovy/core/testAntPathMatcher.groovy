import org.springframework.util.AntPathMatcher

/**
 * ant风格的路径匹配器
 */
def matcher = new AntPathMatcher()
println matcher.match("/**","/a/b/c")
println matcher.matchStart("/**","/a/b/c")