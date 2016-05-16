package com.asgab.service.process;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.entity.CustMaster;
import com.asgab.entity.Mail;
import com.asgab.entity.PayTranDetail;
import com.asgab.entity.PayTranHeader;
import com.asgab.entity.Process;
import com.asgab.entity.User;
import com.asgab.repository.CustMasterMapper;
import com.asgab.repository.ProcessMapper;
import com.asgab.repository.xmo.UserXMOMapper;
import com.asgab.service.account.ShiroDbRealm.ShiroUser;
import com.asgab.service.mail.MailService;
import com.asgab.service.paytran.PayTranHeaderService;
import com.asgab.util.CommonUtil;
import com.asgab.util.Cryptogram;
import com.asgab.web.AutoEmailNotification;

@Component
@Transactional
public class ProcessService {

  public static String SERVER_DOMAIN = "";

  private static String GROUP_FINANCE_CONFIRM = "Automation-財務端口加款";

  private static String GROUP_ADMIN = "Automation-Admin";

  static {
    ResourceBundle bundle = ResourceBundle.getBundle("mail/mail");
    SERVER_DOMAIN = bundle.getString("server.domain");
  }

  @Resource
  private ProcessMapper processMapper;

  @Autowired
  private PayTranHeaderService payTranHeaderService;

  @Resource
  private CustMasterMapper custMasterMapper;

  @Autowired
  private MailService mailService;

  @Resource
  private UserXMOMapper userXMOMapper;

  public List<Process> get(Map<String, Object> parms) {
    return processMapper.get(parms);
  }

  public Process getById(Long processId) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("processId", processId);
    List<Process> processes = get(map);
    return (processes != null && processes.size() == 1) ? processes.get(0) : null;
  }

  public void save(Process process) {
    processMapper.save(process);
  }

  public void update(Process process) {
    processMapper.update(process);
  }

  public List<Process> getProcessesByPaytranNums(Long paytranNum) {
    return processMapper.getProcessesByPaytranNums(paytranNum);
  }

  // 第一封邮件
  public Mail noticeFinance(Long paytranNum) {
    List<String> receiverGroups = getReceiverGroup(paytranNum, "finance");
    List<String> receivers = getReceiver(receiverGroups);
    String randomKey = UUID.randomUUID().toString();

    // 创建一个新的流程
    Process process = new Process();
    process.setCreateDate(new Date());
    process.setIsUpdated('0');
    process.setPayTranNum(paytranNum);
    process.setRandomKey(randomKey);
    process.setRandomIdentification(UUID.randomUUID().toString());
    process.setStatus(CommonUtil.STATUS_NEW);
    processMapper.save(process);

    Mail mail = new Mail();
    mail.setCreateDate(new Date());
    mail.setReceiver(receiverGroups.toString());
    mail.setReceivers(receivers);
    mail.setSubject("财务check");
    mail.setPaytranNum(paytranNum);
    mail.setTemplate(AutoEmailNotification.EMAIL_TEMPLATE_NAME_1);
    mail.setProcessId(process.getProcessId());
    mail.setUrlConfirm(SERVER_DOMAIN + "/process/toFinanceOpin/confirm/" + Cryptogram.encodeId(process.getProcessId() + "") + "/"
        + process.getRandomKey() + "/" + process.getRandomIdentification());
    mail.setUrlReject(SERVER_DOMAIN + "/process/toFinanceOpin/reject/" + Cryptogram.encodeId(process.getProcessId() + "") + "/"
        + process.getRandomKey() + "/" + process.getRandomIdentification());
    mailService.save(mail);
    return mail;
  }

  @Transactional(rollbackFor = Exception.class)
  public Mail reject(Process processDB, Long paytranNum, String description, Character status) {
    PayTranHeader payTranHeader = payTranHeaderService.get(paytranNum);
    payTranHeader.setDescription(description);
    payTranHeader.setStatus(status);
    payTranHeaderService.update(payTranHeader);
    if (processDB != null) {
      // 首先修改老的数据
      processDB.setStatus(status);
      processDB.setOperateDate(new Date());
      processDB.setOperateBy(getCurrentUser());
      processDB.setOperateByName(getCurrentUserName());
      processDB.setIsUpdated('1');
      processDB.setRemarks(status == '2' ? "财务查账邮件拒绝" : "财务加款邮件拒绝");
      processDB.setDescription(description);
      processMapper.update(processDB);
    } else {
      // 如果是页面点的. 数据库需要新增一条记录
      Process newProcess = new Process();
      newProcess.setPayTranNum(paytranNum);
      newProcess.setStatus(status);
      newProcess.setCreateDate(new Date());
      newProcess.setOperateDate(new Date());
      newProcess.setOperateBy(getCurrentUser());
      newProcess.setOperateByName(getCurrentUserName());
      newProcess.setIsUpdated('1');
      newProcess.setDescription(description);
      processMapper.save(newProcess);
    }
    // 发邮件给AM,客户重新上传资料
    List<String> groupNames = getReceiverGroup(paytranNum, "AM");
    List<String> receivers = getReceiver(groupNames);

    Mail mail = new Mail();
    mail.setReceiver(groupNames.toString());
    mail.setReceivers(receivers);
    // 不抄送给客户
    // mail.setCopyto(payTranHeader.getEmail());
    mail.setSubject("Payment transaction rejected");
    mail.setTemplate(AutoEmailNotification.EMAIL_TEMPLATE_NAME_REJECT);
    mail.setCreateDate(new Date());
    mail.setPaytranNum(paytranNum);
    mail.setDescription(description);
    mailService.save(mail);
    return mail;
  }

  @Transactional(rollbackFor = Exception.class)
  public Mail check(Process processDB, Long paytranNum) {
    PayTranHeader payTranHeader = payTranHeaderService.get(paytranNum);
    payTranHeader.setStatus(CommonUtil.STATUS_CHECK);
    payTranHeaderService.update(payTranHeader);
    // 首先修改老的数据
    if (processDB != null) {
      processDB.setStatus(CommonUtil.STATUS_CHECK);
      processDB.setOperateDate(new Date());
      processDB.setOperateBy(getCurrentUser());
      // 设置当前用户为审批人
      processDB.setOperateByName(getCurrentUserName());
      processDB.setIsUpdated('1');
      processDB.setRemarks("财务check通过");
      processMapper.update(processDB);
    }
    // 新建新的流程
    List<String> groupNames = getReceiverGroup(paytranNum, "finance_confirm");
    List<String> receivers = getReceiver(groupNames);

    String newRandomKey = UUID.randomUUID().toString();
    Process nextProcess = new Process();
    nextProcess.setPayTranNum(paytranNum);
    nextProcess.setRandomKey(newRandomKey);
    nextProcess.setRandomIdentification(UUID.randomUUID().toString());
    nextProcess.setStatus(CommonUtil.STATUS_FINANCE_CONFIRM);
    nextProcess.setCreateDate(new Date());
    nextProcess.setIsUpdated('0');
    processMapper.save(nextProcess);
    // 发邮件给finance confirm. 这里发邮件用nextProcess.processId
    Mail mail = new Mail();
    mail.setCreateDate(new Date());
    mail.setReceiver(groupNames.toString());
    mail.setReceivers(receivers);
    mail.setSubject("财务check通过,下一步confirm");
    mail.setTemplate(AutoEmailNotification.EMAIL_TEMPLATE_NAME_2);
    mail.setPaytranNum(paytranNum);
    mail.setProcessId(nextProcess.getProcessId());
    mail.setUrlConfirm(SERVER_DOMAIN + "/process/financeConfirm/" + Cryptogram.encodeId(nextProcess.getProcessId() + "") + "/"
        + nextProcess.getRandomKey() + "/" + nextProcess.getRandomIdentification());
    mail.setUrlReject(SERVER_DOMAIN + "/process/toFinanceReject/" + Cryptogram.encodeId(nextProcess.getProcessId() + "") + "/"
        + nextProcess.getRandomKey() + "/" + nextProcess.getRandomIdentification());
    mailService.save(mail);
    return mail;
  }

  @Transactional(rollbackFor = Exception.class)
  public Mail financeConfirm(Process processDB, Long paytranNum) {
    PayTranHeader payTranHeader = payTranHeaderService.get(paytranNum);
    payTranHeader.setStatus(CommonUtil.STATUS_FINANCE_CONFIRM);
    payTranHeaderService.update(payTranHeader);
    if (processDB != null) {
      // 修改老数据
      processDB.setStatus(CommonUtil.STATUS_FINANCE_CONFIRM);
      processDB.setOperateDate(new Date());
      processDB.setOperateBy(getCurrentUser());
      processDB.setOperateByName(getCurrentUserName());
      processDB.setIsUpdated('1');
      processDB.setRemarks("财务confirm通过");
      processMapper.update(processDB);
    }
    // 新建 发送给ops
    List<String> groupNames = getReceiverGroup(paytranNum, "ops");
    List<String> receivers = getReceiver(groupNames);

    String newRandomKey = UUID.randomUUID().toString();

    Process nextProcess = new Process();
    nextProcess.setPayTranNum(paytranNum);
    nextProcess.setRandomKey(newRandomKey);
    nextProcess.setRandomIdentification(UUID.randomUUID().toString());
    nextProcess.setStatus(CommonUtil.STATUS_OPS_CONFIRM);
    nextProcess.setCreateDate(new Date());
    nextProcess.setIsUpdated('0');
    processMapper.save(nextProcess);
    // 发邮件给AM confirm. 这里发邮件用nextProcess.processId
    Mail mail = new Mail();
    mail.setCreateDate(new Date());
    mail.setReceiver(groupNames.toString());
    mail.setReceivers(receivers);
    mail.setSubject("财务confirm. 发邮件给ops");
    mail.setTemplate(AutoEmailNotification.EMAIL_TEMPLATE_NAME_3);
    mail.setPaytranNum(paytranNum);
    mail.setProcessId(nextProcess.getProcessId());
    mail.setUrlConfirm(SERVER_DOMAIN + "/process/opsConfirm/" + Cryptogram.encodeId(nextProcess.getProcessId() + "") + "/"
        + nextProcess.getRandomKey() + "/" + nextProcess.getRandomIdentification());
    mailService.save(mail);
    return mail;
  }

  @Transactional(rollbackFor = Exception.class)
  public Mail opsConfirm(Process processDB, Long paytranNum) {
    PayTranHeader payTranHeader = payTranHeaderService.get(paytranNum);
    // opsConfirm 和 done
    payTranHeader.setStatus(CommonUtil.STATUS_DONE);
    payTranHeaderService.update(payTranHeader);
    if (processDB != null) {
      // 修改老数据
      processDB.setStatus(CommonUtil.STATUS_OPS_CONFIRM);
      processDB.setOperateDate(new Date());
      processDB.setOperateBy(getCurrentUser());
      processDB.setOperateByName(getCurrentUserName());
      processDB.setIsUpdated('1');
      processDB.setRemarks("Ops confirm通过");
      processMapper.update(processDB);
    }
    // 新建一条记录完成状态 最后一封发给AM和页面录入的邮箱
    List<String> groupNames = getReceiverGroup(paytranNum, "AM");
    List<String> receivers = getReceiver(groupNames);

    Process lastestProcess = new Process();
    lastestProcess.setPayTranNum(paytranNum);
    lastestProcess.setStatus(CommonUtil.STATUS_DONE);
    lastestProcess.setCreateDate(new Date());
    lastestProcess.setDescription("流程完成");
    processMapper.save(lastestProcess);
    // 通知所有人
    Mail mail = new Mail();
    mail.setCreateDate(new Date());
    mail.setReceiver(groupNames.toString());
    mail.setReceivers(receivers);
    mail.setSubject("流程完成");
    mail.setTemplate(AutoEmailNotification.EMAIL_TEMPLATE_NAME_4);
    mail.setPaytranNum(paytranNum);
    mail.setProcessId(lastestProcess.getProcessId());
    mailService.save(mail);
    return mail;
  }

  private String getCurrentUser() {
    ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    return user != null ? user.id + "" : "-";
  }

  private String getCurrentUserName() {
    ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    return user != null ? user.getName() + "" : "-";
  }

  public List<String> getReceiverGroup(Long paytranNum, String roles) {
    List<String> groupNames = new ArrayList<String>();
    PayTranHeader payTranHeader = payTranHeaderService.get(paytranNum);

    // 根据组从user里面找
    for (PayTranDetail payTranDetail : payTranHeader.getPayTranDetails()) {
      String bdUserName = payTranDetail.getBdUserName();
      CustMaster custMaster = custMasterMapper.findByCustUsername(bdUserName);
      if (custMaster != null) {
        String group_name = "";
        if ("finance".equalsIgnoreCase(roles)) {
          group_name = custMaster.getFin_email();
        } else if ("finance_confirm".equalsIgnoreCase(roles)) {
          group_name = GROUP_FINANCE_CONFIRM;
        } else if ("ops".equalsIgnoreCase(roles)) {
          group_name = custMaster.getOps_email();
        } else if ("AM".equalsIgnoreCase(roles)) {
          group_name = custMaster.getAm_email();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("group_name", group_name);
        List<User> users = userXMOMapper.findUsersByGroupName(map);
        if (users == null || (users != null && users.size() == 0)) {
          group_name = GROUP_ADMIN;
        }
        groupNames.add(group_name);
      }
    }
    CommonUtil.distinctList(groupNames);
    return groupNames;

  }

  private List<String> getReceiver(List<String> groupNames) {
    List<String> receivers = new ArrayList<String>();
    for (String groupName : groupNames) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("group_name", groupName);
      List<User> users = userXMOMapper.findUsersByGroupName(map);
      if (users != null && users.size() > 0) {
        for (User user : users) {
          if (StringUtils.isNotBlank(user.getEmail())) {
            receivers.add(user.getEmail());
          }
        }
      }
    }

    receivers.add("vanessa.chen@baiduhk.com.hk");
    receivers.add("chejuncheng@baiduhk.com.hk");
    receivers.add("billy.lee@baiduhk.com.hk");
    receivers.add("aurora.li@baiduhk.com.hk");
    receivers.add("wanghuiming@baiduhk.com.hk");
    receivers.add("kevin.lau@i-click.com");

    CommonUtil.distinctList(receivers);

    // receivers.clear();

    // receivers.add("iori.luo@i-click.com");
    // receivers.add("jack.sun@i-click.com");
    // receivers.add("siuvan.xia@i-click.com");

    return receivers;

  }
}
