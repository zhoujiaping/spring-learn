import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext

class JettyRun {
    static void main(String[] args) {
        def projectName = 'spring-learn'
        def port = 8080
        def userDir = System.getProperty("user.dir")
        def webappPath = "src/main/webapp"
        if (!userDir.endsWith(projectName)) {
            webappPath = projectName + "/" + webappPath
        }

        def server = new Server(port)
        def webContext = new WebAppContext(webappPath, "/" + projectName)
        server.insertHandler(webContext)
        server.stop()
        server.start()
        println "echo: http://localhost:${port}/$projectName/echo"
        server.join()
    }
}
