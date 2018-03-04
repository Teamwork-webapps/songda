package com.songda.recruit.service.upload;

import java.io.File;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.songda.recruit.base.ReturnParam;
import com.songda.recruit.pojo.BaseUploadFile;
import com.songda.recruit.service.user.AccountService;

@Component("top.shiningon.plat.company.service.upload.UploadService")
@Transactional(readOnly = true)
public class UploadService {

	@Autowired UploadFileService uploadFileService;
	@Autowired AccountService accountService;

	/**
	 * 上传文件信息 - 有备注模式
	 * @param file
	 * @return
	 */
	@Transactional(readOnly=false)
	public ReturnParam uploadFileSaveDB(MultipartFile file,String fileName, String remark, String path){
		String originalFilename = file.getOriginalFilename();
		File targetFile = null;
		if( originalFilename.lastIndexOf(".") > 0 ){
			String pix = originalFilename.substring( originalFilename.lastIndexOf(".") + 1 , originalFilename.length() );
			targetFile = new File(path, UUID.randomUUID().toString().replace("-", "") + "." + pix);
		}else{
			targetFile = new File(path, originalFilename);
		}
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
			//保存数据库
			BaseUploadFile fileInfo = new BaseUploadFile();
			if(targetFile.getName().lastIndexOf(".") > 0){
				String type = targetFile.getName().substring( targetFile.getName().lastIndexOf(".") , targetFile.getName().length() );
				fileInfo.setType(type);
			}else{
				fileInfo.setType("file");
			}
			fileInfo.setPath("upload/" + targetFile.getName());
			if(StringUtils.isNoneBlank(remark)){
				fileInfo.setRemark(remark);
			}
			if(StringUtils.isNoneBlank(fileName)){
				fileInfo.setFileName(fileName);
			}else{
				fileInfo.setFileName(originalFilename.substring(0, originalFilename.lastIndexOf(".")));
			}
			if(null != fileInfo.getFileName() && fileInfo.getFileName().length()>200){
				fileInfo.setFileName(fileInfo.getFileName().substring(0, 199));
			}
			ReturnParam rp = uploadFileService.saveUploadFileInfo(fileInfo);
			if(rp.checkIsSuccess()){
				//保存成功后，检查后缀，进行后续可能的预览文件异步生成。
				return ReturnParam.setReturnInfo(null, true, "上传成功")
						.setContent("fileName", targetFile.getName())
						.setContent("filePath", targetFile.getPath())
						.setContent("id", rp.getContent("id"));
			}else{
				return rp;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnParam.setReturnInfo(null, false, "上传失败：" + e.getMessage());
		}
	}
	
}
