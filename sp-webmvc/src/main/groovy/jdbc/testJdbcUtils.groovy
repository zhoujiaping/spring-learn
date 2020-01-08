package jdbc

import org.springframework.jdbc.support.JdbcUtils

def names = """
parent_node,age,last_name,plan_a,a_plan
""".trim().split(/\s*,\s*/).each{
    def propName = JdbcUtils.convertUnderscoreNameToPropertyName(it)
    println propName
}
null?.each{
    println 'hello'
}

null.each{
    println 'hello'
}

