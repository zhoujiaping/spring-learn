import org.springframework.util.StringUtils


assert StringUtils.applyRelativePath("http://localhost/abc","/aaa")=='http://localhost/aaa'
assert StringUtils.applyRelativePath("http://localhost/abc/","/aaa")=='http://localhost/abc/aaa'

assert StringUtils.applyRelativePath("http://localhost/abc","aaa")=='http://localhost/aaa'
assert StringUtils.applyRelativePath("http://localhost/abc/","aaa")=='http://localhost/abc/aaa'

assert !StringUtils.hasText("  ")
assert StringUtils.isEmpty("")
assert !StringUtils.isEmpty(" ")
assert StringUtils.capitalize("helloKitty") == 'HelloKitty'
assert StringUtils.uncapitalize("HelloKitty") == 'helloKitty'
assert StringUtils.getFilename("d:/a/b.txt") == 'b.txt'
assert StringUtils.cleanPath("d:\\a\\./b\\../c.txt") == 'd:/a/c.txt'
assert StringUtils.pathEquals("d:\\a\\b","d:/a/../a/b")
assert StringUtils.addStringToArray(['hello'] as String[],"kitty") == ['hello','kitty']
assert StringUtils.concatenateStringArrays(['hello'] as String[],['kitty'] as String[]) == ['hello','kitty'] as String[]
assert StringUtils.mergeStringArrays(['hello'] as String[],['kitty'] as String[]) == ['hello','kitty'] as String[]
assert StringUtils.sortStringArray(['kitty','hello'] as String[]) == ['hello','kitty'] as String[]
assert StringUtils.toStringArray(['hello','kitty']) == ['hello','kitty'] as String[]

def vector = new Vector()
vector.addAll(['hello','kitty'])
assert StringUtils.toStringArray(vector) == ['hello','kitty'] as String[]

assert StringUtils.trimArrayElements([' hello ','\tkitty\n'] as String[]) == ['hello','kitty'] as String[]

assert StringUtils.removeDuplicateStrings(['hello','kitty','hello'] as String[]) == ['hello','kitty'] as String[]
println StringUtils.splitArrayElementsIntoProperties(
        ['hello=HELLO','kitty= KITTY','hello'] as String[],'=')
//[hello:HELLO, kitty:KITTY]


