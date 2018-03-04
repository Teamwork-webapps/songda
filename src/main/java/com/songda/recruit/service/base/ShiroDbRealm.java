package com.songda.recruit.service.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.songda.recruit.pojo.BaseRole;
import com.songda.recruit.pojo.BaseUser;
import com.songda.recruit.pojo.support.BaseUserStatusEnum;
import com.songda.recruit.service.user.AccountService;
import com.songda.recruit.util.security.Encodes;

public class ShiroDbRealm extends AuthorizingRealm {

	AccountService accountService;

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		try{
			BaseUser user = accountService.findUserByAccount(token.getUsername());
			if (user != null && user.getStatus().equals(BaseUserStatusEnum.正常)) {
				byte[] salt;
				try {
					salt = Encodes.decodeHex(user.getSalt());
				} catch (Exception e) {
					e.printStackTrace();
					throw new AuthenticationException("encode field!");
				}
				List<String> roles = new ArrayList<String>();
				if(null != user.getRoles() && user.getRoles().size()>0){
					for(BaseRole role : user.getRoles()){
						roles.add(role.getCode());
					}
				}
				return new SimpleAuthenticationInfo(
						new ShiroUser(user.getId(), user.getAccount(),user.getAccount(), roles), 
						user.getPassword(), ByteSource.Util.bytes(salt), getName());
			} else {
				throw new UnknownAccountException("无用户信息!");
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new UnknownAccountException("用户信息获取失败!");
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRoles(shiroUser.getRoles());
		return info;
	}

	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(
				AccountService.HASH_ALGORITHM);
		matcher.setHashIterations(AccountService.HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}

	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroUser implements Serializable {
		private static final long serialVersionUID = -1373760761780840081L;
		public Long id;
		public String loginName;
		public String name;
		public List<String> roles = new ArrayList<String>();

		public ShiroUser(Long id, String loginName, String name, List<String> roles) {
			this.id = id;
			this.loginName = loginName;
			this.name = name;
			this.roles = roles;
		}

		public String getName() {
			return name;
		}
		
		public List<String> getRoles(){
			return roles;
		}

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return loginName;
		}

		/**
		 * 重载equals,只计算loginName;
		 */
		@Override
		public int hashCode() {
			return HashCodeBuilder.reflectionHashCode(this, "loginName");
		}

		/**
		 * 重载equals,只比较loginName
		 */
		@Override
		public boolean equals(Object obj) {
			return EqualsBuilder.reflectionEquals(this, obj, "loginName");
		}

	}

	public AccountService getAccountService() {
		return accountService;
	}

	@Autowired 
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

}
