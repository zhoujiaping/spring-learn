import org.springframework.util.PatternMatchUtils

println PatternMatchUtils.simpleMatch('abc*d','abccd')
println PatternMatchUtils.simpleMatch('abc*d','abcd')
println PatternMatchUtils.simpleMatch('abc*d','abcccd')
println PatternMatchUtils.simpleMatch('*cd*','abccd')
println PatternMatchUtils.simpleMatch('abc*d','abd')