import org.springframework.util.backoff.ExponentialBackOff

def backOff = new ExponentialBackOff()
backOff.initialInterval = 1000
backOff.multiplier = 2
backOff.maxInterval = 4000
def execution = backOff.start()
println execution.nextBackOff()
println execution.nextBackOff()
println execution.nextBackOff()
println execution.nextBackOff()
println execution.nextBackOff()
println execution.nextBackOff()
println execution.nextBackOff()
