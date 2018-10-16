package com.baseljc.mvp.tools.time;

import android.content.Context;
import android.text.TextUtils;

import net.danlew.android.joda.DateUtils;

import org.joda.time.DateTime;
import org.joda.time.Weeks;

/**
 * 封装时间操作类，方便替换工具类
 * Created by 0200030 on 2017/9/19.
 */
public class TimeHelper {

    /**
     * 获取"*小时前","*分钟前","*天前"等字符串，超过七天的显示日期字符串
     *
     * @param context
     * @param timeStr
     * @return
     */
    public static String get7DayTimeString(Context context, long timeStr) {
        return DateUtils.getRelativeTimeSpanString(context
                , new DateTime(timeStr)
                , DateUtils.FORMAT_ABBREV_RELATIVE).toString();

    }

    /**
     * 获取"*小时前","*分钟前","*天前"等字符串，超过1天的显示日期字符串
     *
     * @param context 上下文
     * @param time    精确至毫秒级的时间 @see 1508431920241
     * @return
     */
    public static String getRelativeTimeString(Context context, long time) {
        String timeStr = DateUtils.getRelativeDateTimeString(context, new DateTime(time), Weeks.ZERO, 0).
                toString();
        if (TextUtils.isEmpty(timeStr)) {//判空
            return "";
        }

        int index = -1;

        if (timeStr.contains(",")) {//中文逗号
            index = timeStr.indexOf(",");
        } else if (timeStr.contains("，")) {//英文逗号
            index = timeStr.indexOf("，");//，
        }
        if (index != -1) {
            timeStr = timeStr.substring(0, index);//切割
            timeStr = timeStr.replaceAll(" ", "");//替换空格
        }
        if(!TextUtils.isEmpty(timeStr)&&timeStr.trim().equals("0分钟")){
            return "刚刚";
        }
        return timeStr;
    }

    /**
     * 获取年月日 如2017年01月01日
     *
     * @param context
     * @param time
     * @return
     */
    public static String getDate(Context context, long time) {
        return DateUtils.formatDateTime(context, new DateTime(time), DateUtils.FORMAT_SHOW_DATE
                | DateUtils.FORMAT_SHOW_YEAR);
    }

    /**
     * 返回给定时间的对应天数
     *
     * @param time
     * @return
     */
    public static int formartLongTime(long time) {
        return (int) (time / (24 * 3600 * 1000));
    }

    /**
     * 是否是今天
     *
     * @param time
     * @return
     */
    public static boolean isToday(long time) {
        return DateUtils.isToday(new DateTime(time));
    }
}
