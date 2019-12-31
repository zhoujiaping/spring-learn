import org.springframework.util.ObjectUtils

import java.lang.annotation.RetentionPolicy


println ObjectUtils.isEmpty([])
println ObjectUtils.isEmpty(null)
println ObjectUtils.isEmpty('')
println ObjectUtils.isEmpty([:])
println ObjectUtils.isEmpty([] as String[])

println ObjectUtils.containsElement(['q34wrth','kitty'] as String[],'457tyu')

println ObjectUtils.containsConstant(RetentionPolicy.values(),'source',false)


println ObjectUtils.caseInsensitiveValueOf(RetentionPolicy.values(),'source')

println ObjectUtils.addObjectToArray([] as String[],"kitty")

println ObjectUtils.toObjectArray(["kitty"] as String[])



