import org.springframework.beans.BeanWrapperImpl

def wrapper = new BeanWrapperImpl()
def o = new Object(){
    String count
}
wrapper.beanInstance = o
println wrapper.convertIfNecessary("123",Integer)
wrapper.setPropertyValue("count",1)
println o.count