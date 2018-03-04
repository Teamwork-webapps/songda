package com.songda.recruit.controller.admin.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.songda.recruit.base.BaseSearchByPageDTO;
import com.songda.recruit.base.ReturnParam;
import com.songda.recruit.controller.base.BaseController;
import com.songda.recruit.dto.admin.user.UserEditFormDTO;
import com.songda.recruit.pojo.BaseUser;
import com.songda.recruit.pojo.support.BaseUserStatusEnum;
import com.songda.recruit.service.jpa.LogicSearchFilter;
import com.songda.recruit.service.role.RoleService;
import com.songda.recruit.service.user.UserService;

/**
 * 用户的Controller.
 * 
 * @author winder.yang
 */
@Controller("com.songda.recruit.controller.admin.user.IndexController")
@RequestMapping(value = "/admin/user")
public class IndexController extends BaseController{
	
	@Autowired UserService userService;
	@Autowired RoleService roleService;
	
	/**
	 * 首页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "index")
	public String index(Model model, ServletRequest request) {
		return "admin/user/index";
	}
	
	/**
	 * 查询内容
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "content")
	public String content(@Valid BaseSearchByPageDTO pageDTO,
			Model model, ServletRequest request) {
		Map<String, Object> searchParams = getParametersStartingWith(request, "search_");
		Map<String, LogicSearchFilter> filters = LogicSearchFilter.parse(searchParams);
		Page<BaseUser> userPage = userService.searchByParamByPage(filters, pageDTO, BaseUser.class);
		model.addAttribute("userPage", userPage);
		model.addAttribute("searchParams", searchParams);
		model.addAttribute("pageDTO", pageDTO);
		return "admin/user/content";
	}
	
	/**
	 * 查询单个用户
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "view/{id}")
	public String content(@PathVariable("id") Long id,
			Model model, ServletRequest request) {
		model.addAttribute("user", userService.findOne(id));
		return "admin/user/view";
	}
	
	/**
	 * 查询单个用户
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String edit(@PathVariable("id") Long id,
			Model model, ServletRequest request) {
		model.addAttribute("user", userService.findOne(id));
		model.addAttribute("statusList", BaseUserStatusEnum.values());
		model.addAttribute("roleList", roleService.findAll(new HashMap<String,LogicSearchFilter>()));
		return "admin/user/edit";
	}
	
	/**
	 * 查询单个用户
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public Object save(@Valid UserEditFormDTO dto,
			Model model, ServletRequest request) {
//		model.addAttribute("user", userService.findOne(id));
		return ReturnParam.setReturnInfo(null, true, "保存成功").toMap();
	}
	
}
