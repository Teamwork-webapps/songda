package com.songda.recruit.dao;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.songda.recruit.pojo.BaseRole;
/**
 * 账户管理DAO接口.
 * 
 * @author Gavin.chen
 */
public interface BaseRoleDao extends PagingAndSortingRepository<BaseRole, String>, JpaSpecificationExecutor<BaseRole>{
	List<BaseRole> findAll(Specification<BaseRole> spec);
	
	BaseRole findByCode(String code);
}
