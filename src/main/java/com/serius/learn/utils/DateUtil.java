package com.serius.learn.utils;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

/**
 * create by tanweia on Jun 8, 2017
 */
public class DateUtil {
	
	public static final String DATETIME_FORMAT = "yyyyMMddHHmmss";
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATETIME_FORMAT);
    public static final String DATE_FORMAT = "yyyyMMdd";
    public static final String SHOW_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String SHOW_DATE_FORMAT = "yyyy-MM-dd";
    public static Date TIME_2099 = null;
	
	private static final long DAY = 24 * 3600 * 1000;
	
	public static Date getStartTime() {  
        Calendar todayStart = Calendar.getInstance();  
        todayStart.set(Calendar.HOUR, 0);  
        todayStart.set(Calendar.MINUTE, 0);  
        todayStart.set(Calendar.SECOND, 0);  
        todayStart.set(Calendar.MILLISECOND, 0);  
        return todayStart.getTime();  
    }  
  
	public static Date getEndTime() {  
        Calendar todayEnd = Calendar.getInstance();  
        todayEnd.set(Calendar.HOUR, 23);  
        todayEnd.set(Calendar.MINUTE, 59);  
        todayEnd.set(Calendar.SECOND, 59);  
        todayEnd.set(Calendar.MILLISECOND, 999);  
        return todayEnd.getTime();  
    }  
	
	/**
	 * 获取2099年时间
	 * @return
	 * @throws ParseException
	 */
	public static Date getTime2099() {
		if(TIME_2099 != null){
			return TIME_2099;
		} else{
			return parseDate("2099-12-31 23:59:59");
		}
	}
	
	/**
     * @param date      时间
     * @param formatStr 格式化串
     * @return
     */
    public static String format(Date date, String formatStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
    }
	
	/** 
	 * Title: nextDay 
	 * Description:  获取后一天
	 * @param start
	 * @return
	 */
	public static Date nextDay(Date start) {
        Calendar c = Calendar.getInstance();
        c.setTime(start);
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }
	
	/** 
	 * Title: preDay 
	 * Description:  获取前一天
	 * @param start
	 * @return
	 */
	public static Date preDay(Date start) {
        Calendar c = Calendar.getInstance();
        c.setTime(start);
        c.add(Calendar.DATE, -1);
        return c.getTime();
    }
	
	public static Date after99year(Date start) {
		 Calendar c = Calendar.getInstance();
	        c.setTime(start);
	        c.add(Calendar.YEAR, 99);
	        return c.getTime();
	}
	
	/** 
	 * Title: getCurrentDayRemainTime 
	 * Description:  获取当天剩余秒数
	 * @author tanweia
	 * @return
	 */
	public static Long getCurrentDayRemainTime() {
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    return (cal.getTimeInMillis() - System.currentTimeMillis())/1000;
	}

	/**
	 * 获取当前日期
	 *
	 * @return
	 */
	public static String getCurrentYmd() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(date);
	}
	/**
	 * 时间转换成日期
	 */
	public static Date parseDateByShowFormat(String parseTime) {
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(parseTime);
		} catch (ParseException e) {
			date = null;
		}
		return date;
	}
	

	/**
	 * 获取日期
	 *
	 * @return
	 */
	public static Date getCurrentDate() {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(getCurrentYmd());
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 时间描述
	 * 	        精确到天，自动进1
	 * @param remainTime 毫秒为单位
	 * @return
	 */
	public static String getRemainTimeStr(long remainTime) {
		long remainDay = remainTime/DAY;
		remainTime = remainTime%DAY;
		if(remainTime != 0){
			remainDay = remainDay + 1;
		}
		return  remainDay + "天";
	}
	
	/** 
	 * Title: preDay 
	 * Description:  获取后n天
	 * @param start
	 * @return
	 */
	public static Date afterDay(Date start, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(start);
        c.add(Calendar.DATE, n);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }
	
	/**
     * 获取当前时间串，格式为：yyyymmddHHMiSS
     *
     * @return
     */
    public static final String getCurrDatetime() {
        return format(new Date(), DATETIME_FORMAT);
    }

    /**
     * 获取当前日期串，格式为yyyymmdd
     *
     * @return
     */
    public static final String getCurrDate() {
        return format(new Date(), DATE_FORMAT);
    }
    
    /**
     * {@code time1}是否小于{@code time2},即类似于<pre>time1 < time2</pre>。 如果{@code time2}为<code>null</code>，
     * 则视为最小。
     *
     * @param time1 时间字符串，格式为 yyyyMMddHHmmss，不足14位后补0
     * @param time2 时间字符串，格式为 yyyyMMddHHmmss，不足14位后补0
     * @return
     */
    public static boolean lessThan(String time1, String time2) {
        if (StringUtils.isEmpty(time1)) {
            if (StringUtils.isEmpty(time2)) {
                return false;
            } else {
                return true;
            }
        } else {
            return time1.compareTo(time2) < 0;
        }
    }


    /**
     * {@code time1}是否大于{@code time2},即类似于<pre>time1 > time2</pre>。如果{@code time2}为<code>null</code>，
     * 则视为最大。
     *
     * @param time1 时间字符串，格式为 yyyyMMddHHmmss，不足14位后补9
     * @param time2 时间字符串，格式为 yyyyMMddHHmmss，不足14位后补9
     * @return
     */
    public static boolean greaterThan(String time1, String time2) {
        if (StringUtils.isEmpty(time1)) {
            if (StringUtils.isEmpty(time2)) {
                return false;
            } else {
                return true;
            }
        } else {
            return time1.compareTo(time2) > 0;
        }
    }

    /**
     * 将<code>datetime</code>字符串时间转换为毫秒数
     * @param datetime  长度必须大于等于8而小于等于14，格式为 yyyyMMddHHmmss，不足14位后补0
     * @return
     */
    public static long toMilliseconds(String datetime){
        return parseDate(datetime).getTime();
    }

    /**
     * 将格式为{@link #DATETIME_FORMAT}的时间格式解析为Date对象,{@code datetime}的长度必须大于8小于14.
     * @param datetime
     * @return
     */
    @SuppressWarnings("deprecation")
	public static Date parseDate(String datetime){
        Assert.notNull(datetime);
        int len = datetime.length();
        Assert.isTrue(len >= 8 && len <= 19,"长度必须大于等于8而小于等于19");
        DateFormat dateFormat = SIMPLE_DATE_FORMAT;
        try {
            switch (len) {
			case 8:
				dateFormat = new SimpleDateFormat(DATE_FORMAT); 
				break;
			case 10:
				dateFormat = new SimpleDateFormat(SHOW_DATE_FORMAT); 
				break;
			case 19:
				dateFormat = new SimpleDateFormat(SHOW_DATETIME_FORMAT); 
				break;
			default:
				break;
			}
            return dateFormat.parse(datetime);
        } catch (ParseException e) {
            throw new IllegalArgumentException("入参datetime："+datetime+"解析异常，请检查格式必须为："+DATETIME_FORMAT);
        }
    }

    /**
     * 将字符串时间解析为对象
     * @param datetime
     * @param format
     * @return
     */
    @SuppressWarnings("deprecation")
	public static Date parseDate(String datetime,String format){
        Assert.notNull(datetime);
        Assert.notNull(format);
        Assert.isTrue(datetime.length() == format.length(),"值和格式串的长度不一致");
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(datetime);
        } catch (ParseException e) {
            throw new IllegalArgumentException(
                    MessageFormat.format("入参datetime：{1}解析异常，请检查格式必须为：{2}",datetime,format));
        }
    }
    
    /**
     * 获取零点时间
     * @param dateStr
     * @return
     */
    public static String getZeroDateTime(String dateStr) {
    	Date date = parseDate(dateStr, SHOW_DATE_FORMAT);
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        return format(zero, SHOW_DATE_FORMAT);
    }
}
