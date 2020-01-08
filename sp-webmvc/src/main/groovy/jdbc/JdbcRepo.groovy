package jdbc

import org.springframework.beans.DirectFieldAccessor
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.JdbcUtils
import org.springframework.jdbc.support.KeyHolder
import org.springframework.util.ReflectionUtils
import org.springframework.util.StringUtils

import java.lang.reflect.Field
import java.lang.reflect.Modifier
import java.lang.reflect.ParameterizedType

/**
 * @param < PK >  主键类型
 * @param < T >  实体类类型
 * 约定：表名以 t_ 开头
 * 每个表都有主键
 * 表的主键类型都是bigint
 * 每个表都有乐观锁字段，默认列名为version，类型为int
 * 实体类名是 表名去掉t_后转大写开头的驼峰
 * 主键列名是 表名+_id再去掉t_
 * 主键属性名是 表名+_id再去掉t_再转驼峰
 * 表名、主键名可以通过调用init方法重新设置
 *
 * spring jdbcTemplate好优秀，为什么我们一定要用mybatis呢
 */
abstract class JdbcRepo<PK,T> extends NamedParameterJdbcDaoSupport implements BaseDao<PK,T>{
    protected Class<PK> pkClazz
    protected Class<T> modelClazz
    protected String versionColumnName = "version"
    protected Set<String> columnNames
    protected Set<String> columnNamesNoPk

    protected String tableName
    protected String pkColumnName
    protected String pkPropName

    protected RowMapper<T> rowMapper

    protected Map<String, Field> columnNameToFieldMap = new TreeMap()

    JdbcRepo() {
        def uncapitalizedName = StringUtils.uncapitalize(modelClazz.simpleName)
        def underscoredName = uncapitalizedName.replaceAll("(?<Uper>[A-Z])", '_${Uper}').toLowerCase()
        def tName = "t_" + underscoredName
        def pkName = underscoredName + "_id"
        init(tName, pkName)
    }

    void init(String tableName, String pkColumnName) {
        this.tableName = tableName
        this.pkColumnName = pkColumnName
        this.pkPropName = JdbcUtils.convertUnderscoreNameToPropertyName(pkColumnName)
        ParameterizedType type = this.class.genericSuperclass
        pkClazz = type.actualTypeArguments[0]
        modelClazz = type.actualTypeArguments[1]
        rowMapper = new BeanPropertyRowMapper(modelClazz)
        ReflectionUtils.doWithFields(modelClazz) {
            if (!Modifier.isStatic(it.modifiers) && it.name != 'metaClass') {
                def columnName = it.name.replaceAll("(?<Uper>[A-Z])", '_${Uper}').toLowerCase()
                columnNameToFieldMap.put(columnName, it)
            }
        }
        columnNames = columnNameToFieldMap.keySet()
        columnNamesNoPk = columnNames.findAll {
            it != pkColumnName
        }
    }

    T query(PK id) {
        namedParameterJdbcTemplate.queryForObject("""select * from ${tableName} where ${
            pkColumnName
        }=:pk""", [pk: id], new BeanPropertyRowMapper(modelClazz))
    }

    List<T> query() {
        namedParameterJdbcTemplate.query("""select * from ${tableName} """, [:], new BeanPropertyRowMapper(modelClazz))
    }
    /**
     * 如果传了主键，就用传入的主键。否则，用自增长的主键。
     * @param t
     * @return
     */
    int insertSelective(T t) {
        def acc = new DirectFieldAccessor(t)
        if (acc.getPropertyValue(pkPropName) == null) {
            insertSelectiveWithAutoGenPkInternal(acc, t)
        } else {
            insertSelectiveWithPkInternal(acc, t)
        }
    }

    int insertSelectiveWithPk(T t) {
        def acc = new DirectFieldAccessor(t)
        insertSelectiveWithPkInternal(acc, t)
    }

    int insertSelectiveWithAutoGenPk(T t) {
        def acc = new DirectFieldAccessor(t)
        insertSelectiveWithAutoGenPkInternal(acc, t)
    }

    private int insertSelectiveWithPkInternal(DirectFieldAccessor acc, T t) {
        def columnNamesSelective = columnNames.findAll {
            acc.getPropertyValue(columnNameToFieldMap.get(it).name) != null
        }
        def columns = columnNamesSelective.join(',')
        def values = columnNamesSelective.collect {
            ":" + columnNameToFieldMap.get(it).name
        }.join(',')
        namedParameterJdbcTemplate.update("insert into ${tableName}($columns)values($values)",
                new BeanPropertySqlParameterSource(t))
    }

    private int insertSelectiveWithAutoGenPkInternal(DirectFieldAccessor acc, T t) {
        def columnNamesSelective = columnNamesNoPk.findAll {
            acc.getPropertyValue(columnNameToFieldMap.get(it).name) != null
        }
        def columns = columnNamesSelective.join(',')
        def values = columnNamesSelective.collect {
            ":" + columnNameToFieldMap.get(it).name
        }.join(',')
        KeyHolder kh = new GeneratedKeyHolder()
        def count = namedParameterJdbcTemplate.update("insert into ${tableName}($columns)values($values)",
                new BeanPropertySqlParameterSource(t), kh, [pkColumnName] as String[])
        acc.setPropertyValue(pkPropName, kh.key)
        count
    }
    /**
     * id不要更新
     * @param t
     * @return
     */
    int updateSelective(T t) {
        def acc = new DirectFieldAccessor(t)
        def columnNamesSelective = columnNamesNoPk.findAll {
            acc.getPropertyValue(columnNameToFieldMap.get(it).name) != null
        }
        def sets = columnNamesSelective.collect {
            "$it=:" + columnNameToFieldMap.get(it).name
        }.join(',')
        namedParameterJdbcTemplate.update("update ${tableName} set $sets where $pkColumnName=:$pkPropName",
                new BeanPropertySqlParameterSource(t))
    }

    /**
     * 包含乐观锁逻辑的更新
     * version字段不要更新，id不要更新
     * @param t
     * @return
     */
    int updateSelectiveWithOptimisticLock(T t) {
        def acc = new DirectFieldAccessor(t)
        def columnNamesSelective = columnNamesNoPk.findAll {
            it != versionColumnName && acc.getPropertyValue(columnNameToFieldMap.get(it).name) != null
        }
        def setList = columnNamesSelective.collect {
            "$it=:" + columnNameToFieldMap.get(it).name
        }
        setList << "$versionColumnName=$versionColumnName+1"
        def sets = setList.join(',')
        namedParameterJdbcTemplate.update("update ${tableName} set $sets where $pkColumnName=:$pkPropName",
                new BeanPropertySqlParameterSource(t))
    }

    int batchInsert(List<T> list) {
        def columns = columnNamesNoPk.join(',')
        def values = columnNamesNoPk.collect {
            ":" + columnNameToFieldMap.get(it).name
        }.join(',')
        def sources = list.collect {
            new BeanPropertySqlParameterSource(it)
        }
        int[] counts = namedParameterJdbcTemplate.batchUpdate("insert into ${tableName}($columns) values ($values)", sources as BeanPropertySqlParameterSource[])
        counts.inject(0) { prev, curr ->
            prev + curr
        }
    }
    /**
     * 要求主键不能为空
     * @param t
     * @return
     */
    int insertOrUpdateSelective(T t) {
        def acc = new DirectFieldAccessor(t)
        def columnNamesSelective = columnNames.findAll {
            it != versionColumnName && acc.getPropertyValue(columnNameToFieldMap.get(it).name) != null
        }
        def columns = columnNamesSelective.join(',')
        def values = columnNamesSelective.collect {
            ":" + columnNameToFieldMap.get(it).name
        }.join(',')
        def updates = columnNamesSelective.collect{
            "$it=:" + columnNameToFieldMap.get(it).name
        }
        updates << "$versionColumnName=$versionColumnName+1"
        def updatesPart = updates.join(',')
        def sql = """insert into $tableName($columns) values ($values) ON DUPLICATE KEY UPDATE $updatesPart"""
        namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(t))
    }

}
