package com.asgab.web.setting;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asgab.entity.DataSharing;
import com.asgab.service.account.AccountService;
import com.asgab.service.account.ShiroDbRealm.ShiroUser;
import com.asgab.service.setting.DataSharingService;
import com.asgab.util.CommonUtil;

@Controller
@RequestMapping(value = "/setting")
public class DataSharingController {

  @Autowired
  private DataSharingService dataSharingService;

  @Autowired
  private AccountService accountService;

  @RequestMapping(method = RequestMethod.GET)
  public String toSetting(Model model, HttpServletRequest request) {

    ShiroUser shiroUser = getCurrUser();
    if (shiroUser != null) {
      DataSharing currUserDataSharing = dataSharingService.getByUserId(shiroUser.id);
      if (currUserDataSharing == null) {
        model.addAttribute("action", "create");
        model.addAttribute("dataSharing", new DataSharing());
      } else {
        model.addAttribute("action", "update");
        model.addAttribute("dataSharing", currUserDataSharing);
      }
    } else {
      model.addAttribute("action", "create");
      model.addAttribute("dataSharing", new DataSharing());
    }
    model.addAttribute("users", accountService.getAllXMOUser());
    return "setting/settingForm";
  }

  @RequestMapping(value = "create", method = RequestMethod.POST)
  public String create(DataSharing dataSharing, HttpServletRequest request, RedirectAttributes ra) {
    dataSharing.setCreated_at(new Date());
    ShiroUser user = getCurrUser();
    if (user != null) {
      dataSharing.setUser_id(user.id);
      dataSharing.setCreated_by(user.id);
    }
    dataSharingService.save(dataSharing);
    ra.addFlashAttribute("message", CommonUtil.getProperty(request, "message.save.success"));
    return "redirect:/setting";
  }

  @RequestMapping(value = "update", method = RequestMethod.POST)
  public String update(@ModelAttribute("dataSharing") DataSharing dataSharing,
      HttpServletRequest request, RedirectAttributes ra) {
    dataSharingService.update(dataSharing);
    ra.addFlashAttribute("message", CommonUtil.getProperty(request, "message.save.success"));
    return "redirect:/setting";
  }

  @ModelAttribute
  public void getClient(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
    if (id != -1) {
      model.addAttribute("dataSharing", dataSharingService.get(id));
    }
  }

  private ShiroUser getCurrUser() {
    Subject currentUser = SecurityUtils.getSubject();
    if (null == currentUser)
      return null;
    return (ShiroUser) currentUser.getPrincipal();
  }
}
