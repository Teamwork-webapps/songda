package com.songda.recruit.dao;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.songda.recruit.dao.base.BaseDao;
import com.songda.recruit.pojo.BaseUser;
/**
 * 账户管理DAO接口.
 * 
 * @author Gavin.chen
 */
public interface BaseUserDao extends PagingAndSortingRepository<BaseUser, Long>, JpaSpecificationExecutor<BaseUser> ,BaseDao<BaseUser>{
	BaseUser findByAccount(String account);
	
	List<BaseUser> findAll(Specification<BaseUser> spec);
	
}
