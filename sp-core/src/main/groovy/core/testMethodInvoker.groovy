package core

import org.springframework.util.MethodInvoker

def ivk = new MethodInvoker()
ivk.targetClass = Integer
ivk.targetMethod = "parseInt"
ivk.targetObject = null
ivk.arguments = ["100"]

ivk.prepare()

println ivk.invoke()

