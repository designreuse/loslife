package com.asgab.web.ajax;

import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asgab.entity.BackupHistory;
import com.asgab.entity.User;
import com.asgab.service.enterprise.EnterpriseService;
import com.asgab.service.user.UserService;
import com.asgab.util.CommonUtil;

@Controller
@RequestMapping(value = "/ajax")
public class AjaxController {

  @Autowired
  private UserService userService;

  @Autowired
  private EnterpriseService enterpriseService;

  @RequestMapping(value = "view/{id}", method = RequestMethod.POST)
  public String view(HttpServletRequest request, @PathVariable("id") String id, Model model) {
    User user = userService.get(id);
    model.addAttribute("user", user);
    return "user/modalDetail";
  }

  @RequestMapping(value = "backup/{id}", method = RequestMethod.POST)
  public String backup(HttpServletRequest request, @PathVariable("id") String id, Model model) {
    List<BackupHistory> backupHistories = userService.getBackupHistory(id);
    List<String> dateList = new ArrayList<String>();
    for (BackupHistory b : backupHistories) {
      dateList.add(b.getFmtBackup_time());
    }
    CommonUtil.distinctList(dateList);
    List<List<BackupHistory>> target = new ArrayList<>();
    for (String s : dateList) {
      List<BackupHistory> tmpBackupHistory = new ArrayList<BackupHistory>();
      for (BackupHistory b : backupHistories) {
        if (s.equalsIgnoreCase(b.getFmtBackup_time())) {
          tmpBackupHistory.add(b);
        }
      }
      target.add(tmpBackupHistory);
    }

    model.addAttribute("backupHistories", target);
    return "user/modalBackupHistory";
  }

  @ResponseBody
  @RequestMapping(value = "get3DaysBackupHistory/{id}", method = RequestMethod.POST)
  public String get3DaysBackupHistory(HttpServletRequest request, @PathVariable("id") String id, Model model) {
    return userService.countBackupHistorysByEnterpriseId(id);
  }

  @ResponseBody
  @RequestMapping(value = "unlockDevice/{admin_account}", method = RequestMethod.POST)
  public String unlockDevice(HttpServletRequest request, @PathVariable("admin_account") String admin_account, Model model) {
    return userService.unlockDevice(admin_account) == -1 ? "false" : "true";
  }

  @RequestMapping(value = "export/{enterpriseId}")
  public void exportEnterprise(HttpServletResponse response, @PathVariable("enterpriseId") String enterpriseId) {
    InputStream is = AjaxController.class.getClassLoader().getResourceAsStream("excel/商户信息.xlsx");
    XSSFWorkbook workbook = enterpriseService.exportEnterprise(is, enterpriseId);
    // 往客户端传送文件流
    response.setContentType("application/x-msdownload");
    try {
      response.setHeader("Content-Disposition",
          "attachment;filename=" + new String(URLDecoder.decode("商户信息.xlsx", "UTF-8").getBytes(), "iso-8859-1"));
      // 最终写入文件
      workbook.write(response.getOutputStream());
      response.getOutputStream().flush();
      response.getOutputStream().close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
