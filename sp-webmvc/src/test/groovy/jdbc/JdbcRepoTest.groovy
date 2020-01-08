package jdbc

import org.junit.BeforeClass
import org.junit.Test
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.datasource.SingleConnectionDataSource

class JdbcRepoTest {
    static JdbcRepo demoRepo = new JdbcRepo<Long, Demo>() {}

    @BeforeClass
    static void beforeClass() {
        def url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8"
        def ds = new SingleConnectionDataSource(url, "root", "", true)
        demoRepo.dataSource = ds
    }

    @Test
    void testQuery() {
        println demoRepo.query()
    }

    @Test
    void testQueryByPk() {
        println demoRepo.query(4)
    }

    @Test
    void testInsertSelective() {
        def demo = new Demo([
                creator  : "zjp",
                validFlag: 0
        ])
        println demoRepo.insertSelective(demo)
        println demo
    }

    @Test
    void testUpdateSelective() {
        def demo = new Demo([
                demoId   : 12,
                creator  : "zhou",
                validFlag: 1
        ])
        println demoRepo.updateSelective(demo)
        println demo
    }

    @Test
    void testUpdateSelectiveWithOptimisticLock() {
        def demo = new Demo([
                demoId   : 12,
                version  : 1,
                creator  : "zz",
                validFlag: 1
        ])
        println demoRepo.updateSelectiveWithOptimisticLock(demo)
        println demo
    }

    @Test
    void testBatchInsert() {
        def demos = [new Demo([
                version   : 1,
                creator   : "d1",
                validFlag : 1,
                createTime: new Date(),
                updateTime: new Date()
        ]), new Demo([
                version   : 2,
                creator   : "d2",
                validFlag : 1,
                createTime: new Date(),
                updateTime: new Date()
        ])]
        println demoRepo.batchInsert(demos)
        println demos
    }

    @Test
    void testInsertOrUpdateSelective() {
        println demoRepo.insertOrUpdateSelective(new Demo([
                demoId : 27,
                creator: 'd3'
        ]))
    }
}
