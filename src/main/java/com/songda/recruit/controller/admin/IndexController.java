package com.songda.recruit.controller.admin;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.songda.recruit.controller.base.BaseController;
import com.songda.recruit.service.user.AccountService;

/**
 * 角色的Controller.
 * 
 * @author winder.yang
 */
@Controller("com.songda.recruit.controller.admin.IndexController")
@RequestMapping(value = "/admin")
public class IndexController extends BaseController{
	
	@Autowired AccountService accountService;
	
	/**
	 * 首页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "index")
	public String index(Model model, ServletRequest request) {
		model.addAttribute("user", this.accountService.getCurrentUser());
		return "admin/index";
	}
	
	/**
	 * 首页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "dashboard")
	public String dashboard(Model model, ServletRequest request) {
		return "admin/dashboard";
	}
	
}
