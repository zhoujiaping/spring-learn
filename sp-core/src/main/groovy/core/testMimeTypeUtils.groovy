import org.springframework.util.MimeTypeUtils

println MimeTypeUtils.APPLICATION_JSON
println MimeTypeUtils.parseMimeType('application/json')
println MimeTypeUtils.parseMimeType('application/json; charset=utf-8')
println MimeTypeUtils.parseMimeType(' Application/json; charset=utf-8')
