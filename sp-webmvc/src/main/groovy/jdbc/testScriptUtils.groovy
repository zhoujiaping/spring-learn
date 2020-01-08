import groovy.sql.Sql
import groovy.transform.Field
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.ScriptUtils
import org.springframework.util.ResourceUtils


Sql getSql(){
    def url = 'jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8'
    def user = 'root'
    def password = ''
    def driver = 'com.mysql.jdbc.Driver'
    def sql = Sql.newInstance(url, user, password, driver)
    return sql
}
Sql sql = getSql()


def conn = sql.connection
def resource = new ClassPathResource("test.sql")
ScriptUtils.executeSqlScript(conn, resource)

