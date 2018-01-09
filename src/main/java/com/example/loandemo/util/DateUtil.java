package com.example.loandemo.util;

/**
 * create by zhangjun1 on 2017/12/25
 */

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 日期Util类
 *  yyyy-MM-dd H:m:s
 * @author
 */
public class DateUtil
{
    private static String defaultDatePattern = "yyyy-MM-dd";



    public static Date getLocalDate(String timeZone, Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        TimeZone tz = TimeZone.getTimeZone("timeZone");
        calendar.setTimeZone(tz);
        return calendar.getTime();
    }


    public static Timestamp getDiffDayTimeStamp(Date date, int days, String format){
        Calendar   calendar   =   new   GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,days);
        date=calendar.getTime();
        DateFormat sdf = new SimpleDateFormat(format);
        Timestamp stamp=Timestamp.valueOf(sdf.format(date));
        return stamp;
    }

    public static Timestamp getDiffHourTimeStamp(Date date,int hours,String format){
        Calendar   calendar   =   new   GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR,hours);
        date=calendar.getTime();
        DateFormat sdf = new SimpleDateFormat(format);
        Timestamp stamp=Timestamp.valueOf(sdf.format(date));
        return stamp;
    }

    public static Date getDiffDayDate(Date date,int days,String format){
        Calendar   calendar   =   new   GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,days);
        date=calendar.getTime();
        return date;
    }


    public static Date getDiffHourDate(Date date,int hours,String format){
        Calendar   calendar   =   new   GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR,hours);
        date=calendar.getTime();
        return date;
    }

    public static String getToShortString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        // if(format.format(date).equals(format.format(new Date())))
        return format.format(date);
        //format.applyPattern("H:m");
        //return format.format(date);
    }

    public static String getToMiddleString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        // if(format.format(date).equals(format.format(new Date())))
        return format.format(date);
        //format.applyPattern("H:m");
        //return format.format(date);
    }



    public static String getToLongString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss.SSS");
        if(format.format(date).equals(format.format(new Date())))
            return format.format(date);
        format.applyPattern("H:m");
        return format.format(date);
    }




    /**
     * 获得默认的 date pattern
     */
    public static String getDatePattern()
    {
        return defaultDatePattern;
    }

    /**
     * 返回预设Format的当前日期字符串
     */
    public static String getToday()
    {
        Date today = new Date();
        return format(today);
    }

    /**
     * 使用预设Format格式化Date成字符串
     */
    public static String format(Date date)
    {
        return date == null ? " " : format(date, getDatePattern());
    }

    /**
     * 使用参数Format格式化Date成字符串
     */
    public static String format(Date date, String pattern)
    {
        return date == null ? " " : new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 使用预设格式将字符串转为Date
     */
    public static Date parse(String strDate) throws ParseException
    {
        return StringUtils.isBlank(strDate) ? null : parse(strDate,
                getDatePattern());
    }

    /**
     * 使用参数Format将字符串转为Date
     */
    public static Date parse(String strDate, String pattern)
            throws ParseException
    {
        return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(
                pattern).parse(strDate);
    }

    /**
     * 在日期上增加数个整月
     */
    public static Date addMonth(Date date, int n)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    public static String getLastDayOfMonth(String year, String month)
    {
        Calendar cal = Calendar.getInstance();
        // 年
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        // 月，因为Calendar里的月是从0开始，所以要-1
        // cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        // 日，设为一号
        cal.set(Calendar.DATE, 1);
        // 月份加一，得到下个月的一号
        cal.add(Calendar.MONTH, 1);
        // 下一个月减一为本月最后一天
        cal.add(Calendar.DATE, -1);
        return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));// 获得月末是几号
    }

    public static Date getDate(String year, String month, String day)
            throws ParseException
    {
        String result = year + "- "
                + (month.length() == 1 ? ("0 " + month) : month) + "- "
                + (day.length() == 1 ? ("0 " + day) : day);
        return parse(result);
    }
    /*
     * return：相差秒数
     * */
    public static long calLastedTime(Date startDate) {
        long a = new Date().getTime();
        long b = startDate.getTime();
        long c = (long)((a - b) / 1000);
        return c;
    }
}