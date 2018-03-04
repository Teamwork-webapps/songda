package com.songda.recruit.service.role.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.songda.recruit.base.ReturnParam;
import com.songda.recruit.dao.BaseRoleDao;
import com.songda.recruit.pojo.BaseRole;
import com.songda.recruit.service.jpa.LogicDynamicSpecifications;
import com.songda.recruit.service.jpa.LogicSearchFilter;
import com.songda.recruit.service.role.RoleService;

/**
 * @author winder.yang
 */
@Component("com.songda.recruit.service.role.impl.RoleServiceImpl")
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService{
	
	@Autowired BaseRoleDao baseRoleDao;
	
	@Transactional(readOnly=false)
	public ReturnParam saveRole(BaseRole role) {
		role = this.baseRoleDao.save(role);
		if(null != role && null != role.getCode()){
			return ReturnParam.setReturnInfo(null, true, "保存成功");
		}
		return ReturnParam.setReturnInfo(null, false, "保存失败");
	}

	public BaseRole findOne(String code) {
		return this.baseRoleDao.findByCode(code);
	}

	public List<BaseRole> findAll(Map<String, LogicSearchFilter> filters) {
		Specification<BaseRole> spec = LogicDynamicSpecifications.bySearchFilter(filters.values(), BaseRole.class);
		return baseRoleDao.findAll(spec);
	}

}
