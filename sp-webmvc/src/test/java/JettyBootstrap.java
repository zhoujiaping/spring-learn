import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

//-javaagent:/home/wt/IdeaProjects/java-agent/target/java-agent-jar-with-dependencies.jar=/home/wt/IdeaProjects/java-agent/src/main/resources/script
public class JettyBootstrap {
    private static final String PROJECT_NAME = "spring-learn";
    private static final int PORT = 8080;
    public static void main(String[] args) throws Exception {
        String userDir = System.getProperty("user.dir");
        String webappPath = "src/main/webapp";
        if(!userDir.endsWith(PROJECT_NAME)){
            webappPath = PROJECT_NAME+"/" + webappPath;
        }
        // 创建Server
        Server server = new Server(PORT);
        WebAppContext webContext = new WebAppContext(webappPath, "/"+ PROJECT_NAME);
        server.insertHandler(webContext);
        server.stop();
        server.start();
        String echoUrl = "http://localhost:"+PORT+"/"+PROJECT_NAME+"/echo";
        System.out.println("回声地址："+echoUrl);
        server.join();
    }
}

