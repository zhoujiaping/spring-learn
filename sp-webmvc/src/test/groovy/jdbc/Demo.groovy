package jdbc

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class Demo {
    Long demoId
    Integer version
    String creator
    Date createTime
    String modifier
    Integer validFlag
    Date updateTime
}
