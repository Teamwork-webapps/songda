package com.songda.recruit.util;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;

import com.songda.recruit.base.ReturnParam;

public class HttpClientUtil {

	public static enum HTTP_CONTENT_TYPE{
		APPLICATION_JSON("application/json");
		private String strDesc;
		
		private HTTP_CONTENT_TYPE(String strDesc){
			this.strDesc = strDesc;
		}
		
		public String getDesc(){
			return this.strDesc;
		}
	}
	
	public ReturnParam sendPost(String urlString, String urlParam, HTTP_CONTENT_TYPE contentType) {
		System.out.println("sendPost urlString:" + urlString);
		System.out.println("sendPost urlParam:" + urlParam);
		HttpURLConnection connection = null;
		try {
			// 创建连接
			URL url = new URL(urlString);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", contentType.getDesc() + "; charset=UTF-8");
			connection.connect();
			// POST请求
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.write(urlParam.getBytes("UTF-8"));// 这样可以处理中文乱码问题
			out.flush();
			out.close();
			//从链接中获取一个输入流对象  
			InputStream inStream = connection.getInputStream();  
	        //调用数据流处理方法  
			String retStr = this.converInputStreamToByteArray(inStream);
			System.out.println("sendPost return:" + retStr);
			return ReturnParam.setReturnInfo(null, true, retStr);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return ReturnParam.setReturnInfo(null, false, e.getClass().getName() +":"+ e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			return ReturnParam.setReturnInfo(null, false, e.getClass().getName() +":"+ e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			return ReturnParam.setReturnInfo(null, false, e.getClass().getName() +":"+ e.getMessage());
		}finally{
			// 断开连接
			connection.disconnect();
		}
	}
	
	/**
	 * 用于调用微信多媒体文件上传
	 * @param urlString
	 * @param urlParam
	 * @param contentType
	 * @param file
	 * @return
	 */
	public ReturnParam sendPost(String urlString, String urlParam, HTTP_CONTENT_TYPE contentType, File file) {
		System.out.println("sendPost urlString:" + urlString);
		System.out.println("sendPost urlParam:" + urlParam);
		HttpURLConnection connection = null;
		try {
			// 创建连接
			URL url = new URL(urlString);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			//设置请求头信息
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Charset", "UTF-8");
			// 设置边界
		    String BOUNDARY = "----------" + System.currentTimeMillis();
		    connection.setRequestProperty("Content-Type","multipart/form-data; boundary=" + BOUNDARY);
		    // 请求正文信息
		    // 第一部分：
		    StringBuilder sb = new StringBuilder();
		    sb.append("--"); // 必须多两道线
		    sb.append(BOUNDARY);
		    sb.append("\r\n");
		    sb.append("Content-Disposition: form-data;name=\"media\";filelength=\"" + file.length() + "\";filename=\""
		            + file.getName() + "\"\r\n");
		    sb.append("Content-Type:application/octet-stream\r\n\r\n");
		    byte[] head = sb.toString().getBytes("utf-8");
		    // 获得输出流
		    OutputStream out = new DataOutputStream(connection.getOutputStream());
		    // 输出表头
		    out.write(head);
		    
		    // 文件正文部分
		    // 把文件已流文件的方式 推入到url中
		    DataInputStream in = new DataInputStream(new FileInputStream(file));
		    int bytes = 0;
		    byte[] bufferOut = new byte[1024];
		    while ((bytes = in.read(bufferOut)) != -1) {
		        out.write(bufferOut, 0, bytes);
		    }
		    in.close();
		    byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
		    out.write(foot);
		    out.flush();
		    out.close();
		    
			connection.connect();
			//从链接中获取一个输入流对象  
			InputStream inStream = connection.getInputStream();  
	        //调用数据流处理方法  
			String retStr = this.converInputStreamToByteArray(inStream);
			System.out.println("sendPost return:" + retStr);
			return ReturnParam.setReturnInfo(null, true, retStr);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return ReturnParam.setReturnInfo(null, false, e.getClass().getName() +":"+ e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			return ReturnParam.setReturnInfo(null, false, e.getClass().getName() +":"+ e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			return ReturnParam.setReturnInfo(null, false, e.getClass().getName() +":"+ e.getMessage());
		}finally{
			// 断开连接
			connection.disconnect();
		}
	}

	public ReturnParam sendGet(String urlString, String urlParam){
		HttpURLConnection connection = null;
		try{
			String fullUrl = urlString;
			if(StringUtils.isNoneBlank(urlParam)){
				fullUrl = fullUrl + "?" + urlParam;
			}
			URL url = new URL(fullUrl);
			System.out.println("url:"+fullUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			//从链接中获取一个输入流对象  
			InputStream inStream = connection.getInputStream();
	        //调用数据流处理方法  
			String retStr = this.converInputStreamToByteArray(inStream);
			System.out.println("sendGet return:" + retStr);
			return ReturnParam.setReturnInfo(null, true, retStr);
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return ReturnParam.setReturnInfo(null, false, e.getClass().getName() +":"+ e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			return ReturnParam.setReturnInfo(null, false, e.getClass().getName() +":"+ e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			return ReturnParam.setReturnInfo(null, false, e.getClass().getName() +":"+ e.getMessage());
		}finally{
			// 断开连接
			connection.disconnect();
		}
	}

	public static byte[] readInputStream(InputStream inStream) throws Exception{  
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        while( (len=inStream.read(buffer)) != -1 ){  
        	outStream.write(buffer, 0, len);  
        }  
        inStream.close();  
        return outStream.toByteArray();  
    }

	/**
	 * 将http的回复信息流，转换为字符串
	 * @param inputStream
	 * @return
	 */
	public String converInputStreamToByteArray(InputStream inputStream) {
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		int flag = 0;
		byte[] buffer = new byte[1024];
		try {
			while (-1 != (flag = inputStream.read(buffer))) {
				arrayOutputStream.write(buffer, 0, flag);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] bytes = arrayOutputStream.toByteArray();
		try {
			return new String(bytes,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new String(bytes);
	}
	
}
