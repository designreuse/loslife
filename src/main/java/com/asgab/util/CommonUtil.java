package com.asgab.util;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.asgab.entity.ExchangeRate;

public class CommonUtil {

  public static final PropertiesLoader properties_zh = new PropertiesLoader("message_zh.properties");
  public static final PropertiesLoader properties_en = new PropertiesLoader("message_en.properties");

  public static final Map<Character, String> STATUSES_ZH = new LinkedHashMap<Character, String>();
  public static final Map<Character, String> STATUSES_EN = new LinkedHashMap<Character, String>();
  public static final Map<String, String> CURRENCY_ZH = new TreeMap<String, String>();
  public static final Map<String, String> CURRENCY_EN = new TreeMap<String, String>();
  public static final Map<String, String> LOG_MODULE_ZH = new TreeMap<String, String>();
  public static final Map<String, String> LOG_MODULE_EN = new TreeMap<String, String>();
  public static final Map<String, String> LOG_OPERATE_TYPE_ZH = new TreeMap<String, String>();
  public static final Map<String, String> LOG_OPERATE_TYPE_EN = new TreeMap<String, String>();

  public static final Character STATUS_NEW = '1';
  public static final Character STATUS_CHECK = '3';
  public static final Character STATUS_REJECT = '2';
  public static final Character STATUS_FINANCE_CONFIRM = '4';
  public static final Character STATUS_FINANCE_REJECT = '7';
  public static final Character STATUS_OPS_CONFIRM = '5';
  public static final Character STATUS_DONE = '6';

  public static final String LOG_MODULE_CUSTOM_UPLOAD = "1";// 日志记录模块 - 客户信息上传
  public static final String LOG_MODULE_EXCHANGE_RATE = "2";// 日志记录模块 - 汇率信息变动
  public static final String LOG_MODULE_COSTOM = "3";// 日志记录模块 - 客户信息变动

  static {
    // N R C F O D
    STATUSES_EN.put('1', "New");
    STATUSES_EN.put('3', "Finance Checked");
    STATUSES_EN.put('2', "Finance Check Rejected");
    STATUSES_EN.put('4', "Finance Confirm");
    STATUSES_EN.put('7', "Finance Confirm Rejected");
    // STATUSES_EN.put('5', "Ops Confirm");
    STATUSES_EN.put('6', "Done");
    STATUSES_ZH.put('1', "新建");
    STATUSES_ZH.put('3', "财务查账确认");
    STATUSES_ZH.put('2', "财务查账拒绝");
    STATUSES_ZH.put('4', "财务加款至端口确认");
    STATUSES_ZH.put('7', "财务加款至端口拒绝");
    // STATUSES_ZH.put('5', "Ops确认");
    STATUSES_ZH.put('6', "完成");

    CURRENCY_EN.put("CNY", "CNY");
    CURRENCY_EN.put("HKD", "HKD");
    CURRENCY_EN.put("USD", "USD");
    CURRENCY_EN.put("SGD", "SGD");
    CURRENCY_ZH.put("CNY", "人民币");
    CURRENCY_ZH.put("HKD", "港币");
    CURRENCY_ZH.put("USD", "美元");
    CURRENCY_ZH.put("SGD", "新加坡元");

    LOG_MODULE_ZH.put("1", "客户信息上传");
    LOG_MODULE_ZH.put("2", "汇率");
    // LOG_MODULE_ZH.put("3", "客户"); TODO
    LOG_MODULE_EN.put("1", "Customer Upload");
    LOG_MODULE_EN.put("2", "Exchange Rate");
    // LOG_MODULE_EN.put("3", "Customer");TODO

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
   * @param file
   * @return 返回 fileName,showName,filePath
   */
  public static JSONObject saveFile(MultipartFile file, String uploadDir) {
    JSONObject jsonObject = null;
    String filePath = uploadDir + formatDate(new Date(), "yyyy-MM-dd");
    File fileDir = new File(filePath);
    if (!fileDir.exists()) {
      fileDir.mkdirs();
    }
    // 判断文件是否为空
    if (!file.isEmpty()) {
      try {
        int fileNameLegth = file.getOriginalFilename().lastIndexOf(".");
        String showName = file.getOriginalFilename().substring(0, fileNameLegth);
        String suffix = file.getOriginalFilename().substring(fileNameLegth, file.getOriginalFilename().length());
        String fileName = UUID.randomUUID().toString();
        // 生成当前日期文件夹
        // 转存文件
        file.transferTo(new File(filePath + "/" + fileName + suffix));
        jsonObject = new JSONObject();
        jsonObject.put("fileName", fileName + suffix);
        jsonObject.put("showName", showName + suffix);
        jsonObject.put("filePath", filePath);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return jsonObject;
  }

  // TODO
  /**
   * 其他币种转人民币
   * 
   * @param currency 货币
   * @param money
   * @return
   */
  public static Double transferMoneyToRMB(List<ExchangeRate> exchangeRates, String currency, Double money) {
    return transferMoneyToBigDecimalRMB(exchangeRates, currency, money).doubleValue();
  }

  public static BigDecimal transferMoneyToBigDecimalRMB(List<ExchangeRate> exchangeRates, String currency, Double money) {
    if ("CNY".equalsIgnoreCase(currency)) {
      return new BigDecimal(money);
    }
    for (int i = 0; i < exchangeRates.size(); i++) {
      // CNY-HKD 除法
      if ("HKD".equals(currency) && "CNY".equalsIgnoreCase(exchangeRates.get(i).getBase_currency())
          && "HKD".equalsIgnoreCase(exchangeRates.get(i).getCurrency())) {
        return new BigDecimal(money).divide(new BigDecimal(exchangeRates.get(i).getRate()), 2, BigDecimal.ROUND_HALF_UP);
      } else {
        // currency-CNY 乘法
        if (currency.equalsIgnoreCase(exchangeRates.get(i).getBase_currency()) && "CNY".equalsIgnoreCase(exchangeRates.get(i).getCurrency())) {
          return new BigDecimal(money).multiply(new BigDecimal(exchangeRates.get(i).getRate()));
        }

      }
    }
    return BigDecimal.ZERO;
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
    if (properties_zh == null || properties_en == null || !StringUtils.isNotBlank(key)) {
      return "";
    }

    return request.getLocale().getLanguage().equalsIgnoreCase("zh") ? properties_zh.getProperty(key) : properties_en.getProperty(key);
  }
}
