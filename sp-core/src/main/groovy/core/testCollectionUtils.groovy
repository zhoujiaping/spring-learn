import org.springframework.util.CollectionUtils

assert CollectionUtils.toMultiValueMap(['a':[1,2,3]]).a == [1,2,3]