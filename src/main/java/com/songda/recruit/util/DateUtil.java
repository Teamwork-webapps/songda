package com.songda.recruit.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtil {

	public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";
	public static final String PRECISE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * @param dateTime
	 * @return
	 */
	public static String getDateTimeByText(DateTime dateTime , String DATE_FORMAT_TYPE) {
		if (dateTime != null) {
			return dateTime.toString(DATE_FORMAT_TYPE);
		}
		return null;
	}
	
	/**
	 * @param dateTime
	 * @return
	 */
	public static String getDateByText(Date date , String DATE_FORMAT_TYPE) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_TYPE);
			return sdf.format(date);
		}
		return null;
	}

	/**
	 * 获取日期时间格式字符串
	 * @param dateTime
	 * @return
	 */
	public  static String getDateText(DateTime dateTime , String DATE_FORMAT_TYPE) {
		if (dateTime != null) {
			return dateTime.toString(DATE_FORMAT_TYPE);
		}
		return null;
	}

	/**
	 * 将字符串转换为DateTime对象 格式：'yyyy-MM-dd HH:mm:ss'
	 * @param dateText
	 * @return
	 */
	public  static DateTime setDateText(String dateText) {
		// parse the string
		DateTimeFormatter formatter = DateTimeFormat.forPattern(PRECISE_DATE_FORMAT);
		DateTime dateTime = formatter.parseDateTime(dateText);
		return dateTime;
	}
	
	/**
	 * 将字符串转换为DateTime对象 格式：'yyyy-MM-dd'
	 * @param dateText
	 * @return
	 */
	public  static DateTime setDateTextSimple(String dateText) {
		// parse the string
		DateTimeFormatter formatter = DateTimeFormat.forPattern(SIMPLE_DATE_FORMAT);
		DateTime dateTime = formatter.parseDateTime(dateText);
		return dateTime;
	}
	
	/**
	 * 将日期时间字符串转换为Timestamp对象 格式：'yyyy-MM-dd HH:mm:ss'
	 * @param strDate
	 * @return
	 */
	public static Timestamp parseToTimestamp(String strDate){
		SimpleDateFormat formater = new SimpleDateFormat(PRECISE_DATE_FORMAT);
		Date date = null;
		Timestamp result =null;
		try {
			date = formater.parse(strDate);
			result = new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 将字符串转换为Date对象
	 * @param strDate
	 * @return
	 */
	public static Date parseToDate(String strDate , String DATE_FORMAT_TYPE){
		SimpleDateFormat formater = new SimpleDateFormat(DATE_FORMAT_TYPE);
		Date result = null;
		try {
			if(null !=strDate && !"".equals(strDate)){
				result = formater.parse(strDate);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	   * 检查日期合法性 格式为：'yyyy-MM-dd HH:mm:ss'
	   * @param strDate 日期字符串
	   * @return boolean
	   */
	  public static boolean checkDate(String strDate) {
	    boolean ret = true;
	    try {
	      DateFormat df = new SimpleDateFormat(PRECISE_DATE_FORMAT);
	      ret = df.format(df.parse(strDate)).equals(strDate);
	    }catch (ParseException e) {
	      ret = false;
	    }
	    return ret;
	  }

	  public static String getDateTimeWeek(DateTime dt){
		//星期  
		  switch(dt.getDayOfWeek()) {
		  case DateTimeConstants.SUNDAY:  
		      return "星期日";  
		  case DateTimeConstants.MONDAY:  
			  return "星期一";  
		  case DateTimeConstants.TUESDAY:  
			  return "星期二";  
		  case DateTimeConstants.WEDNESDAY:  
			  return "星期三";  
		  case DateTimeConstants.THURSDAY:  
			  return "星期四";  
		  case DateTimeConstants.FRIDAY:  
			  return "星期五";  
		  case DateTimeConstants.SATURDAY:  
			  return "星期六";  
		  }
		  return "";
	  }
	  
}
