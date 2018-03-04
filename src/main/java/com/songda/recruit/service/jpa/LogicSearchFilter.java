package com.songda.recruit.service.jpa;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

/**
 * 查询过滤器
 * 支持查询条件中与、或两种情况
 * 
 * @author winder.yang
 *
 */
public class LogicSearchFilter {

	public String fieldName;
	public Object value;
	public LogicOperator operator;
	public boolean isAnd;

	/**
	 * 创建动态查询对象的初始化方法，必须传递参数。
	 * @see shiningon.top.crm.util.support.service.LogicOperator
	 * @param fieldName 查询的字段(列)名。
	 * @param operator	查询的操作，详见：LogicOperator 类
	 * @param value 所需查询的值。
	 * @param isAnd 是否用and连接下一个条件，true为and连接，false为or连接。
	 */
	public LogicSearchFilter(String fieldName, LogicOperator operator, Object value, boolean isAnd) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
		this.isAnd = isAnd;
	}
	
	/**
	 * 静态方法，创建动态查询条件，根据map对象进行创建查询条件。
	 * map－》key ＝ 判断方式_字段名_连接方式。如key中没有“_”则该条件不做处理，
	 * 	如只有一个，则被解析为：判断方式_字段名_and 默认连接方式为and连接。
	 * 	如有两个，则被解析为：判断方式_字段名_连接方式。
	 *  如有超过两个，则不做处理。
	 * 	支持的判断方式，详见：LogicOperator 类
	 * map－》val ＝ 判断时用的值，如果判断无需参数，则该值不被使用。如果该判断需要多个参数，则将按照“,”进行切割，组成参数列表。
	 * 辅助设置：字段名加入前缀：date-，则该字段将会作为日期型进行解析和组装查询，否则默认按照字符串处理。
	 * 辅助设置：字段名加入前缀：bool-，则该字段将会作为Boolean型进行解析和组装查询，否则默认按照字符串处理。
	 * 辅助设置：字段名加入前缀：GPX-，则该字段将会作为分组方式进行组合，X为任意数字序号，用于区分分组的内容。分组中，支持不同列名组合。
	 * 	分组后格式为：(第一个GP1的列 and/or 第二个GP1的列) and (第一个GP2的列 and/or 第二个GP2的列) ......
	 * @see shiningon.top.crm.util.support.service.LogicOperator
	 * @param searchParams
	 * @return
	 */
	public static Map<String, LogicSearchFilter> parse(Map<String, Object> searchParams) {
		Map<String, LogicSearchFilter> filters = new HashMap<String, LogicSearchFilter>();

		for (Entry<String, Object> entry : searchParams.entrySet()) {
			// 过滤掉空值
			String key = entry.getKey();
			Object value = entry.getValue();
			//不为BOOLEAN 的，判断为空验证
			if(value instanceof Boolean){
			}else if(value instanceof Collection){	//如果是集合，不判断为空
			}else{
				if (StringUtils.isBlank((String) value)) {
					continue;
				}
			}

			// 拆分operator与filedAttribute
			String[] names = StringUtils.split(key, "_");
			if (names.length != 2 && names.length != 3) {
				throw new IllegalArgumentException(key + " is not a valid search filter name");
			}
			String filedName = names[1];
			String[] filedInfo = StringUtils.split(filedName, "-");
			LogicOperator operator = LogicOperator.valueOf(names[0]);
			if(filedInfo.length > 1){
				int filedIndex = 0;
				//如果有group分组的话，则长度为三位。在之后判断类型的时候，要从下一位开始取滤掉。
				if(filedInfo.length == 3){
					filedIndex++;
				}
				//date 类型的转换
				if( "date".equalsIgnoreCase(filedInfo[filedIndex]) ){
					filedName = filedName.replaceAll("date-", "");
					try {
						if(value.toString().length()==10){
							value = value.toString()+"T00:00:00";
							if(operator.equals(LogicOperator.LT) || operator.equals(LogicOperator.LTE)){
								value = new DateTime(value.toString()).plusDays(1).toDate();
							}else{
								value = new DateTime(value.toString()).toDate();
							}
						}else{
							value = StringUtils.replace(StringUtils.trim(value.toString()), " ", "T");
							value = new DateTime(value.toString()).toDate();
						}
					} catch (IllegalArgumentException e) {
						throw new IllegalArgumentException(key + " value=" + value + " can't format to date");
					}
				}
				//bool 类型的转换
				if( "bool".equalsIgnoreCase(filedInfo[filedIndex]) ){
					filedName = filedName.replaceAll("bool-", "");
					try {
						if("1".equals(value.toString()) || "t".equalsIgnoreCase(value.toString()) ||
								"true".equalsIgnoreCase(value.toString())){
							value = new Boolean(true);
						}else if("0".equals(value.toString()) || "f".equalsIgnoreCase(value.toString()) ||
								"false".equalsIgnoreCase(value.toString())){
							value = new Boolean(false);
						}else{
							value = new Boolean(value.toString());
						}
					} catch (IllegalArgumentException e) {
						throw new IllegalArgumentException(key + " value=" + value + " can't format to bool");
					}
				}
			}
			
			// 创建searchFilter
			LogicSearchFilter filter = null;
			if(names.length == 3 && "or".equalsIgnoreCase(names[2])){
				filter = new LogicSearchFilter(filedName, operator, value,false);
			}else{
				filter = new LogicSearchFilter(filedName, operator, value,true);
			}
			filters.put(key, filter);
		}

		return filters;
	}
}
