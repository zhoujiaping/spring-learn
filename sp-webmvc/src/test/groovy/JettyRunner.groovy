import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext
/*
idea控制台中文乱码
https://blog.csdn.net/m0_37800137/article/details/78914364
-Dfile.encoding=UTF-8

目录结构
sp-webmvc
    classes
    lib
    webapp
>cd xxx/sp-webmvc/classes
>java -cp ".;../lib/*" -Dwebapp.path=../webapp JettyRunner
或者java -cp ".;../lib/*" JettyRunner
 */

class JettyRunner {
    static final String PROJECT_NAME = "sp-webmvc"
    static final int PORT = 8989
    static void main(String[] args) throws Exception {
        String webappPath = System.getProperty("webapp.path")
        println """System.getProperty("webapp.path")=>$webappPath"""
        if(!webappPath){
            webappPath = useDefaultWebappPath()
        }
        println "webappPath=>$webappPath"
        // 创建Server
        Server server = new Server(PORT)
        WebAppContext webContext = new WebAppContext(webappPath, "/"+ PROJECT_NAME)
        server.insertHandler(webContext)
        server.stop()
        server.start()
        String echoUrl = "localhost:"+PORT+"/"+PROJECT_NAME+"/index.html"
        System.out.println("回声地址："+echoUrl)
        server.join()
    }
    static String useDefaultWebappPath(){
        String userDir = System.getProperty("user.dir")
        //java -jar xxx.jar执行的话，userDir就是jar包所在目录
        println "userDir=> $userDir"
        //多模块工程的情况下，userDir并不是src/main/webapp的上级目录。
        if(new File(userDir, "src/main/webapp").exists()){
            return "src/main/webapp"
        }else if(new File(userDir, PROJECT_NAME+"/src/main/webapp").exists()){
            return PROJECT_NAME+"/src/main/webapp"
        }else if(new File(userDir, "webapp").exists()){
            //其他情况，比如打包成jar，就用userDir目录下的webapp
            return "webapp"
        }else{
            return "../webapp"
        }
    }
}
