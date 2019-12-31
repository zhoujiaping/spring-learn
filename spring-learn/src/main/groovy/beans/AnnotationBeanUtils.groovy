import groovy.transform.ToString
import org.springframework.beans.annotation.AnnotationBeanUtils

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Retention(RetentionPolicy.RUNTIME)
@interface Table{
    String tableName()
}
@Table(tableName="t_demo")
class DemoMapper{

}
@ToString
class TableModel{
    String tableName
}

def anno = DemoMapper.getAnnotationsByType(Table)[0]
def tm = new TableModel()
AnnotationBeanUtils.copyPropertiesToBean(anno,tm)
println tm