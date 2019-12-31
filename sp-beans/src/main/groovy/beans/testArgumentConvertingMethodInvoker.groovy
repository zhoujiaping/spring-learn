import org.springframework.beans.support.ArgumentConvertingMethodInvoker

def ivk = new ArgumentConvertingMethodInvoker()
ivk.targetClass = Integer
ivk.targetMethod = "parseInt"
ivk.arguments = 100
ivk.prepare()

println ivk.invoke()