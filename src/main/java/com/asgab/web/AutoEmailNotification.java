package com.asgab.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.mail.MessagingException;

import org.apache.commons.lang3.StringUtils;

import com.asgab.core.mail.MailUtil;
import com.asgab.entity.CustMaster;
import com.asgab.entity.Mail;
import com.asgab.entity.PayTranAttachement;
import com.asgab.entity.PayTranDetail;
import com.asgab.entity.PayTranHeader;
import com.asgab.util.CommonUtil;
import com.asgab.util.MoneyToChinese;

import freemarker.template.TemplateException;

public class AutoEmailNotification {

  public final static String EMAIL_TEMPLATE_NAME_1 = "example1.ftl";

  public final static String EMAIL_TEMPLATE_NAME_2 = "example2.ftl";

  public final static String EMAIL_TEMPLATE_NAME_3 = "example3.ftl";

  public final static String EMAIL_TEMPLATE_NAME_4 = "example4.ftl";

  // 发送给创建paytran的email
  public final static String EMAIL_TEMPLATE_NAME_5 = "example5.ftl";

  public final static String EMAIL_TEMPLATE_NAME_REJECT = "reject.ftl";

  /***
   * send1 发送邮件 > Finance
   *
   * @param custMaster 客户信息
   * @param header 交易信息
   * @param parms 参数集合
   * @return success
   * @throws MessagingException
   * @throws TemplateException
   * @throws IOException
   */
  public static void send1(Map<String, CustMaster> custMasterMap, PayTranHeader header, Mail mail) throws Exception {

    Map<String, Object> map = new HashMap<String, Object>();
    // 公共头部
    getPublicHeader(map, header);
    // 收据上传日期
    map.put("uploadDate", header.getTranDateFormat());
    // 确认到账 / 拒绝到账 连接
    map.put("confirmArrivalURL", mail.getUrlConfirm());
    map.put("rejectArrivalURL", mail.getUrlReject());

    map.put("custUserList", getCustUserList(header, custMasterMap));
    MailUtil.sendMailAndFileByTemplate(mail.getReceivers(), mail.getSubject(), getFilePaths(header), map, EMAIL_TEMPLATE_NAME_1);
  }

  /***
   * send2 发送邮件 > Finance
   * 
   * @param custMaster 客户信息
   * @param header 交易信息
   * @param parms 参数集合
   * @return success
   */
  public static void send2(Map<String, CustMaster> custMasterMap, PayTranHeader header, Mail mail) throws Exception {

    Map<String, Object> map = new HashMap<String, Object>();
    // 公共头部
    getPublicHeader(map, header);
    // 收据上传日期
    map.put("uploadDate", header.getTranDateFormat());
    // 财务确认查账日期
    map.put("finConfirmAuditDate", mail.getFinConfirmAuditDate());
    // 财务查账人员
    map.put("finAuditBy", mail.getFinAuditBy());
    // 确认完成打款
    map.put("confirmPayTranURL", mail.getUrlConfirm());
    // 拒绝完成打款
    map.put("rejectPayTranURL", mail.getUrlReject());
    map.put("custUserList", getCustUserList(header, custMasterMap));

    MailUtil.sendMailAndFileByTemplate(mail.getReceivers(), mail.getSubject(), getFilePaths(header), map, EMAIL_TEMPLATE_NAME_2);

  }

  /***
   * send3 发送邮件 > Ops
   * 
   * @param custMaster 客户信息
   * @param header 交易信息
   * @return success
   */
  public static void send3(Map<String, CustMaster> custMasterMap, PayTranHeader header, Mail mail) throws Exception {

    Map<String, Object> map = new HashMap<String, Object>();
    // 公共头部
    getPublicHeader(map, header);
    // 财务确认收款日期
    map.put("finConfirmReceivableDate", mail.getFinConfirmReceivableDate());
    // 财务确认打款日期
    map.put("finPayTranDate", mail.getFinPayTranDate());
    // 财务确收人员
    map.put("finConfirmReceivableBy", mail.getFinConfirmReceivableBy());
    // 财务打款人员
    map.put("finPayTranBy", mail.getFinPayTranBy());

    // 确认入账至客户用户名连接
    map.put("confirmCustArrivalURL", mail.getUrlConfirm());

    map.put("custUserList", getCustUserList(header, custMasterMap));
    map.put("annualInfoList", getAnnualInfoList(header, custMasterMap));

    MailUtil.sendMailAndFileByTemplate(mail.getReceivers(), mail.getSubject(), getFilePaths(header), map, EMAIL_TEMPLATE_NAME_3);

  }

  /***
   * send4 发送邮件 > AM & Sales
   * 
   * @param custMaster 客户信息
   * @param header 交易信息
   * @param parms 参数集合
   * @return success
   */
  public static void send4(Map<String, CustMaster> custMasterMap, PayTranHeader header, Mail mail) throws Exception {

    Map<String, Object> map = new HashMap<String, Object>();
    // 公共头部
    getPublicHeader(map, header);
    // 财务查账日期
    map.put("finConfirmReceivableDate", mail.getFinConfirmReceivableDate());
    // 财务确认打款日期
    map.put("finPayTranDate", mail.getFinPayTranDate());
    // ops确认加款日期
    map.put("opsPayTranDate", mail.getOpsPayTranDate());

    // 财务确收人员
    map.put("finConfirmReceivableBy", mail.getFinConfirmReceivableBy());
    // 财务打款人员
    map.put("finPayTranBy", mail.getFinPayTranBy());
    // ops 确认加款人
    map.put("opsPayTranBy", mail.getOpsPayTranBy());

    map.put("custUserList", getCustUserList(header, custMasterMap));
    map.put("annualInfoList", getAnnualInfoList(header, custMasterMap));

    MailUtil.sendMailAndFileByTemplate(mail.getReceivers(), mail.getSubject(), getFilePaths(header), map, EMAIL_TEMPLATE_NAME_4);
  }

  /**
   * 发送邮件给paytran email
   * 
   * @param custMasterMap
   * @param header
   * @param parms
   * @throws Exception
   */
  public static void send5(PayTranHeader header, Mail mail) throws Exception {

    Map<String, Object> map = new HashMap<String, Object>();
    // 公共头部
    getPublicHeader(map, header);
    map.put("currency_en", getDecodedCurrency(header.getCurrency(), "en"));
    // 数据上传时间
    map.put("uploadDate", CommonUtil.formatDate(header.getCreateDate()));
    // 确认日期
    map.put("finPayTranDate", CommonUtil.formatDate(header.getUpdateDate()));
    List<Map<String, Object>> details = new ArrayList<Map<String, Object>>();
    for (PayTranDetail detail : header.getPayTranDetails()) {
      Map<String, Object> detailMap = new HashMap<String, Object>();
      detailMap.put("bdUserName", detail.getBdUserName());
      detailMap.put("payCode", detail.getPayCodeZH());
      detailMap.put("payCode_en", detail.getPayCodeEN());
      detailMap.put("amount", CommonUtil.digSeg(detail.getAmount()));
      detailMap.put("amountInRMB", CommonUtil.digSeg(detail.getAmountInRMB()));
      detailMap.put("additionAmount", CommonUtil.digSeg(detail.getAdditionAmount()));
      details.add(detailMap);
    }
    map.put("details", details);

    MailUtil.sendMailAndFileByTemplate(mail.getReceivers(), mail.getSubject(), getFilePaths(header), map, EMAIL_TEMPLATE_NAME_5);
  }

  /***
   * 财务拒绝到账
   * 
   * @param receiver 收件人
   * @param subject 主题
   * @param content 内容
   * @return
   */
  public static void send_reject(Mail mail, Map<String, CustMaster> custMasterMap, PayTranHeader header) throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    // 公共头部
    getPublicHeader(map, header);

    map.put("title", header.getStatus() == '2' ? "财务查账邮件拒绝" : "财务加款邮件拒绝");
    map.put("paytranNum", header.getTranNum() + "");
    map.put("description", StringUtils.isNotBlank(mail.getDescription()) ? mail.getDescription() : "");
    map.put("custEmail", header.getEmail());
    map.put("custUserList", getCustUserList(header, custMasterMap));
    map.put("annualInfoList", getAnnualInfoList(header, custMasterMap));

    MailUtil.sendMailAndFileByTemplate(mail.getReceivers(), mail.getSubject(), getFilePaths(header), map, EMAIL_TEMPLATE_NAME_REJECT);

  }


  /***
   * 返回一个字符串
   * 
   * @param object
   * @return
   */
  private static String getValue(Object object) {
    if (object == null)
      return "";

    if (object instanceof String)
      return object.toString();

    if (object instanceof Date)
      return CommonUtil.formatDate((Date) object, "yyyy-MM-dd HH:mm:ss");

    return "";
  }

  private static void getPublicHeader(Map<String, Object> map, PayTranHeader header) {
    // 交易号
    map.put("tradeNo", String.valueOf(header.getTranNum()));
    // 币种
    map.put("currency", getDecodedCurrency(header.getCurrency()));
    map.put("totalAmount", CommonUtil.digSeg(header.getTotalAmount()));
    map.put("totalAmountInRMB",
        CommonUtil.digSeg(CommonUtil.transferMoneyToRMB(header.getExchangeRates(), header.getCurrency(), header.getTotalAmount())));
    // 账单备注
    map.put("paymentRemark", getValue(header.getRemarks()));
  }

  /**
   * 公共的部分
   */
  private static List<Map<String, String>> getCustUserList(PayTranHeader header, Map<String, CustMaster> custMasterMap) {
    List<Map<String, String>> custUserList = new ArrayList<Map<String, String>>();
    for (int i = 0; i < header.getPayTranDetails().size(); i++) {
      PayTranDetail detail = header.getPayTranDetails().get(i);
      String baiduUserName = detail.getBdUserName();
      double amount = detail.getAmount();
      // 每一个百度用户添加一列数据
      Map<String, String> custUserMap = new HashMap<String, String>();
      // 获取对于的百度客户信息
      CustMaster custMaster = custMasterMap.get(baiduUserName);
      boolean existCustMaster = custMaster != null;
      // 客户用户名
      custUserMap.put("custUsername", baiduUserName);
      // 客户名称
      custUserMap.put("custName", existCustMaster ? custMaster.getCustName() : "");

      // 入账金额小写
      custUserMap.put("rzjeLowercase", CommonUtil.digSeg(amount));
      // 入账金额大写
      custUserMap.put("rzjeCapital", MoneyToChinese.getMoneyString(amount));
      // 加款金额小写
      custUserMap.put("jkjeLowercase", CommonUtil.digSeg(detail.getAdditionAmount()));
      // 加款金额大写
      custUserMap.put("jkjeCapital", MoneyToChinese.getMoneyString(detail.getAdditionAmount()));
      // 入账项目
      String item = "";
      boolean isTopup = false;
      if ("加款".equalsIgnoreCase(detail.getPayCodeZH()) || "Topup".equalsIgnoreCase(detail.getPayCodeEN())) {
        // 如果是加款
        isTopup = true;
        item = "管理费 " + CommonUtil.digSeg(Double.valueOf(detail.getMgtFee()) * 100) + "%";
      } else {
        item = detail.getDecodePayCode();
      }
      custUserMap.put("item", item);
      // 如果是年费,开户费,保证金不计算返点(金额)
      if (!isTopup) {
        // 返点率
        custUserMap.put("rebate", "0.00");
        // 返点金额
        custUserMap.put("rebateAmount", "0.00");
      } else {
        // 返点率
        custUserMap.put("rebate", CommonUtil.digSeg(detail.getRewards() * 100) + "%");
        // 返点金额
        custUserMap.put("rebateAmount", CommonUtil.digSeg(detail.getRebateValue()));
      }
      // 赠送金额
      custUserMap.put("giftAmount", "0.00");

      // 端口
      custUserMap.put("custPort", existCustMaster ? custMaster.getCustPort() : "");
      // 销售
      custUserMap.put("sales_contact", existCustMaster ? custMaster.getSales_contact() : "");
      // 客服
      custUserMap.put("custService", existCustMaster ? custMaster.getAm_contact() : "");
      // 代理商
      custUserMap.put("agent", existCustMaster ? custMaster.getCustName() : "");
      custUserList.add(custUserMap);
    }

    return custUserList;
  }


  /*
   * private static String getRebateValue(String currency,Double mgtFee,PayTranDetail detail,
   * CustMaster custMaster) { // 如果是SZ ,港币 返点金额= 客户入账金额/汇率/(1+管理费)*续费返点 (前面的一段通过AmountInRmb解决)
   * AmountInRmb如果是香港就是/汇率,不是香港就是*汇率 // 如果是SZ ,其他 返点金额= 客户入账金额*汇率/(1+管理费)*续费返点 // 如果是HK, 港币 返点金额=
   * 加款金额*续费返点 // 如果是HK, 其他 返点金额= 加款金额*续费返点 BigDecimal rebateValue = BigDecimal.ZERO;
   * if(custMaster.getFin_email().contains("SZ")&&"HKD".equalsIgnoreCase(currency)){ // rebateValue
   * = new BigDecimal(detail.getAmountInRMB()).divide(BigDecimal.ONE.add(new BigDecimal(mgtFee)) ,2,
   * BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(detail.getRewards()));
   * 
   * }else if(custMaster.getFin_email().contains("SZ") && !"HKD".equalsIgnoreCase(currency) ){ //
   * rebateValue = new BigDecimal(detail.getAmountInRMB()).divide(BigDecimal.ONE.add(new
   * BigDecimal(mgtFee)) ,2, BigDecimal.ROUND_HALF_UP).multiply(new
   * BigDecimal(detail.getRewards()));
   * 
   * }else if(custMaster.getFin_email().contains("HK") && "HKD".equalsIgnoreCase(currency)){ //
   * rebateValue = new BigDecimal(detail.getAdditionAmount()).multiply(new
   * BigDecimal(detail.getRewards()));
   * 
   * }else if(custMaster.getFin_email().contains("HK") && !"HKD".equalsIgnoreCase(currency)){
   * 
   * rebateValue = new BigDecimal(detail.getAdditionAmount()).multiply(new
   * BigDecimal(detail.getRewards()));
   * 
   * } return CommonUtil.reserved2Digit(rebateValue.doubleValue()); }
   */

  /**
   * 公共部分2 用于模板34
   * 
   * @param header
   * @param custMasterMap
   * @return
   */
  private static List<Map<String, String>> getAnnualInfoList(PayTranHeader header, Map<String, CustMaster> custMasterMap) {
    List<Map<String, String>> annualInfoList = new ArrayList<Map<String, String>>();
    List<String> distinctBDUserNameList = getDistinctCustUserName(header.getPayTranDetails());
    for (String bdUserName : distinctBDUserNameList) {
      Map<String, String> tmpMap = new HashMap<String, String>();
      // 获取对于的百度客户信息
      CustMaster custMaster = custMasterMap.get(bdUserName);
      boolean existCustMaster = custMaster != null;
      // 公司名称
      tmpMap.put("custName", existCustMaster ? custMaster.getCustName() : "");
      // 收取年服务费时间
      tmpMap.put("annualSvcFeeDate", existCustMaster ? CommonUtil.formatDate(custMaster.getAnnualSvcFeeDate(), "yyyy年MM月") : "");
      // 续费返点率
      Double tmpRewardsPercent = Double.parseDouble(custMaster.getRewardsPercent());
      tmpMap.put("rewardsPercent", tmpRewardsPercent.doubleValue() == 0 ? "0" : tmpRewardsPercent * 100 + "%");
      // 收取的年服务费
      Double tmpAnnualSvcFee = Double.parseDouble(custMaster.getAnnualSvcFee());
      tmpMap.put("annualSvcFee", tmpAnnualSvcFee.doubleValue() == 0 ? "0" : tmpAnnualSvcFee + "");
      // 管理费率
      Double tmpMgtFeePercent = Double.parseDouble(custMaster.getMgtFeePercent());
      tmpMap.put("mgtFeePercent", tmpMgtFeePercent.doubleValue() == 0 ? "0" : tmpMgtFeePercent * 100 + "%");
      // 备注
      tmpMap.put("remark1", StringUtils.isNotBlank(custMaster.getRemark1()) ? custMaster.getRemark1() : "");
      annualInfoList.add(tmpMap);
    }
    return annualInfoList;
  }

  private static List<String> getDistinctCustUserName(List<PayTranDetail> payTranDetails) {
    Map<String, String> map = new HashMap<String, String>();
    for (PayTranDetail detail : payTranDetails) {
      map.put(detail.getBdUserName(), detail.getBdUserName());
    }
    List<String> bdUserNameList = new ArrayList<String>();
    Set<Entry<String, String>> bdSet = map.entrySet();
    Iterator<Entry<String, String>> bdIterator = bdSet.iterator();
    while (bdIterator.hasNext()) {
      bdUserNameList.add(bdIterator.next().getKey());
    }
    return bdUserNameList;
  }

  private static String getDecodedCurrency(String currency) {
    return getDecodedCurrency(currency, "zh");
  }

  private static String getDecodedCurrency(String currency, String language) {
    Iterator<Entry<String, String>> iterator =
        ("zh".equalsIgnoreCase(language) ? CommonUtil.CURRENCY_ZH : CommonUtil.CURRENCY_EN).entrySet().iterator();
    while (iterator.hasNext()) {
      Entry<String, String> entry = iterator.next();
      if (entry.getKey().equalsIgnoreCase(currency)) {
        return entry.getValue();
      }
    }

    return "";
  }

  private static List<String> getFilePaths(PayTranHeader payTranHeader) {
    List<String> filePaths = new ArrayList<String>();
    List<PayTranAttachement> attachments = payTranHeader.getPayTranAttachements();
    for (PayTranAttachement attachment : attachments) {
      filePaths.add(attachment.getPath() + "/" + attachment.getFileName());
    }
    return filePaths;
  }
}
