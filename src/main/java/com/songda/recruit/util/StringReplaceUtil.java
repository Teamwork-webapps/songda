package com.songda.recruit.util;

import java.util.ArrayList;
import java.util.List;

public class StringReplaceUtil {

	/**
	 * 字符串替换功能。
	 * 查找字符串中“{}”部分内容，其中的序号，从list中的对应下标获取字符串替换。
	 * @param str
	 * @param argsList
	 * @return
	 */
	public String stringReplace(String str, List<String> argsList){
		if(null != argsList && argsList.size()>0){
			for(int index=0 ; index < argsList.size() ; index++){
				str = str.replaceAll("\\{" + index + "\\}", argsList.get(index));
			}
		}
		return str;
	}
	
	/**
	 * 字符串获取，根据前置和后置字符串，获取中间部分的内容。组成list返回
	 * @param str
	 * @param front
	 * @param behind
	 * @return
	 */
	public List<String> stringGet(String str, String front, String behind){
		String newStr = str;
		List<String> retList = new ArrayList<String>();
		do{
			int fIndex = newStr.indexOf(front);
			int eIndex = newStr.indexOf(behind);
			if( fIndex != -1 && eIndex != -1 && eIndex > fIndex){
				retList.add(newStr.substring(fIndex+1,eIndex));
				newStr = newStr.substring(eIndex+1);
			}else{
				break;
			}
		}while(true);
		return retList;
	}
	
}
