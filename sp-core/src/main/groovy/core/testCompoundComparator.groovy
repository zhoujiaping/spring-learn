import org.springframework.util.comparator.CompoundComparator

/**
 *
 * 组合多个比较器，逐个调用。如果某个比较器返回0，则继续调用。否则，返回值。
 */
def comparator = new CompoundComparator({a,b->
    Integer.parseInt(a[0])-Integer.parseInt(b[0])
} as Comparator,{a,b->
    Integer.parseInt(a[1])-Integer.parseInt(b[1])
}  as Comparator,{a,b->
    Integer.parseInt(a[2])-Integer.parseInt(b[2])
}  as Comparator)
println comparator.compare("1234","1234")
println comparator.compare("1234","123")
println comparator.compare("1234","121")
println comparator.compare("1234","129")
println comparator.compare("1234","12345")