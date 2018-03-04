package com.songda.recruit.service.upload;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.songda.recruit.base.ReturnParam;
import com.songda.recruit.pojo.BaseUploadFile;
import com.songda.recruit.service.base.BaseSearchService;

/**
 * 网页信息抓取的服务 
 * 
 * @author winder.yang
 */
@Component("top.shiningon.plat.enterprise.service.upload.UploadFileService")
@Transactional(readOnly = true)
public interface UploadFileService extends BaseSearchService<BaseUploadFile> {
	
	public ReturnParam saveUploadFileInfo(BaseUploadFile fileInfo);
	
	public ReturnParam saveLogicDelete(long salesVisitId);
}
