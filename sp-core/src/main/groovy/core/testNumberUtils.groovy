import org.springframework.util.NumberUtils

assert NumberUtils.convertNumberToTargetClass(1,Long).class == Long
assert new String(NumberUtils.parseNumber('0x61',Byte)) == 'a'
assert new String([Byte.parseByte('61',16)] as Byte[]) == 'a'

assert NumberUtils.decodeBigInteger('#61') == 97