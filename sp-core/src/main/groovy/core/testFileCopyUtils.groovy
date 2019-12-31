import org.springframework.util.FileCopyUtils
import org.springframework.util.StreamUtils

/**
 * 和StreamUtils不同的是，该工具类会自动关闭流
 */
FileCopyUtils.copy("""\
diffEciqsCreditApplyReq world\
""".bytes,new File("/testFileCopyUtils.txt"))

FileCopyUtils.copy(new File("/testFileCopyUtils.txt"),
        new File("/testFileCopyUtils2.txt"))