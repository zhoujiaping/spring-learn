import org.springframework.util.ConcurrentReferenceHashMap

import java.lang.ref.SoftReference
import java.lang.ref.WeakReference
import java.util.concurrent.ConcurrentHashMap

/**
 * ConcurrentReferenceHashMap与ConcurrentHashMap的区别是
 * ConcurrentReferenceHashMap能指定所存放对象的引用级别，适用于并发下Map的数据缓存。
 *
 * ConcurrentReferenceHashMap是基于Segment分段锁实现的，
 * 与JDK1.7的ConcurrentHashMap是一样的，
 * 与JDK1.8的ConcurrentHashMap是不一样的。
 *
 * ConcurrentHashMap 1.7和1.8区别
 * https://blog.csdn.net/xingxiupaioxue/article/details/88062163
 */
def map = new ConcurrentReferenceHashMap(16, ConcurrentReferenceHashMap.ReferenceType.WEAK)
map.put("xxxxx","world")
println map
System.gc()
println map

WeakReference weakRef = new WeakReference(["i":"am"])
println weakRef.get()
System.gc()
println weakRef.get()
