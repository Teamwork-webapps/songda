package com.songda.recruit.service.user;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.songda.recruit.dao.BaseRoleDao;
import com.songda.recruit.dao.BaseUserDao;
import com.songda.recruit.pojo.BaseRole;
import com.songda.recruit.pojo.BaseUser;
import com.songda.recruit.service.base.ShiroDbRealm.ShiroUser;
import com.songda.recruit.service.jpa.LogicDynamicSpecifications;
import com.songda.recruit.service.jpa.LogicSearchFilter;
import com.songda.recruit.util.security.Digests;
import com.songda.recruit.util.security.Encodes;

/**
 * 用户管理service类.
 * 
 * @author Gavin.chen
 */
@Component
@Transactional(readOnly = true)
public class AccountService {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;

	private static Logger logger = LoggerFactory.getLogger(AccountService.class);

	BaseUserDao userDao;
	BaseRoleDao roleDao;
	
	public List<BaseUser> getAllUser() {
		return (List<BaseUser>) userDao.findAll();
	}

	public BaseUser getUser(Long id) {
		return userDao.findOne(id);
	}

	public BaseUser findUserByAccount(String account) {
		return userDao.findByAccount(account);
	}
	
	@Transactional(readOnly = false)
	public BaseUser registerUser(BaseUser user) throws Exception {
		entryptPassword(user);
		return userDao.save(user);
	}

	@Transactional(readOnly = false)
	public BaseUser updateUser(BaseUser user) throws Exception {
		if (StringUtils.isNotBlank(user.getPlainPassword())) {
			entryptPassword(user);
		}
		return userDao.save(user);
	}

	@Transactional(readOnly = false)
	public String deleteUser(Long id) {
		if (!isSupervisor()) { //如果其不具备admin角色
			logger.info("操作员{}尝试删除用户，拒绝其操作!", getCurrentUserName());
			return "您没有权限删除用户!";
		}
		userDao.delete(id);
		return "ok";
	}

	public List<BaseUser> getUsersByParams(Long userId, Map<String, Object> filterParams ) {
		Specification<BaseUser> spec = buildSpecification(userId, filterParams);
		return userDao.findAll(spec);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<BaseUser> buildSpecification(Long userId, Map<String, Object> filterParams) {
		Map<String, LogicSearchFilter> filters = LogicSearchFilter.parse(filterParams);
		Specification<BaseUser> spec = LogicDynamicSpecifications.bySearchFilter(filters.values(), BaseUser.class);
		return spec;
	}

	//--------------------//
	//   Role Management  //
	//--------------------//

	public List<BaseRole> getAllRole() {
		return (List<BaseRole>) roleDao.findAll();
	}
	
	/**
	 * 判断是否拥有管理员权限.
	 */
	private boolean isSupervisor() {
		Boolean tag= false;
		for(BaseRole role : userDao.findByAccount(getCurrentUserName()).getRoles()){
			if(role.getCode().contains("admin")){
				tag = true;
				break;
			}
		}
		return tag;
	}
	
	/**
	 * 更新Shiro中当前用户的用户名.
	 */
	public void updateCurrentUserName(String userName) {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		user.name = userName;
	}
	
	/**
	 * 取出Shiro中的当前用户LoginName.
	 */
	public ShiroUser getCurrentShiroUser() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user;
	}
	
	/**
	 * 取出Shiro中的当前用户LoginName.
	 */
	public String getCurrentUserName() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.loginName;
	}
	
	/**
	 * 取出Shiro中的当前用户id.
	 */
	public Long getCurrentUserId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if(null != user){
			return user.id;
		}
		return null;
	}
	
	/**
	 * 取出Shiro中的当前用户,返回系统的用户对象
	 */
	public BaseUser getCurrentUser() {
		Long userId = this.getCurrentUserId();
		if(null == userId){
			return null;
		}else{
			return this.userDao.findOne(userId);
		}
	}
	
	/**
	 * 判断当前用户，是否有相应的roleCode权限
	 * @param roleCode
	 * @return
	 */
	public boolean hasRole(String roleCode){
		return SecurityUtils.getSubject().hasRole(roleCode);
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 * @throws Exception 
	 */
	private void entryptPassword(BaseUser user) throws Exception {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}
	
	@Autowired
	public void setBaseUserDao(BaseUserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setBaseRoleDao(BaseRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public BaseUserDao getUserDao() {
		return userDao;
	}

	public BaseRoleDao getRoleDao() {
		return roleDao;
	}
	
}
