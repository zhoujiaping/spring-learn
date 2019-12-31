package core
//测试循环依赖
class A{
    B b
}
class B{
    A a
}
def app = [:]
def todo = [:]
def a = A.newInstance()
app.a = a
def b = app.b
if(b){
    a.b = b
}else{
    todo.b={a.b = it}
}
b = B.newInstance()
todo.b.call(b)

b.a = app.a

println a
println b