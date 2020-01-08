import beans.DemoService

beans{
    xmlns context: 'http://www.springframework.org/schema/context'
    demoService(DemoService){
        someProp = 'hello world'
    }
    context.'component-scan'('base-package':'beans','use-default-filters':false){
        context.'exclude-filter'(type:'annotation',expression:'org.springframework.stereotype.Controller')
    }
}