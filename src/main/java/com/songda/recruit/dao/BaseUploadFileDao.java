package com.songda.recruit.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.songda.recruit.dao.base.BaseDao;
import com.songda.recruit.pojo.BaseUploadFile;
/**
 * DAO接口.
 * 
 * @author Gavin.chen
 */
public interface BaseUploadFileDao extends PagingAndSortingRepository<BaseUploadFile, Long>, JpaSpecificationExecutor<BaseUploadFile> ,BaseDao<BaseUploadFile>{

}
