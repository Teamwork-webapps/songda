package com.songda.recruit.util;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FreemarkerPropertiesUtils {

	private static Properties prop = null;
	
	/**
	 * 获取配置信息。
	 * 如果已经设置过配置，则不进行更新，如果没有设置，则会使用根目录包下的JMail.properties文件
	 */
	static{
		//获取默认的properties文件
		if( null == FreemarkerPropertiesUtils.prop ){
			InputStream inStream;
			try {
				FreemarkerPropertiesUtils.prop = new Properties();
				inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("production/freemarker.properties");
				FreemarkerPropertiesUtils.prop.load(inStream);
				inStream.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				//配置文件找不到
				FreemarkerPropertiesUtils.prop = null;
			} catch (IOException e) {
				e.printStackTrace();
				//配置文件读取失败
				FreemarkerPropertiesUtils.prop = null;
			}
		}
		System.out.println("no init");
	}
	
	public static String getProp(String name){
		String str = FreemarkerPropertiesUtils.prop.get(name).toString();
		return str;
	}
}
