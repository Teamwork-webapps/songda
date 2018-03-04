package com.songda.recruit.service.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.songda.recruit.base.BaseSearchByPageDTO;
import com.songda.recruit.dao.base.BaseDao;
import com.songda.recruit.service.jpa.LogicDynamicSpecifications;
import com.songda.recruit.service.jpa.LogicOperator;
import com.songda.recruit.service.jpa.LogicSearchFilter;
import com.songda.recruit.util.PageUtil;

public abstract class BaseSearchServiceImpl<T> implements BaseSearchService<T> {

	public abstract BaseDao<T> getDao();
	
	public T findOne(Object id){
		return this.getDao().findOne(id);
	}
	
	public List<T> findAllUnDel(Class<T> clazz){
		Map<String, LogicSearchFilter> filters = new HashMap<String, LogicSearchFilter>();
		filters.put("delflag", new LogicSearchFilter("delflag", LogicOperator.EQ, false, true));
		Specification<T> spec = LogicDynamicSpecifications.bySearchFilter(filters.values(), clazz);
		BaseSearchByPageDTO sortDTO = new BaseSearchByPageDTO();
		sortDTO.setOrderType("desc");
		sortDTO.setSortCol("id");
		Sort sort = PageUtil.buildSort(sortDTO);
		return this.getDao().findAll(spec, sort);
	}
	
	/**
	 * 根据参数进行查询，获得符合条件的行数
	 * @param filters
	 * @return
	 */
	public Long getCountByParam(Map<String, LogicSearchFilter> filters , Class<T> clazz){
		Specification<T> spec = LogicDynamicSpecifications.bySearchFilter(filters.values(), clazz);
		return this.getDao().count(spec);
	}
	
	/**
	 * 根据参数进行查询，不分页。
	 * @param filters
	 * @param formDTO
	 * @return
	 */
	public List<T> searchByParamNoPage(Map<String, LogicSearchFilter> filters ,BaseSearchByPageDTO formDTO , Class<T> clazz){
		if(null == formDTO){
			formDTO = new BaseSearchByPageDTO();
		}
		Sort sort = PageUtil.buildSort(formDTO);
		Specification<T> spec = LogicDynamicSpecifications.bySearchFilter(filters.values(), clazz);
		return this.getDao().findAll(spec,sort);
	}
	
	/**
	 * 根据参数进行查询，分页和排序功能在 formDTO中定义
	 * @param filters
	 * @param formDTO
	 * @return
	 */
	public Page<T> searchByParamByPage(Map<String, LogicSearchFilter> filters ,BaseSearchByPageDTO formDTO , Class<T> clazz){
		PageRequest pageRequest = PageUtil.buildPageRequest(formDTO);
		Specification<T> spec = LogicDynamicSpecifications.bySearchFilter(filters.values(), clazz);
		return this.getDao().findAll(spec,pageRequest);
	}
	
}
