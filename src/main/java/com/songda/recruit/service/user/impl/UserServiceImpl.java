package com.songda.recruit.service.user.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.songda.recruit.base.BaseSearchByPageDTO;
import com.songda.recruit.base.ReturnParam;
import com.songda.recruit.dao.BaseUserDao;
import com.songda.recruit.dao.base.BaseDao;
import com.songda.recruit.pojo.BaseUser;
import com.songda.recruit.service.base.BaseSearchService;
import com.songda.recruit.service.base.BaseSearchServiceImpl;
import com.songda.recruit.service.jpa.LogicOperator;
import com.songda.recruit.service.jpa.LogicSearchFilter;
import com.songda.recruit.service.user.AccountService;
import com.songda.recruit.service.user.UserService;

/**
 * 售后管理的订单查询 
 * 
 * @author winder.yang
 */
@Component("com.songda.recruit.service.user.impl.UserService")
@Transactional(readOnly = true)
public class UserServiceImpl extends BaseSearchServiceImpl<BaseUser> implements UserService,BaseSearchService<BaseUser>{
	
	@Autowired BaseUserDao baseUserDao;
	@Autowired AccountService accountService;
	
	public List<BaseUser> getUsersByRoleCode(List<String> roleCodeList){
		if(null != roleCodeList && roleCodeList.size()>0){
			Map<String, LogicSearchFilter> filters = LogicSearchFilter.parse(new HashMap<String, Object>());
			BaseSearchByPageDTO formDTO = new BaseSearchByPageDTO();
			formDTO.setOrderType("desc");
			formDTO.setSortCol("createTime");
			int index = 0;
			for(String roleCode : roleCodeList){
				filters.put("roles.code" + index, new LogicSearchFilter("roles.code", LogicOperator.EQ, roleCode, false));
				index++;
			}
			return this.searchByParamNoPage(filters, formDTO, BaseUser.class);
		}
		return null;
	}
	
	@Transactional(readOnly=false)
	public ReturnParam saveUser(BaseUser user){
		user = this.baseUserDao.save(user);
		if(null != user && null != user.getId()){
			return ReturnParam.setReturnInfo(null, true, "保存成功");
		}
		return ReturnParam.setReturnInfo(null, false, "保存失败");
	}
	
	/**
	 * 逻辑删除记录
	 */
	@Transactional(readOnly=false)
	public ReturnParam saveLogicDelete(long userId){
		BaseUser bu = this.baseUserDao.findOne(userId);
		bu.setDelflag(true);
		bu.setUpdateBy(accountService.getCurrentUserId());
		bu.setUpdateTime(new Date());
		return this.saveUser(bu);
	}
	
	@Override
	public BaseDao<BaseUser> getDao() {
		return baseUserDao;
	}
	
}
