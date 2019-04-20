package pers.joel.daos.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import pers.joel.daos.GenericDao;
import pers.joel.models.QueryResult;

import java.util.List;
import java.util.Map;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 *
 * @param <T> a type variable
 */
public class GenericDaoImpl<T> implements GenericDao<T> {
    /**
     * Log variable for all child classes. Uses LoggerFactory.getLogger(getClass())
     * from slf4j
     */
    protected transient final Logger log = LoggerFactory.getLogger(getClass());

    private Class<T> persistentClass;

    public GenericDaoImpl(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    protected JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    ////////////////////
    public List<Map<String, Object>> queryForListMap(String queryString) {
        List<Map<String, Object>> lm = jdbcTemplate.queryForList(queryString);
        log.debug("++++++++++++++++++ sql=" + queryString);
        if (lm.size() == 0)
            log.debug("~~~~~~~~~~~~~~~~~~ resultSize=0");
        log.trace("================== result=" + lm);
        return lm;
    }

    ////////////////////
    public Map<String, Object> queryForMap(String queryString) {
        try {
            log.debug("++++++++++++++++++ sql=" + queryString);
            Map<String, Object> m = jdbcTemplate.queryForMap(queryString);
            log.trace("================== result=" + m);
            return m;
        } catch (DataAccessException dae) {
            log.debug("~~~~~~~~~~~~~~~~~~ resultSize=0");
            return null;
        }
    }

    public void execute(String sql) {
        log.debug("------------------ executing...sql=" + sql);
        jdbcTemplate.execute(sql);
    }

    public int update(String sql) {
        log.debug("================== updating...sql=" + sql);
        int updated = jdbcTemplate.update(sql);
        log.debug("~~~~~~~~~~~~~~~~~~ " + updated + " row(s) updated");
        return updated;
    }

    /**
     * 默认Mysql中使用
     * 获得自增主键(新增数据后调用才生效)
     */
    public int getPrimaryKey() {
        String sql = "SELECT LAST_INSERT_ID()";
        int primarykey = jdbcTemplate.queryForObject(sql, Integer.class);
        log.debug("获取唯一识别号：" + primarykey);
        return primarykey;
    }

    /**
     * 默认Mysql中使用
     * 获得自增主键(新增数据后调用才生效)
     */
    public long getLongPrimaryKey() {
        String sql = "SELECT LAST_INSERT_ID()";
        long primarykey = jdbcTemplate.queryForObject(sql, Long.class);
        log.debug("获取唯一识别号：" + primarykey);
        return primarykey;
    }

    /**
     * @param pageNumber 起始页码
     * @return mysql的分页语句
     */
    protected String getPageSql(String queryString, int pageNumber, int linesPerPage) {
        int limitFrom = (pageNumber - 1) * linesPerPage;
        if (limitFrom < 0) limitFrom = 0;
        if (linesPerPage < 0) linesPerPage = 20;
        return queryString + " LIMIT " + limitFrom + "," + linesPerPage;
    }

    /**
     * 分页查询
     *
     * @return QueryResult
     */
    public QueryResult<T> query(String queryString, int pageNumber, int linesPerPage) {
        QueryResult<T> qr = new QueryResult<>();
        /** 符合查询条件的记录的总条数 */
        String countSql = String.format("SELECT COUNT(*) FROM (%s) T", queryString);
        int count = jdbcTemplate.queryForObject(countSql, Integer.class);
        log.debug("++++++++++++++++++ countSql=" + countSql);
        log.trace("(((((((((((((((((( count=" + count);
        qr.setCount(count);

        queryString = getPageSql(queryString, pageNumber, linesPerPage);
        qr.setList(getObjects(queryString));

        return qr;
    }

    /**
     * 分页查询，同find类似，只是增加了countString的参数
     * 主要是mysql的count查询时，只查询索引列会比查询*速度快很多
     */
    public QueryResult<T> query(String queryString, String countSql, int pageNumber, int linesPerPage) {
        QueryResult<T> qr = new QueryResult<>();
        /** 符合查询条件的记录的总条数 */
        int count = jdbcTemplate.queryForObject(countSql, Integer.class);
        log.debug("++++++++++++++++++ countSql=" + countSql);
        log.trace("(((((((((((((((((( count=" + count);
        qr.setCount(count);

        queryString = getPageSql(queryString, pageNumber, linesPerPage);
        qr.setList(getObjects(queryString));

        return qr;
    }

    /**
     * 分页查询2,返回map的list对象列表
     */
    public QueryResult<Map<String, Object>> queryForMap(String queryString, int pageNumber, int linesPerPage) {
        QueryResult<Map<String, Object>> qr = new QueryResult<>();
        /** 符合查询条件的记录的总条数 */
        String countSql = String.format("SELECT COUNT(*) FROM (%s) T", queryString);
        int count = jdbcTemplate.queryForObject(countSql, Integer.class);
        log.debug("++++++++++++++++++ countSql=" + countSql);
        log.trace("(((((((((((((((((( count=" + count);
        qr.setCount(count);

        queryString = getPageSql(queryString, pageNumber, linesPerPage);

        qr.setList(queryForListMap(queryString));

        return qr;
    }

    /**
     * @return List<Class>
     * 返回的list是clazz的对象集
     */
    public List<T> getObjects(String queryString) {
        BeanPropertyRowMapper<T> b = BeanPropertyRowMapper.newInstance(persistentClass);
        b.setPrimitivesDefaultedForNullValue(true);
        List<T> lt = jdbcTemplate.query(queryString, b);
        log.debug("++++++++++++++++++ sql=" + queryString);
        if (lt.size() == 0)
            log.debug("~~~~~~~~~~~~~~~~~~ resultSize=0");
        log.trace("================== result=" + lt);
        return lt;
    }

    /**
     * @return T
     * 返回的是clazz的对象
     */
    public T getAObject(String queryString) {
        BeanPropertyRowMapper<T> b = BeanPropertyRowMapper.newInstance(persistentClass);
        b.setPrimitivesDefaultedForNullValue(true);
        List<T> lt = jdbcTemplate.query(queryString, b);
        log.debug("++++++++++++++++++ sql=" + queryString);
        if (lt.size() == 0) {
            log.debug("~~~~~~~~~~~~~~~~~~ resultSize=0");
            return null;
        }
        log.trace("================== result=" + lt);
        return lt.get(0);
    }

    /**
     * 返回结果集的大小
     */
    public int findObjectSize(String countSql) {
        int count = jdbcTemplate.queryForObject(countSql, Integer.class);
        log.debug("++++++++++++++++++ countSql=" + countSql);
        log.trace("(((((((((((((((((( count=" + count);
        return count;
    }


}
