def port = 8080
def cmd = $/cmd /c netstat -ano|findstr "$port"/$
println cmd
def process = cmd.execute()
println process.err.text

def text = process.text
println text
text.eachLine {
    line->
        if(line.contains('TCP')){
            def pid = line.split(/\s+/)[-1]
            cmd = "cmd /c taskkill /pid $pid /F"
            println cmd
            process = cmd.execute()
            println process.err.text
            println process.text
        }
}