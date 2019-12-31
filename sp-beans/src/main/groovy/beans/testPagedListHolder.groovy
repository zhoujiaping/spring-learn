import org.springframework.beans.support.PagedListHolder

def holder = new PagedListHolder("""
All the build logic is in the “build.gradle” 
file of the root project.[8] A “lib” 
dependency is a special form of an execution dependency. 
It causes the other project to be built first and adds 
the jar with the classes of the other project to the classpath. 
It also adds the dependencies of the other project to the classpath. 
So you can enter the “api” directory and trigger a “gradle compile”. 
First the “shared” project is built and then the “api” project is built. 
Project dependencies enable partial multi-project builds.
""".replaceAll(/\.|[-\[\]“”]/," ").trim().split(/\s+/).findAll{it.trim()})
//ps: /[\.-\[\]“”]/ 这样写有bug，每行第一个字符被替换了。。。

holder.pageSize = 5
println holder.pageCount
//println holder.pageList
while(!holder.lastPage){
    println holder.pageList
    holder.nextPage()
}

