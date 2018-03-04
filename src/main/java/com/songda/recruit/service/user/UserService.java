package com.songda.recruit.service.user;

import java.util.List;

import com.songda.recruit.base.ReturnParam;
import com.songda.recruit.pojo.BaseUser;
import com.songda.recruit.service.base.BaseSearchService;

/**
 * 售后管理的订单查询 
 * 
 * @author winder.yang
 */
public interface UserService extends BaseSearchService<BaseUser>{
	
	public List<BaseUser> getUsersByRoleCode(List<String> roleCodeList);
	
	public ReturnParam saveUser(BaseUser user);
	
	public ReturnParam saveLogicDelete(long companyLinkerId);
	
}
