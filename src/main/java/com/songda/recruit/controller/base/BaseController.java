package com.songda.recruit.controller.base;

import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.Validate;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.songda.recruit.service.base.ShiroDbRealm.ShiroUser;

public abstract class BaseController{

	protected final String PAGE_SIZE = "40";
	
	public void responseReturnStream(HttpServletResponse response, String result){
		try {
            OutputStream os = response.getOutputStream();
            os.write(result.getBytes("UTF-8"));
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 取得带相同前缀的Request Parameters, copy from spring WebUtils.
	 * 
	 * 返回的结果的Parameter名已去除前缀.
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> getParametersStartingWith(ServletRequest request, String prefix) {
		Validate.notNull(request, "Request must not be null");
		Enumeration paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		if (prefix == null) {
			prefix = "";
		}
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {
					// Do nothing, no values found at all.
				} else if (values.length > 1) {
					params.put(unprefixed, values);
				} else {
					params.put(unprefixed, values[0]);
				}
			}
		}
		return params;
	}

	public boolean isLogin(){
		Subject currentUser = SecurityUtils.getSubject();
		if(null != currentUser){
			if(null !=(ShiroUser) currentUser.getPrincipal()){//如果已经登录，则退出or 跳转到主页 
				return true;
			}
		}
		return false;
	}
	
}
