package com.songda.recruit.controller.admin.role;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.songda.recruit.controller.base.BaseController;
import com.songda.recruit.pojo.BaseRole;
import com.songda.recruit.service.jpa.LogicSearchFilter;
import com.songda.recruit.service.role.RoleService;

/**
 * 角色的Controller.
 * 
 * @author winder.yang
 */
@Controller("com.songda.recruit.controller.admin.role.IndexController")
@RequestMapping(value = "/admin/role")
public class IndexController extends BaseController{
	
	@Autowired RoleService roleService;
	
	/**
	 * 首页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "index")
	public String index(Model model, ServletRequest request) {
		return "admin/role/index";
	}
	
	/**
	 * 查询内容
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "content")
	public String content(Model model, ServletRequest request) {
		Map<String, Object> searchParams = getParametersStartingWith(request, "search_");
		Map<String, LogicSearchFilter> filters = LogicSearchFilter.parse(searchParams);
		List<BaseRole> roleList = roleService.findAll(filters);
		model.addAttribute("roleList", roleList);
		model.addAttribute("searchParams", searchParams);
		return "admin/role/content";
	}
	
}
