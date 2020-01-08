import org.springframework.remoting.httpinvoker.HttpInvokerClientConfiguration
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean
import org.springframework.remoting.httpinvoker.SimpleHttpInvokerRequestExecutor
import org.springframework.remoting.support.RemoteInvocation
import org.springframework.remoting.support.RemoteInvocationResult

def executor = new SimpleHttpInvokerRequestExecutor()
HttpInvokerClientConfiguration config = new HttpInvokerProxyFactoryBean()
config.serviceUrl = "http://localhost:8080/mypro/mybean"
config.afterPropertiesSet()
RemoteInvocation invocation = new RemoteInvocation()
invocation.arguments = []
invocation.methodName = "test"
invocation.parameterTypes = []
RemoteInvocationResult result = executor.executeRequest(config, invocation)
println result.value