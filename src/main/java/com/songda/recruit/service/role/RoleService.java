package com.songda.recruit.service.role;

import java.util.List;
import java.util.Map;

import com.songda.recruit.base.ReturnParam;
import com.songda.recruit.pojo.BaseRole;
import com.songda.recruit.service.jpa.LogicSearchFilter;

/**
 * 
 * @author winder.yang
 */
public interface RoleService{
	
	public ReturnParam saveRole(BaseRole role);
	
	public BaseRole findOne(String code);

	public List<BaseRole> findAll(Map<String, LogicSearchFilter> filters);
	
}
