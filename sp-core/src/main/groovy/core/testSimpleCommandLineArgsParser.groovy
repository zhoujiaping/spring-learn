import org.springframework.core.env.SimpleCommandLineArgsParser

def parser = new SimpleCommandLineArgsParser()
def args = parser.parse("test.txt","--color=red","--smell=xxx")
println args.optionNames