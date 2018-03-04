package com.songda.recruit.controller;

import javax.servlet.ServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.songda.recruit.base.ReturnParam;
import com.songda.recruit.controller.base.BaseController;
import com.songda.recruit.service.base.ShiroDbRealm.ShiroUser;
import com.songda.recruit.service.user.AccountService;

/**
 * 角色的Controller.
 * 
 * @author winder.yang
 */
@Controller("com.songda.recruit.controller.LoginController")
@RequestMapping(value = "")
public class LoginController extends BaseController{
	
	@Autowired AccountService accountService;
	
	/**
	 * 登陆页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "login")
	public String login(Model model, ServletRequest request) {
		Subject currentUser = SecurityUtils.getSubject();
		if(null != currentUser){
			if(null !=(ShiroUser) currentUser.getPrincipal()){//如果已经登录，则退出or 跳转到主页 
				currentUser.logout();  	//安全起见，用这种，退出再登录
			}
		}
		return "login";
	}
	
	/**
	 * 登陆页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "userLogin")
	@ResponseBody
	public Object userLogin(Model model, ServletRequest request) {
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(request.getParameter("loginName"), request.getParameter("password"));
			Subject currentUser = SecurityUtils.getSubject();
			if (!currentUser.isAuthenticated()) {
				// 使用shiro来验证
				token.setRememberMe(true);
				currentUser.login(token);// 验证角色和权限
				return ReturnParam.setReturnInfo(null, true, "登陆成功").toMap();
			}
			return ReturnParam.setReturnInfo(null, false, "登陆失败").toMap();
		} catch (Exception ex) {
			ex.printStackTrace();
			return ReturnParam.setReturnInfo(null, false, "登陆失败：异常原因").toMap();
		}
	}
	
}
