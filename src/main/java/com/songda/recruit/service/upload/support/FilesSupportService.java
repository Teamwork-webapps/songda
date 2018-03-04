package com.songda.recruit.service.upload.support;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文件存放路径设置。如果有文件服务器，则可以设置文件服务器地址。用于获取和上传
 * @author winder
 *
 */
@Component("top.shiningon.plat.company.service.upload.FilesSupportService")
@Transactional(readOnly = true)
public class FilesSupportService {

	@Autowired  
	private  HttpServletRequest request;
	
	public String fileUploadPath;
	
	public String fileDownloadPath;

	public String getFileUploadPath() {
		return request.getSession().getServletContext().getRealPath(fileUploadPath);
	}
	
	public String getFileUploadViewPath() {
		return fileUploadPath + File.separatorChar;
	}

	public void setFileUploadPath(String fileUploadPath) {
		this.fileUploadPath = fileUploadPath;
	}

	public String getFileDownloadPath() {
		return request.getSession().getServletContext().getRealPath(fileDownloadPath);
	}
	
	public String getFileDownloadViewPath() {
		return fileDownloadPath + File.separatorChar;
	}

	public void setFileDownloadPath(String fileDownloadPath) {
		this.fileDownloadPath = fileDownloadPath;
	}
	
}
