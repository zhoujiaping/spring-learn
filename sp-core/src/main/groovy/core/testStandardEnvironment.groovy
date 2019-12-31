import org.springframework.core.env.MapPropertySource
import org.springframework.core.env.StandardEnvironment
//https://blog.csdn.net/xwq911/article/details/50113121
def env = new StandardEnvironment()
def propSrc = new MapPropertySource("someProps",
        [
                "someOne":"kitty",
                "hi":'hi ${someOne}!'
        ])
env.propertySources.addFirst(propSrc)
println env.getProperty("someOne")
println env.getProperty("hi")
println env.getProperty("user.home")
println env.getActiveProfiles()
println env.properties
//System.setProperty("spring.profiles.active","dev")
//-Dspring.profiles.active=dev
println env.activeProfiles
env.activeProfiles = ['dev']
if(env.acceptsProfiles('dev')){
    println "do smt on dev"
}