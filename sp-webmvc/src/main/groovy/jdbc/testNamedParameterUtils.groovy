import com.alibaba.fastjson.JSON
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterUtils
import org.springframework.jdbc.core.namedparam.ParsedSql

ParsedSql sql = NamedParameterUtils.parseSqlStatement('''
select * from t_demo where id=:id and age>:ageMin and age <= :{ageMax}
''')
println sql

println NamedParameterUtils.substituteNamedParameters(sql,new MapSqlParameterSource([
        id:1,
        ageMin:100,
        ageMax:200
]))
println NamedParameterUtils.buildValueArray(sql,new MapSqlParameterSource([
        id:1,
        ageMin:100,
        ageMax:200
]),null)
//返回的是  java.sql.Types中定义的常量的数组
println NamedParameterUtils.buildSqlTypeArray(sql,new BeanPropertySqlParameterSource(new Object(){
    Long id = 1
    Integer ageMin = 100
    Integer ageMax = 200
}))


def parameterList = NamedParameterUtils.buildSqlParameterList(sql,new BeanPropertySqlParameterSource(new Object(){
    Long id = 1
    Integer ageMin = 100
    Integer ageMax = 200
}))
println JSON.toJSONString(parameterList)


