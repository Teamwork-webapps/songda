package com.songda.recruit.dao.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public interface BaseDao<T> {

	T findOne(Object id);
	
	long count(Specification<T> spec);
	
	List<T> findAll(Specification<T> spec, Sort sort);
	
	Page<T> findAll(Specification<T> spec, Pageable pageable);
	
	List<T> findAll();
	
}
