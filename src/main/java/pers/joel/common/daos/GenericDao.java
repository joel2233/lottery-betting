package pers.joel.common.daos;


import pers.joel.common.models.QueryResult;

import java.util.List;
import java.util.Map;

/**
 * Generic DAO (Data Access Object) with common methods to CRUD POJOs.
 * <p>
 * <p>
 * Extend this interface if you want typesafe (no casting necessary) DAO's for
 * your domain objects.
 *
 * @param <T> a type variable
 * @author <a href="mailto:javaworld@qq.com">Woody</a>
 * @creation 2013年11月1日
 */
public interface GenericDao<T> {

    /**
     * 执行SQL语句，只建议DDL语句
     *
     * @param sql
     */
    void execute(String sql);

    /**
     * 执行SQL语句，INSERT,UPDATE,DELETE
     *
     * @param sql
     */
    int update(String sql);

    /**
     * 执行queryString语句返回Map
     *
     * @param queryString
     * @return
     * @add by zaweze on 2017-10-26 就是补了一个接口的方法定义
     */
    Map<String, Object> queryForMap(String queryString);

    /**
     * 执行queryString语句返回list
     * list中是Map
     * Map中是查询的每行记录集
     * <p>
     * 推荐使用 findObject 方法
     *
     * @param queryString
     * @return list
     */
    List<Map<String, Object>> queryForListMap(String queryString);

    /**
     * 获得自增主键(新增数据后调用才生效)
     *
     * @return
     */
    int getPrimaryKey();

    /**
     * 获得自增主键(新增数据后调用才生效)
     *
     * @return
     */
    long getLongPrimaryKey();

    /**
     * 分页查询
     *
     * @param queryString  qureySting 例子: SELECT NAME,ID,EMAIL FROM USER
     * @param pageNumber
     * @param linesPerPage
     * @return
     */
    QueryResult<T> query(String queryString, int pageNumber, int linesPerPage);

    /**
     * 分页查询，同find类似，只是增加了countString的参数
     * 主要是mysql的count查询时，只查询索引列会比查询*速度快很多
     *
     * @param queryString
     * @param countString
     * @param pageNumber
     * @param linesPerPage
     * @return QueryResult<T>
     */
    QueryResult<T> query(String queryString, String countString, int pageNumber, int linesPerPage);

    QueryResult<Map<String, Object>> queryForMap(String queryString, int pageNumber, int linesPerPage);

    /**
     * @param queryString
     * @return List<Class>
     */

    List<T> getObjects(String queryString);

    T getAObject(String queryString);

    int findObjectSize(String countSql);

}
