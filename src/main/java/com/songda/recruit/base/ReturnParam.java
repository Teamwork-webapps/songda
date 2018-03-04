package com.songda.recruit.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

/**
 * 返回信息封装类
 * @author winder.yang
 *
 */
public class ReturnParam {

	private String result;
	private String msg;
	private Map<String,Object> content;
	
	public Map<String,Object> toMap(){
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("result", result);
		retMap.put("msg", msg);
		retMap.put("content", content);
		return retMap;
	}
	
	/**
	 * 检查对象中返回的结果，是否是成功。
	 * @return
	 */
	public boolean checkIsSuccess(){
		if( StringUtils.isNotBlank(this.result) && "T".equals(this.result) ){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取返回的map对象中，默认给予的消息参数
	 * @return
	 */
	public String getMessage(){
		return this.msg;
	}
	
	public Map<String,Object> getContent(){
		return this.content;
	}
	
	/**
	 * 获取范围的map对象中，指定的消息返回参数
	 * @param map
	 * @param contentName
	 * @return
	 */
	public Object getContent(String contentName){
		if(StringUtils.isNotBlank(contentName) && !CollectionUtils.isEmpty(content)){
			return content.get(contentName);
		}
		return null;
	}
	
	/**
	 * 设定操作成功与否，返回对象
	 * @param isSuccess
	 * @return
	 */
	public ReturnParam setIsSuccess(boolean isSuccess){
		this.result = (isSuccess?"T":"F");
		return this;
	}
	
	/**
	 * 设定操作的信息，返回对象
	 * @param message
	 * @return
	 */
	public ReturnParam setMessage(String message){
		this.msg = message;
		return this;
	}
	
	/**
	 * 设置成功与否以及操作信息
	 * @param isSuccess
	 * @param message
	 * @return
	 */
	public ReturnParam setReturnInfo(boolean isSuccess, String message){
		return this.setIsSuccess(isSuccess).setMessage(message);
	}
	
	/**
	 * 设置相关信息到对象
	 * 如果contentName已经存在，则将覆盖原来的值
	 * @param contentName
	 * @param content
	 * @return
	 */
	public ReturnParam setContent(String contentName, Object content){
		if(null == this.content) this.content = new HashMap<String,Object>();
		this.content.put(contentName, content);
		return this;
	}
	
	/**
	 * 设置相关信息到对象
	 * @param contentNames
	 * @param contents
	 * @return
	 * @throws Exception
	 */
	public ReturnParam setContents(Map<String,Object> contents){
		if(null == this.content) this.content = new HashMap<String,Object>();
		this.content = contents;
		return this;
	}
	
	/**
	 * 判断content中是否已经包含相应的key
	 * 如不存在，则返回false
	 * @param key
	 * @return
	 */
	public boolean contentHasKey(String key){
		if(null == this.content) return false;
		return this.content.containsKey(key);
	}
	
	/**
	 * 获取content中key对应的值
	 * 如果没有该key值，则返回null
	 * @param key
	 * @return
	 */
	public Object getContentValue(String key){
		if(null == this.content) return null;
		return this.content.get(key);
	}
	
	
	//******************************
	//****以下为静态方法，供通用调用。****
	//******************************
	
	
	/**
	 * 检查返回的map对象，显示操作是否成功
	 * @param returnParam
	 * @return
	 */
	public static boolean checkIsSuccess(ReturnParam returnParam){
		if(null == returnParam) return false;
		return returnParam.checkIsSuccess();
	}
	
	/**
	 * 获取范围的map对象中，指定的消息返回参数
	 * @param map
	 * @param contentName
	 * @return
	 */
	public static Object getContent(ReturnParam returnParam , String contentName){
		if(null == returnParam) return false;
		return returnParam.getContent(contentName);
	}
	
	/**
	 * 给予指定的对象，设定对象操作成功与否
	 * @param returnParam
	 * @param isSuccess
	 * @return
	 */
	public static ReturnParam setIsSuccess(ReturnParam returnParam, boolean isSuccess){
		if(null == returnParam) returnParam = new ReturnParam();
		return returnParam.setIsSuccess(isSuccess);
	}
	
	/**
	 * 给予指定的对象，设定对象操作的信息
	 * @param returnMap
	 * @param message
	 * @return
	 */
	public static ReturnParam setMessage(ReturnParam returnParam, String message){
		if(null == returnParam) returnParam = new ReturnParam();
		return returnParam.setMessage(message);
	}
	
	/**
	 * 给予指定的对象，设置成功与否以及操作信息
	 * @param returnMap
	 * @param isSuccess
	 * @param message
	 * @return
	 */
	public static ReturnParam setReturnInfo(ReturnParam returnParam, boolean isSuccess, String message){
		if(null == returnParam) returnParam = new ReturnParam();
		return returnParam.setReturnInfo(isSuccess, message);
	}

	
	
	/**
	 * 设置相关信息到对象
	 * 如果contentName已经存在，则将覆盖原来的值
	 * @param contentName
	 * @param content
	 * @return
	 */
	public static ReturnParam setContent(ReturnParam returnParam, String contentName, Object content) throws NullPointerException{
		if(null == returnParam) throw new NullPointerException("ReturnParam object is null");
		return returnParam.setContent(contentName, content);
	}
	
	/**
	 * 设置相关信息到对象
	 * @param contentNames
	 * @param contents
	 * @return
	 * @throws Exception
	 */
	public static ReturnParam setContents(ReturnParam returnParam, Map<String,Object> contents){
		if(null == returnParam) returnParam = new ReturnParam();
		return returnParam.setContents(contents);
	}
	
	/**
	 * 判断content中是否已经包含相应的key
	 * 如不存在，则返回false
	 * @param key
	 * @return
	 */
	public static boolean contentHasKey(ReturnParam returnParam, String key) throws NullPointerException{
		if(null == returnParam) throw new NullPointerException("ReturnParam object is null");
		return returnParam.contentHasKey(key);
	}
	
	/**
	 * 获取content中key对应的值
	 * 如果没有该key值，则返回null
	 * @param key
	 * @return
	 */
	public static Object getContentValue(ReturnParam returnParam, String key) throws NullPointerException{
		if(null == returnParam) throw new NullPointerException("ReturnParam object is null");
		return returnParam.getContentValue(key);
	}
	
	
}
