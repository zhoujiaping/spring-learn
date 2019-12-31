import org.springframework.util.ClassUtils

assert ClassUtils.getShortNameAsProperty(ClassUtils) == 'classUtils'

assert ClassUtils.forName("int",Thread.currentThread().contextClassLoader) == int

assert ClassUtils.getClassFileName(ClassUtils) == 'ClassUtils.class'

assert ClassUtils.getPackageName(ClassUtils) == 'org.springframework.util'

assert ClassUtils.getQualifiedName(ClassUtils) == 'org.springframework.util.ClassUtils'

assert ClassUtils.getQualifiedName(ClassUtils[]) == 'org.springframework.util.ClassUtils[]'

assert ClassUtils.getQualifiedMethodName(ClassUtils.methods.find{
    it.name == 'forName'
}) == 'org.springframework.util.ClassUtils.forName'

assert ClassUtils.getDescriptiveType(ClassUtils)=='java.lang.Class'

assert ClassUtils.matchesTypeName(ClassUtils[],'org.springframework.util.ClassUtils[]')

assert ClassUtils.addResourcePathToPackagePath(testClassUtils,'testAssert.groovy') == '/testAssert.groovy'

assert ClassUtils.isAssignable(Object,List)





