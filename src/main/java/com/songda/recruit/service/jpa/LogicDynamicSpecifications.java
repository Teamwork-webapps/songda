package com.songda.recruit.service.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.songda.recruit.util.DateUtil;

/**
 * 动态组装查询过滤器
 * @author winder.yang
 */
public class LogicDynamicSpecifications {
	/**
	 * 静态方法，创建动态组装查询条件，支持与、或两种操作情况
	 * @author winder.yang
	 * edit by winder.yang 2014-04-09
	 * 修复关联问题中，M表对象关联A对象的条件(M.A)，与关联B对象关联的A对象的条件(M.B.A)，产生的SQL语句错误。
	 * edit by winder.yang 2014-04-11
	 * 增加group by操作符号，但是不能用于分页操作，因为分页操作会执行count语句，造成count结果异常。
	 * 使用案例：filters.put(key,new LogicSearchFilter(字段名, Operator.GROUPBY, "", true));
	 * edit by winder.yang 2014-04-11
	 * 增加distinct操作符号。
	 * 使用案例：filters.put(key,new LogicSearchFilter(""[空字符串], Operator.DISTINCT, "", true));
	 * @param filters 由 LogicSearchFilter 对象组成的集合。
	 * @param clazz 操作的对象
	 * @return 用于使用data-jpa的动态查询所需对象。
	 */
	public static <T> Specification<T> bySearchFilter(final Collection<LogicSearchFilter> filters, final Class<T> clazz) {
		return LogicDynamicSpecifications.bySearchFilter(filters, clazz, ",");
	}
	
	/**
	 * 静态方法，创建动态组装查询条件，支持与、或两种操作情况
	 * @author winder.yang
	 * edit by winder.yang 2014-04-09
	 * 修复关联问题中，M表对象关联A对象的条件(M.A)，与关联B对象关联的A对象的条件(M.B.A)，产生的SQL语句错误。
	 * edit by winder.yang 2014-04-11
	 * 增加group by操作符号，但是不能用于分页操作，因为分页操作会执行count语句，造成count结果异常。
	 * 使用案例：filters.put(key,new LogicSearchFilter(字段名, Operator.GROUPBY, "", true));
	 * edit by winder.yang 2014-04-11
	 * 增加distinct操作符号。
	 * 使用案例：filters.put(key,new LogicSearchFilter(""[空字符串], Operator.DISTINCT, "", true));
	 * @param filters 由 LogicSearchFilter 对象组成的集合。
	 * @param clazz 操作的对象
	 * @param splitChar 对于参数需要作为列表的的属性，根据该字符进行切割，并组装进入条件中。如无列表性的查询，可调用本类同名，无该参数的函数。
	 * @return 用于使用data-jpa的动态查询所需对象。
	 */
	public static <T> Specification<T> bySearchFilter(final Collection<LogicSearchFilter> filters, final Class<T> clazz, final String splitChar) {
		return new Specification<T>() {
			@SuppressWarnings({ "rawtypes" })
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				if (filters != null && filters.size()>0) {
					//将条件进行分组的map对象，
					Map<String , List<Predicate>> predicateMap = new HashMap<String , List<Predicate>>();
					//用于存放join过的对象，避免重复join
					Map<String,Object> joinTable = new HashMap<String,Object>();
					for (LogicSearchFilter filter : filters) {
						String key = filter.fieldName + "." + (filter.isAnd?"and":"or");
						if(this.isGroupFilter(filter)){
							key = this.getGroupFilterName(filter) + "." + (filter.isAnd?"and":"or");
						}
						//group by有字段，而distinct无字段，所有distinct不处理该步骤
						Path expression = null;
						if(filter.operator != LogicOperator.DISTINCT){
							expression = this.buildJoinTypeByFilter(root,filter,joinTable);
						}
						
						//组装每个条件
						Predicate predicate = this.buildPredicate(builder,query,expression,filter);
						//group by 和 distinct 操作，不放入map中
						if(filter.operator != LogicOperator.GROUPBY && filter.operator != LogicOperator.DISTINCT){
							//判断相同的字段名，进行分组。
							//如果有重名的，一般为分组条件，包括在一个括号中
							predicateMap = this.addToPredicateMap(key, predicate, predicateMap);
						}
					}
					
					List<Predicate> predicateList = new ArrayList<Predicate>();
					//根据生成的map，生成相应的分组
					Set<String> keySet = predicateMap.keySet();
					for(String str : keySet){
						//如果是分组，则截取判断是and还是or
						String[] repeats = StringUtils.split(str, ".");
						if( "and".equals(repeats[repeats.length-1]) ){
							this.getPredicateByKeyFromMap(builder,str ,predicateMap,predicateList,true);
							continue;
						}
						if( "or".equals(repeats[repeats.length-1]) ){
							this.getPredicateByKeyFromMap(builder,str ,predicateMap,predicateList,false);
							continue;
						}
					}
					return builder.and(predicateList.toArray(new Predicate[predicateList.size()]));
				}
				//取交集
				return builder.conjunction();
			}
			
			/**
			 * 判断当前filter是否是分组的
			 * @param filter
			 * @return
			 */
			private boolean isGroupFilter(LogicSearchFilter filter){
				if(filter.fieldName.startsWith("GP")){
					String[] fieldArr = StringUtils.split( filter.fieldName ,"-");
					if(ArrayUtils.isNotEmpty(fieldArr) && fieldArr.length>1){
						return true;
					}
				}
				return false;
			}
			
			/**
			 * 获取当前filter的分组名，即横杠前的部分包括GP关键字
			 * @param filter
			 * @return
			 */
			private String getGroupFilterName(LogicSearchFilter filter){
				if(filter.fieldName.startsWith("GP")){
					String[] fieldArr = StringUtils.split( filter.fieldName ,"-");
					if(ArrayUtils.isNotEmpty(fieldArr) && fieldArr.length>1){
						return fieldArr[0];
					}
				}
				return null;
			}
			
			/**
			 * 获取当前filter除去分组名部分的内容，即横杠后面的字符串
			 * @param filter
			 * @return
			 */
			private String getWithOutGroupFilterName(LogicSearchFilter filter){
				if(filter.fieldName.startsWith("GP")){
					String[] fieldArr = StringUtils.split( filter.fieldName ,"-");
					if(ArrayUtils.isNotEmpty(fieldArr) && fieldArr.length>1){
						return fieldArr[1];
					}
				}
				return filter.fieldName;
			}
			
			/**
			 * 添加predicate对象到map的key中，如果有，则追加，如果没有就新建一个，放入
			 * @param key
			 * @param predicate
			 * @param predicateMap
			 * @return
			 */
			private Map<String , List<Predicate>> addToPredicateMap(String key,Predicate predicate, Map<String , List<Predicate>> predicateMap){
				List<Predicate> list = predicateMap.get(key);
				if( null == list) list = new ArrayList<Predicate>();
				list.add(predicate);
				predicateMap.put(key,list);
				return predicateMap;
			}
			
			/**
			 * 根据给予的filter对象中的fieldName，创建join关联关系。
			 * @param root
			 * @param filter
			 * @param joinTable
			 * @return
			 */
			@SuppressWarnings("rawtypes")
			private Path buildJoinTypeByFilter(Root<T> root, LogicSearchFilter filter, Map<String,Object> joinTable){
				//根据每个参数进行封装
				String[] names = StringUtils.split(this.getWithOutGroupFilterName(filter), ".");
				Path expression = root.get(names[0]);
				//用于组装级联查询时的join对象
				Join join = null;
				SetJoin setJoin = null;
				//记录级联join的类型，逐步级联时，获取前一个级联的
				String joinType = "none";//{"none","join","setJoin"}
				StringBuffer namePath = new StringBuffer("");
				for (int i = 1; i < names.length; i++) {
					namePath.append(names[i-1]);
					//判断上一层是否是集合类型
					String[] type = StringUtils.split(expression.getJavaType().getName(),".");
					//如果超过两层级联，则需要join。
					//none属性，在当前属性字段，为第一次进入。之后会被改为join或setjoin
					if( "none".equals(joinType) ){
						//如果是set序列对象，则进行setJoin操作
						if("Set".equalsIgnoreCase(type[type.length-1])){
							//判断是不是已经生成过join对象，如果没有生成，则需要生成后放入map中，否则拿出来直接用
							setJoin = getSetJoin(namePath.toString(),names[i-1],root,joinTable,JoinType.LEFT);
							joinType = "setJoin";
						}else{	//如果不是set序列对象，则进行join操作
							//判断是不是已经生成过join对象，如果没有生成，则需要生成后放入map中，否则拿出来直接用
							join = getJoin(namePath.toString(),names[i-1],root,joinTable,JoinType.LEFT);
							joinType = "join";
						}
					}else if( "join".equals(joinType) ){
						//判断上一个属性对象的类的类型
						type = StringUtils.split( join.getJavaType().getName() ,".");
						//如果是set序列对象，则进行setJoin操作
						if("Set".equalsIgnoreCase(type[type.length-1])){
							if("setJoin".equals(joinType)){
								setJoin = this.getSetJoinByNameOrSetJoin(namePath.toString(),joinTable, names[i-1], setJoin,JoinType.LEFT);
							}else{
								setJoin = this.getSetJoinByNameOrJoin(namePath.toString(),joinTable, names[i-1], join,JoinType.LEFT);
							}
							joinType = "setJoin";
						}else{	//如果不是set序列对象，则进行join操作
							if("setJoin".equals(joinType)){
								join = this.getJoinByNameOrSetJoin(namePath.toString(),joinTable,names[i-1],setJoin,JoinType.LEFT);
							}else{
								join = this.getJoinByNameOrJoin(namePath.toString(),joinTable,names[i-1],join,JoinType.LEFT);
							}
							joinType = "join";
						}
					}else if( "setJoin".equals(joinType) ){
						//判断上一个属性对象的类的类型
						type = StringUtils.split( setJoin.getJavaType().getName() ,".");
						//如果是set序列对象，则进行setJoin操作
						if("Set".equalsIgnoreCase(type[type.length-1])){
							if("setJoin".equals(joinType)){
								setJoin = this.getSetJoinByNameOrSetJoin(namePath.toString(),joinTable, names[i-1], setJoin,JoinType.LEFT);
							}else{
								setJoin = this.getSetJoinByNameOrJoin(namePath.toString(),joinTable, names[i-1], join,JoinType.LEFT);
							}
							joinType = "setJoin";
						}else{	//如果不是set序列对象，则进行join操作
							if("setJoin".equals(joinType)){
								join = this.getJoinByNameOrSetJoin(namePath.toString(),joinTable,names[i-1],setJoin,JoinType.LEFT);
							}else{
								join = this.getJoinByNameOrJoin(namePath.toString(),joinTable,names[i-1],join,JoinType.LEFT);
							}
							joinType = "join";
						}
					}
					
					//如果当前是最后一个属性值，则取出join对象，拼装最终的查询条件。
					if( i >= names.length-1 ){	//如果是最后一个元素，则进行最后的获取属性匹配
						if( "none".equals(joinType) ){
						}else if( "join".equals(joinType) ){
							expression = join.get(names[i]);
						}else if( "setJoin".equals(joinType) ){
							expression = setJoin.get(names[i]);
						}
					}
				}
				return expression;
			}
			
			private void getPredicateByKeyFromMap(CriteriaBuilder builder, String key ,Map<String , List<Predicate>> predicateMap,List<Predicate> predicateList,boolean isAnd){
				List<Predicate> tmpList = predicateMap.get( key );
				if(tmpList.size()>0){
					Predicate p = null;
					if(isAnd){
						p = builder.and(tmpList.toArray(new Predicate[tmpList.size()]));
					}else{
						p = builder.or(tmpList.toArray(new Predicate[tmpList.size()]));
					}
					predicateList.add(p);
				}
			}
			
			/**
			 * 从joinTable中获取name的setjoin对象，如果没有，则从setjoin中获取setjoin对象，并放入jointable中
			 * @param joinTable
			 * @param name
			 * @param setJoin
			 * @return
			 */
			@SuppressWarnings("rawtypes")
			private SetJoin getSetJoinByNameOrSetJoin(String namePath,Map<String,Object> joinTable, String name, SetJoin setJoin,JoinType joinType){
				SetJoin returnSetJoin = null;
				//判断是不是已经生成过join对象，如果没有生成，则需要生成后放入map中，否则拿出来直接用
				if(joinTable.containsKey(namePath)){
					returnSetJoin = ((SetJoin)joinTable.get(namePath));
				}else{
					//从上一个属性对象中获取关联的对象
					returnSetJoin = setJoin.joinSet(name,joinType);
					joinTable.put(namePath, setJoin);
				}
				return returnSetJoin;
			}
			
			/**
			 * 从joinTable中获取name的setjoin对象，如果没有，则从join中获取setjoin对象，并放入jointable中
			 * @param joinTable
			 * @param name
			 * @param join
			 * @return
			 */
			@SuppressWarnings("rawtypes")
			private SetJoin getSetJoinByNameOrJoin(String namePath,Map<String,Object> joinTable, String name, Join join,JoinType joinType){
				SetJoin setJoin = null;
				//判断是不是已经生成过join对象，如果没有生成，则需要生成后放入map中，否则拿出来直接用
				if(joinTable.containsKey(namePath)){
					setJoin = ((SetJoin)joinTable.get(namePath));
				}else{
					//从上一个属性对象中获取关联的对象
					setJoin = join.joinSet(name,joinType);
					joinTable.put(namePath, setJoin);
				}
				return setJoin;
			}
			
			/**
			 * 从joinTable中获取name的join对象，如果没有，则从setjoin中获取join对象，并放入jointable中
			 * @param joinTable
			 * @param name
			 * @param setJoin
			 * @return
			 */
			@SuppressWarnings("rawtypes")
			private Join getJoinByNameOrSetJoin(String namePath,Map<String,Object> joinTable, String name, SetJoin setJoin,JoinType joinType){
				Join join = null;
				//判断是不是已经生成过join对象，如果没有生成，则需要生成后放入map中，否则拿出来直接用
				if(joinTable.containsKey(namePath)){
					join = ((Join)joinTable.get(namePath));
				}else{
					//从上一个属性对象中获取关联的对象
					join = setJoin.join(name,joinType);
					joinTable.put(namePath, join);
				}
				return join;
			}
			
			/**
			 * 从joinTable中获取name的join对象，如果没有，则从join中获取join对象，并放入jointable中
			 * @param joinTable
			 * @param name
			 * @param join
			 * @return
			 */
			@SuppressWarnings("rawtypes")
			private Join getJoinByNameOrJoin(String namePath,Map<String,Object> joinTable, String name, Join join,JoinType joinType){
				Join returnJoin = null;
				//判断是不是已经生成过join对象，如果没有生成，则需要生成后放入map中，否则拿出来直接用
				if(joinTable.containsKey(namePath)){
					returnJoin = ((Join)joinTable.get(namePath));
				}else{
					//从上一个属性对象中获取关联的对象
					returnJoin = join.join(name,joinType);
					joinTable.put(namePath, returnJoin);
				}
				return returnJoin;
			}
			
			/**
			 * 从root级别获取setjoin对象，如果在jointable中有，则使用现有的，如果没有，则加入到jointable中。
			 * @param name
			 * @param expression
			 * @param root
			 * @param joinTable
			 * @return
			 */
			@SuppressWarnings("rawtypes")
			private SetJoin getSetJoin(String namePath,String name ,Root<T> root,Map<String,Object> joinTable,JoinType joinType){
				SetJoin setJoin = null;
				if(joinTable.containsKey(namePath)){
					setJoin = ((SetJoin)joinTable.get(namePath));
				}else{
					//当前语句只在第一次进入时执行，所以从root中获取级联对象
					setJoin = root.joinSet(name,joinType);
					joinTable.put(namePath, setJoin);
				}
				return setJoin;
			}
			/**
			 * 从root级别获取join对象，如果在jointable中有，则使用现有的，如果没有，则加入到jointable中。
			 * @param name
			 * @param expression
			 * @param root
			 * @param joinTable
			 * @return
			 */
			@SuppressWarnings("rawtypes")
			private Join getJoin(String namePath,String name ,Root<T> root,Map<String,Object> joinTable , JoinType joinType){
				Join join = null;
				if(joinTable.containsKey(namePath)){
					join = ((Join)joinTable.get(namePath));
				}else{
					//当前语句只在第一次进入时执行，所以从root中获取级联对象
					join = root.join(name,joinType);
					joinTable.put(namePath, join);
				}
				return join;
			}
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			private Predicate buildPredicate(CriteriaBuilder builder, CriteriaQuery<?> query, Path expression, LogicSearchFilter filter ){
				Predicate predicate = null;
				switch (filter.operator) {
				case EQ:
					predicate = builder.equal(expression, filter.value);
					break;
				case NOTEQ:
					predicate = builder.notEqual(expression, filter.value);
					break;
				case LIKE:
					predicate = builder.like(expression, "%" + filter.value + "%");
					break;
				case GT:
					predicate = builder.greaterThan(expression, (Comparable) filter.value);
					break;
				case LT:
					predicate = builder.lessThan(expression, (Comparable) filter.value);
					break;
				case GTE:
					predicate = builder.greaterThanOrEqualTo(expression, DateUtil.checkDate(filter.value.toString())==true?DateUtil.parseToDate(filter.value.toString(),DateUtil.PRECISE_DATE_FORMAT):(Comparable) filter.value);
					break;
				case LTE:
					predicate = builder.lessThanOrEqualTo(expression, DateUtil.checkDate(filter.value.toString())==true?DateUtil.parseToDate(filter.value.toString(),DateUtil.PRECISE_DATE_FORMAT):(Comparable) filter.value);
					break;
				case ISNOTNULL:
					predicate = builder.isNotNull(expression);
					break;
				case ISNULL:
					predicate = builder.isNull(expression);
					break;
				case ISNOTEMPTY:
					predicate = builder.isNotEmpty(expression);
					break;
				case ISEMPTY:
					predicate = builder.isEmpty(expression);
					break;
				case IN:
					if(filter.value instanceof Collection){
						Collection c = (Collection)filter.value;
						predicate = expression.in(c.toArray());
					}else{
						if(StringUtils.isBlank(filter.value.toString()))	break;
						String[] valArr = StringUtils.split(filter.value.toString(),splitChar);
						if(valArr.length>0){
							Object[] inObj = new Object[valArr.length];
							for(int i=0 ; i<valArr.length ;i++){
								inObj[i] = valArr;
							}
							predicate = expression.in(inObj);
						}
					}
					break;
				case GROUPBY:
					query = query.groupBy(expression);
					break;
				case DISTINCT:
					query = query.distinct(true);
					break;
				}
				return predicate;
			}
		};
	}
}
