import java.util.jar.JarEntry
import java.util.jar.JarFile

def pom = "jar:file:///userhome/.gradle/caches/modules-2/files-2.1/ch.qos.logback/logback-classic/1.1.2/b316e9737eea25e9ddd6d88eaeee76878045c6b2/logback-classic-1.1.2.jar!/META-INF/maven/ch.qos.logback/logback-classic/pom.xml"
println new URL(pom).openStream().bytes.length
