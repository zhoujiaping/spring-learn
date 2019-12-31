import org.springframework.beans.DirectFieldAccessor
//BeanWrapperImpl以get,set方法访问属性,DirectFieldAccessor直接操作属性变量
def acc = new DirectFieldAccessor(new Object(){
    private String name = 'Neo'
    String nick = 'hack'
    Integer age = 100
    Object job = new Object(){
        Number salary = 1000
    }
    def wife = ['ab','cd']
    def map = ['ONE':1]
})
println acc.getPropertyValue("name")
println acc.getPropertyValue("job.salary")
println acc.getPropertyValue("wife[1]")
println acc.getPropertyValue("map[ONE]")