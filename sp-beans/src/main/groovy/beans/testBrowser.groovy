def cmd = """cmd /c c:"""
println cmd
def process = cmd.execute()
println process.err.text
println process.text

cmd = '''cmd /c cd "C:/Program Files (x86)/Google/Chrome/Application"'''
println cmd
process = cmd.execute()
println process.err.text
println process.text

cmd = '''cmd /c start chrome.exe D:/test.html --allow-file-access-from-files'''
println cmd
process = cmd.execute()
println process.err.text
println process.text