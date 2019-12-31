import org.springframework.util.comparator.BooleanComparator

println BooleanComparator.TRUE_HIGH.compare(true,Boolean.FALSE)
println BooleanComparator.TRUE_HIGH.compare(false,Boolean.FALSE)
println BooleanComparator.TRUE_HIGH.compare(false,Boolean.TRUE)