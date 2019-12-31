import org.springframework.util.CustomizableThreadCreator

def tc = new CustomizableThreadCreator("my-thread-")
def thread = tc.createThread({
    ->
    println System.currentTimeMillis()
})
println thread.name