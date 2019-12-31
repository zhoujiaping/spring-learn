import org.springframework.util.FastByteArrayOutputStream

/**
 * 和ByteArrayOutputStream不同的是，
 * FastByteArrayOutputStream减少了字节数组的拷贝，性能上有优势
 */
def out = new FastByteArrayOutputStream(2)
out.write('abc'.bytes)
byte[] res = out.toByteArray()