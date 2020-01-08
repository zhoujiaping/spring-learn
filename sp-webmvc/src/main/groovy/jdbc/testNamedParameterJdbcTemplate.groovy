import groovy.transform.ToString
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterUtils
import org.springframework.jdbc.core.namedparam.ParsedSql
import org.springframework.jdbc.datasource.SingleConnectionDataSource
@ToString
class Demo{
    Long demoId
    String creator
}
/**
 * hibernate、mybatis、jdbcTemplate 感觉jdbcTemplate挺好的
 */
def url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8"
def ds = new SingleConnectionDataSource(url,"root","",true)
def jdbcTemplate = new NamedParameterJdbcTemplate(ds)
RowMapper rm = new BeanPropertyRowMapper(Demo)
//println jdbcTemplate.query("select * from t_demo where demo_id in (:demoId) limit 2",[demoId:[100]],rm)

//println jdbcTemplate.query("select * from t_demo where demo_id > :demoId limit 2",[demoId:100],rm)

//	public <T> List<T> queryForList(String sql, Map<String, ?> paramMap, Class<T> elementType)
//	这个方法的elementType只能用简单类型，不能用自定义的Bean
//def userList = jdbcTemplate.queryForList("select * from t_demo where demo_id>:demoId limit 2",[demoId:100],Demo)
//println userList

/**
 * substituteNamedParameters方法支持Map里面的value为集合类型，集合类型里面的元素，支持Object数。
 * 这可以非常方便地支持批量操作
 */
int count = jdbcTemplate.update("insert into t_demo(demo_id,creator)values :values",new MapSqlParameterSource([
        values:[
                [20,"aaa"] as Object[],
                [21,"bbb"] as Object[],
        ]
]))
println count

def objList = [new Demo([creator:'o',updateTime:new Date()]),new Demo([creator:'p',updateTime:new Date()])]
def resources = objList.collect{
    new BeanPropertySqlParameterSource(it)
}
int[] count2 = jdbcTemplate.batchUpdate("insert into t_demo(creator,update_time)values (:creator,:updateTime)",
        resources as BeanPropertySqlParameterSource[])
println count2
