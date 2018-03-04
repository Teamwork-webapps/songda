package com.songda.recruit.service.upload.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.songda.recruit.base.ReturnParam;
import com.songda.recruit.dao.BaseUploadFileDao;
import com.songda.recruit.dao.base.BaseDao;
import com.songda.recruit.pojo.BaseUploadFile;
import com.songda.recruit.service.base.BaseSearchService;
import com.songda.recruit.service.base.BaseSearchServiceImpl;
import com.songda.recruit.service.upload.UploadFileService;
import com.songda.recruit.service.user.AccountService;

/**
 * 
 * 
 * @author winder.yang
 */
@Component("top.shiningon.plat.company.service.upload.impl.UploadFileServiceImpl")
@Transactional(readOnly = true)
public class UploadFileServiceImpl extends BaseSearchServiceImpl<BaseUploadFile> implements UploadFileService,BaseSearchService<BaseUploadFile> {

	@Autowired BaseUploadFileDao baseUploadFileDao;
	@Autowired AccountService accountService;
	
	@Override
	public BaseDao<BaseUploadFile> getDao() {
		return baseUploadFileDao;
	}
	
	/**
	 * 保存更新productinfo主体
	 */
	@Transactional(readOnly=false)
	public ReturnParam saveUploadFileInfo(BaseUploadFile uploadFile){
		if(null == uploadFile){
			return ReturnParam.setReturnInfo(null, false, "参数提交失败");
		}
		BaseUploadFile ufi;
		//获取编辑还是新增
		if(null != uploadFile.getId()){
			ufi = this.baseUploadFileDao.findOne(uploadFile.getId());
			ufi.setUpdateBy(accountService.getCurrentUserId());
			ufi.setUpdateTime(new Date());
			ufi.setDelflag(uploadFile.isDelflag());
		}else{
			ufi = new BaseUploadFile();
			ufi.setCreateBy(accountService.getCurrentUserId());
			ufi.setCreateTime(new Date());
			ufi.setDelflag(false);
		}
		//更新相关字段。不做直接对象映射
		ufi.setType(uploadFile.getType());
		ufi.setFileName(uploadFile.getFileName());
		ufi.setRemark(uploadFile.getRemark());
		ufi.setPath(uploadFile.getPath());
		ufi = this.baseUploadFileDao.save(ufi);
		if(null != ufi && null != ufi.getId()){
			return ReturnParam.setReturnInfo(null, true, "保存成功")
					.setContent("id", ufi.getId());
		}
		return ReturnParam.setReturnInfo(null, false, "保存失败");
	}
	
	/**
	 * 逻辑删除记录
	 */
	@Transactional(readOnly=false)
	public ReturnParam saveLogicDelete(long uploadFileId){
		BaseUploadFile spsc = this.baseUploadFileDao.findOne(uploadFileId);
		spsc.setUpdateBy(accountService.getCurrentUserId());
		spsc.setUpdateTime(new Date());
		spsc.setDelflag(true);
		spsc = this.baseUploadFileDao.save(spsc);
		if(null != spsc && null != spsc.getId()){
			return ReturnParam.setReturnInfo(null, true, "保存成功");
		}
		return ReturnParam.setReturnInfo(null, false, "保存失败");
	}
	
}
