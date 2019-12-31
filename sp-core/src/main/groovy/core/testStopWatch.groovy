import org.springframework.util.StopWatch

def watch = new StopWatch()
watch.start("mytest")
Thread.sleep(1008)
watch.stop()
println watch.prettyPrint()