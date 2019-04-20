package pers.joel.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:javaworld@qq.com">Woody</a>
 * @creation 2013年11月13日
 */
public class QueryResult<T> {
	/**
	 * 符合查询条件结果的总数量，而不是list的大小；
	 */
	int count;

	/**
	 * 结果集的列表
	 */
	List<T> list;
	/**
	 * 设置一些固定信息外的额外信息，不定key的值可以直接通过他传递而不需要再构建数据结构
	 */
	Map<String,Object> extraMap = new HashMap<>();

	public Map getExtraMap(){
		return extraMap;
	}
	public void setExtraMap(String key,Object value){
		this.extraMap.put(key,value);
	}
	/**
	 * @return Returns the count.
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            The count to set.
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * @return Returns the list.
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * @param list
	 *            The list to set.
	 */
	public void setList(List<T> list) {
		this.list = list;
	}
}
