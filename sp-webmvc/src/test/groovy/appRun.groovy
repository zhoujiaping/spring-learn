import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext
//用这个脚本启动会报错。。。因为通过脚本方式执行，类加载器环境会不一样
def projectName = 'spring-learn'
def port = 8080
def userDir = System.getProperty("user.dir")
def webappPath = "src/main/webapp"
/*if(!userDir.endsWith(projectName)){
    webappPath = projectName+"/" + webappPath
}*/
webappPath = "../../../" + webappPath
// 创建Server
def server = new Server(port)
def webContext = new WebAppContext(webappPath, "/"+ projectName)
server.insertHandler(webContext)
server.stop()
server.start()
server.join()
