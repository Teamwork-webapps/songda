package com.songda.recruit.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.songda.recruit.base.BaseSearchByPageDTO;

public class PageUtil {

	private static Logger logger = LoggerFactory.getLogger(PageUtil.class);
	
	/**
	 * 创建分页请求.
	 */
	public static PageRequest buildPageRequest(BaseSearchByPageDTO dto) {
		if(null == dto){
			return new PageRequest(1, 20, new Sort( Direction.DESC ,"id" ));
		}
		int page = NumberUtils.toInt(dto.getPage());
		int size = NumberUtils.toInt(dto.getRows());
		Sort sort = null;
		if( StringUtils.isNotBlank(dto.getSortCol()) ) {
			if(dto.getSortCol().contains(";")){
				List<Order> list = new ArrayList<Order>();
				String[] sorts = StringUtils.split(dto.getSortCol(),";");
				String[] orders = StringUtils.split(dto.getOrderType(),";");
				int index = 0;
				String os;
				for(String ts : sorts){
					os = orders.length >= (index+1) ? orders[index] : orders[0];
					Order order = new Order( (null != os && "asc".equals(os)?Direction.ASC:Direction.DESC) , ts);
					index++;
					list.add(order);
				}
				sort = new Sort(list);
			}else{
				sort = new Sort( (null != dto.getOrderType() && "asc".equals(dto.getOrderType())?Direction.ASC:Direction.DESC) , dto.getSortCol());
			}
		}
		logger.debug("pageUtil info begin");
		logger.debug("page:" + dto.getPage());
		logger.debug("size:" + dto.getRows());
		logger.debug("order:" + dto.getOrderType());
		logger.debug("sort:" + dto.getSortCol());
		logger.debug("pageUtil info end");
		return new PageRequest(page-1, size, sort);
	}
	
	public static Sort buildSort(BaseSearchByPageDTO dto){
		Sort sort = null;
		if(null == dto) return sort;
		if( StringUtils.isNotBlank(dto.getSortCol()) ) {
			if(dto.getSortCol().contains(";")){
				List<Order> list = new ArrayList<Order>();
				String[] sorts = StringUtils.split(dto.getSortCol(),";");
				String[] orders = StringUtils.split(dto.getOrderType(),";");
				int index = 0;
				String os;
				for(String ts : sorts){
					os = orders.length >= (index+1) ? orders[index] : orders[0];
					Order order = new Order( (null != os && "asc".equals(os)?Direction.ASC:Direction.DESC) , ts);
					index++;
					list.add(order);
				}
				sort = new Sort(list);
			}else{
				sort = new Sort( (null != dto.getOrderType() && "asc".equals(dto.getOrderType())?Direction.ASC:Direction.DESC) , dto.getSortCol());
			}
		}
		return sort;
	}
	
}
