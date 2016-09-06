package com.asgab.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.alibaba.fastjson.JSONArray;

public class CommonUtil {

  public static final PropertiesLoader properties_zh = new PropertiesLoader("message_zh_CN.properties");
  public static final PropertiesLoader properties_en = new PropertiesLoader("message_en_US.properties");

  public static final Map<String, String> LOG_MODULE_ZH = new TreeMap<String, String>();
  public static final Map<String, String> LOG_MODULE_EN = new TreeMap<String, String>();
  public static final Map<String, String> LOG_OPERATE_TYPE_ZH = new TreeMap<String, String>();
  public static final Map<String, String> LOG_OPERATE_TYPE_EN = new TreeMap<String, String>();

  static {

    for (Operate oper : Operate.values()) {
      LOG_OPERATE_TYPE_ZH.put(String.valueOf(oper.getIndex()), oper.getName());
      LOG_OPERATE_TYPE_EN.put(String.valueOf(oper.getIndex()), oper.getNameEN());
    }
  }

  public static String i18nStr(ServletRequest request, String zh, String en) {
    return "zh".equalsIgnoreCase(request.getLocale().getLanguage()) ? zh : en;
  }

  public static String formatDate(Date date) {
    return date != null ? formatDate(date, "yyyy-MM-dd HH:mm:ss") : "";
  }

  public static String formatDate(Date date, String format) {
    if (date != null) {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      return sdf.format(date);
    } else {
      return "";
    }
  }

  /**
   * 保留2位小数
   * 
   * @param num
   * @return
   */
  public static String reserved2Digit(String num) {
    return reserved2Digit(Double.parseDouble(num));
  }

  public static String reserved2Digit(Double num) {
    String format = "0.00";
    return new DecimalFormat(format).format(num);
  }

  /**
   * 数字千位分割, 保留2位
   * 
   * @param num
   * @return
   */
  public static String digSeg(Double num) {
    String format = "#,##0.00";
    return new DecimalFormat(format).format(num);
  }

  /**
   * list去重复
   * 
   * @param list
   */
  public static void distinctList(List<String> list) {
    for (int i = list.size() - 1; i >= 0; i--) {
      boolean exist = false;
      for (int j = 0; j < i; j++) {
        if (list.get(i).equalsIgnoreCase(list.get(j))) {
          list.remove(i);
          exist = true;
          break;
        }
      }
      if (exist) {
        continue;
      }
    }
  }

  /***
   * 获取国家化资源文件，按key获取
   * 
   * @param request
   * @param key
   * @return
   */
  public static String getProperty(HttpServletRequest request, String key) {
    LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
    String lang = localeResolver.resolveLocale(request).getLanguage();
    return getProperty(lang, key);
  }

  public static String getProperty(String lang, String key) {
    if (properties_zh == null || properties_en == null || !StringUtils.isNotBlank(key)) {
      return "";
    }
    return lang.equalsIgnoreCase("zh") ? properties_zh.getProperty(key) : properties_en.getProperty(key);
  }

  public static String array2String(String[] array) {
    if (array != null && array.length > 0) {
      JSONArray jsonArray = new JSONArray();
      for (String s : array) {
        jsonArray.add(s);
      }
      return jsonArray.toJSONString();
    }
    return "";
  }

  public static String[] string2Array(String str) {
    if (StringUtils.isNotBlank(str)) {
      JSONArray jsonArray = JSONArray.parseArray(str);
      String[] strArr = new String[jsonArray.size()];
      for (int i = 0; i < jsonArray.size(); i++) {
        strArr[i] = jsonArray.getString(i);
      }
      return strArr;
    }
    return new String[0];
  }

  public static String formatDate(Long date, String format) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      Calendar c = Calendar.getInstance();
      if (date != null) {
        c.setTimeInMillis(date);
        return sdf.format(c.getTime());
      }
    } catch (Exception e) {
      return "";
    }
    return "";
  }

  public static Long getFistDayOfMonth() {
    Calendar calendar = Calendar.getInstance();
    return getFistDayOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
  }

  public static Long getFistDayOfMonth(int year, int month) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, month);
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    return calendar.getTimeInMillis();
  }

  public static Long getLastDayOfMonth() {
    Calendar calendar = Calendar.getInstance();
    return getFistDayOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
  }

  public static Long getLastDayOfMonth(int year, int month) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, month);
    calendar.add(Calendar.MONTH, 1);
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.add(Calendar.SECOND, -1);
    return calendar.getTimeInMillis();
  }

  // gap Day
  public static Long getGapNDaysStart(int day) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_MONTH, day);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    return calendar.getTimeInMillis();
  }

  public static Long getGapNDaysEnd(int day) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_MONTH, day + 1);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.add(Calendar.SECOND, -1);
    return calendar.getTimeInMillis();
  }

  public static Long getCurrentDateTime() {
    return Calendar.getInstance().getTimeInMillis();
  }

  public static void main(String[] args) {
    System.out.println(getGapNDaysStart(-1));
    System.out.println(getGapNDaysEnd(-1));
    System.out.println(getGapNDaysStart(-2));
    System.out.println(getGapNDaysEnd(-2));
    System.out.println(getGapNDaysStart(-3));
    System.out.println(getGapNDaysEnd(-3));
  }

}
