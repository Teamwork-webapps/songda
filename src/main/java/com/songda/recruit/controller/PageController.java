package com.songda.recruit.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.songda.recruit.service.base.ShiroDbRealm.ShiroUser;
import com.songda.recruit.service.user.AccountService;

/**
 * 分配页面跳转
 * 
 * @author gavin.chen
 */
@Controller
@RequestMapping(value = "/page")
public class PageController {

	@Autowired private AccountService accountService;
	
	@RequestMapping(method = RequestMethod.GET)
	@RequiresAuthentication
	public String page(Model model) {
		ShiroUser user = accountService.getCurrentShiroUser();
		if(null == user){
			return "login";
		}
		if(null != user.getRoles() && user.getRoles().size()>0){
			return "redirect:/admin/index";
		}
		return "login";
	}

}
