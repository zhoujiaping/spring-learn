package jdbc

interface BaseDao<PK extends Number,T> {
    void init(String tableName, String pkColumnName)
    T query(PK id)
    List<T> query()
    int insertSelective(T t)
    int insertSelectiveWithPk(T t)
    int insertSelectiveWithAutoGenPk(T t)
    int updateSelective(T t)
    int updateSelectiveWithOptimisticLock(T t)
    int batchInsert(List<T> list)
    int insertOrUpdateSelective(T t)
}