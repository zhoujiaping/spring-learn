import org.springframework.core.task.SimpleAsyncTaskExecutor
import org.springframework.util.ConcurrencyThrottleSupport

/**
 * 并发控制工具
 * 控制同一时刻执行方法的线程数
 * 实际应用场景：比如系统最大能同时支持处理10个业务.
 * 那么可以设置并发上限为10.
 */
def adapter = new ConcurrencyThrottleSupport(){
    void invoke(){
        try{
            beforeAccess()
            Thread.sleep(1000)
            println System.currentTimeMillis()
        }finally{
            afterAccess()
        }
    }
}
adapter.concurrencyLimit = 2

(1..5).collect{
    new Thread({
        ->
        adapter.invoke()
    })
}.each {
    it.start()
}
/*结果
1574997040050
1574997040054
1574997041053
1574997041054
1574997042053
* */