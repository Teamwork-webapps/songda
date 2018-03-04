package com.songda.recruit.service.base;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.songda.recruit.base.BaseSearchByPageDTO;
import com.songda.recruit.dao.base.BaseDao;
import com.songda.recruit.service.jpa.LogicSearchFilter;

public interface BaseSearchService<T> {

	public abstract BaseDao<T> getDao();
	
	public T findOne(Object id);
	
	public List<T> findAllUnDel(Class<T> clazz);
	
	/**
	 * 根据参数进行查询，获得符合条件的行数
	 * @param filters
	 * @return
	 */
	public Long getCountByParam(Map<String, LogicSearchFilter> filters , Class<T> clazz);
	
	/**
	 * 根据参数进行查询，不分页。
	 * @param filters
	 * @param formDTO
	 * @return
	 */
	public List<T> searchByParamNoPage(Map<String, LogicSearchFilter> filters ,BaseSearchByPageDTO formDTO , Class<T> clazz);
	
	/**
	 * 根据参数进行查询，分页和排序功能在 formDTO中定义
	 * @param filters
	 * @param formDTO
	 * @return
	 */
	public Page<T> searchByParamByPage(Map<String, LogicSearchFilter> filters ,BaseSearchByPageDTO formDTO , Class<T> clazz);
	
}
