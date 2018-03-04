package com.songda.recruit.controller.base;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class ControllerParamSupport {

	/**
	 * 组合Parameters生成Query String的Parameter部分,并在paramter name上加上prefix.
	 * 
	 */
	public static String encodeParameterStringWithPrefix(Map<String, Object> params, String prefix) {
		if (params == null || params.size() == 0) {
			return "";
		}

		if (prefix == null) {
			prefix = "";
		}

		StringBuilder queryStringBuilder = new StringBuilder();
		Iterator<Entry<String, Object>> it = params.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			queryStringBuilder.append(prefix).append(entry.getKey()).append('=').append(entry.getValue());
			if (it.hasNext()) {
				queryStringBuilder.append('&');
			}
		}
		return queryStringBuilder.toString();
	}
	
	/**
	 * 组合Parameters生成Query String的Parameter部分,并在paramter name上加上prefix.
	 * 另外追加unPrefixParams，用于特殊用处，如pageNum等，无需前缀的参数
	 */
	public static String encodeParameterStringWithPrefix(Map<String, Object> params, String prefix, Map<String, Object> unPrefixParams ) {
		if (params == null || params.size() == 0) return "";
		if (prefix == null) prefix = "";

		StringBuilder queryStringBuilder = new StringBuilder();
		Iterator<Entry<String, Object>> it = params.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			queryStringBuilder.append(prefix).append(entry.getKey()).append('=').append(entry.getValue());
			queryStringBuilder.append('&');
		}
		Iterator<Entry<String, Object>> unit = unPrefixParams.entrySet().iterator();
		while (unit.hasNext()) {
			Entry<String, Object> entry = unit.next();
			queryStringBuilder.append(entry.getKey()).append('=').append(entry.getValue());
			if (unit.hasNext()) {
				queryStringBuilder.append('&');
			}
		}
		return queryStringBuilder.toString();
	}
	
}
