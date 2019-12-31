import beans.DemoService
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.beans.factory.support.DefaultListableBeanFactory
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader

def fac = new DefaultListableBeanFactory()
def reader = new XmlBeanDefinitionReader(fac)
reader.loadBeanDefinitions("classpath:applicationContext.xml")
DemoService demoService = fac.getBean("demoService")
println demoService.someProp
