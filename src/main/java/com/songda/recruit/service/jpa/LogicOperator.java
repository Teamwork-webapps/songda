package com.songda.recruit.service.jpa;

/**
 * 支持动态查询的查询方式枚举。
 * 支持方法包括：
 * EQ：描述为：等于。操作等同于 =
 * NOTEQ：描述为：不等于。操作等同于 <>
 * LIKE：描述为：模糊匹配。操作等同于 like 注：该条件的内容，将自动匹配前后模糊查询。
 * GT：描述为：大于。操作等同于 >
 * LT：描述为：小于。操作等同于 <
 * GTE：描述为：大于等于。操作等同于 >=
 * LTE：描述为：小于等于。操作等同于 <=
 * ISNOTNULL：描述为：不等于null值。操作等同于 is not null
 * ISNULL：描述为：等于null值。操作等同于 is null
 * ISNOTEMPTY：描述为：不等于空值。操作等同于 <>''
 * ISEMPTY：描述为：等于空值。操作等同于 =''
 * IN：描述为：在列表中。操作等同于 in()
 * GROUPBY：描述为：按列内容分组。操作等同于 group by column
 * DISTINCT描述为：去除重复记录。操作等同于 distinct
 * @author winder.yang
 *
 */
public enum LogicOperator {
	EQ, NOTEQ, LIKE, GT, LT, GTE, LTE, ISNOTNULL, ISNULL, ISNOTEMPTY, ISEMPTY, IN, 
	GROUPBY, DISTINCT
}
