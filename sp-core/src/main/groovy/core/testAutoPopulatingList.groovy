import org.springframework.util.AutoPopulatingList

/**
 * 自动增长的列表
 */
def list = new AutoPopulatingList({i->
    null
})
println list
println list.get(20)
println list.get(20)
list.set(20,"xxx world")
println list.get(20)
println list