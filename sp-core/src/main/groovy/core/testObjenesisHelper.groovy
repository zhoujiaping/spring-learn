import org.springframework.objenesis.ObjenesisHelper

class Pig implements Serializable{
    String name
    Pig(String name){
        println "Pig($name)"
        this.name = name
    }
}
// 不使用构造方法创建Java对象： objenesis的基本使用方法 https://blog.csdn.net/codershamo/article/details/52015206

println ObjenesisHelper.newInstance(Pig)
println ObjenesisHelper.newSerializableInstance(Pig).class.interfaces
