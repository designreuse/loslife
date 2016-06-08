package com.asgab.web.client;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asgab.core.pagination.Page;
import com.asgab.entity.Client;
import com.asgab.service.account.ShiroDbRealm.ShiroUser;
import com.asgab.service.client.ClientContactService;
import com.asgab.service.client.ClientService;
import com.asgab.util.CommonUtil;
import com.asgab.util.Servlets;

@Controller
@RequestMapping(value = "/client")
public class ClientController {

  private static final String PAGE_SIZE = "10";

  @Autowired
  private ClientService clientService;
  @Autowired
  private ClientContactService clientContactService;

  @RequestMapping(method = RequestMethod.GET)
  public String list(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
      @RequestParam(value = "pageSize", defaultValue = PAGE_SIZE) int pageSize, @RequestParam(value = "sort", defaultValue = "id desc") String sort,
      HttpServletRequest request, Model model) {
    Map<String, Object> params = new HashMap<String, Object>();
    if (StringUtils.isNotBlank(request.getParameter("clientname"))) {
      params.put("clientname", request.getParameter("clientname"));
    }
    if (StringUtils.isNotBlank(request.getParameter("brand"))) {
      params.put("brand", request.getParameter("brand"));
    }
    model.addAttribute("search", Servlets.encodeParameterString(params));
    params.put("sort", sort);
    Page<Client> page = new Page<Client>(pageNumber, pageSize, sort, params);
    Page<Client> pages = clientService.search(page);
    model.addAttribute("pages", pages);
    clientService.setSelect(request);
    return "client/clientList";
  }

  @RequestMapping(value = "create", method = RequestMethod.GET)
  public String toCreate(Model model, HttpServletRequest request) {
    Client client = new Client();
    client.setCurrency_id(2);// 默认RMB
    model.addAttribute("client", client);
    model.addAttribute("action", "create");
    clientService.setSelect(request);
    return "client/clientForm";
  }

  @RequestMapping(value = "create", method = RequestMethod.POST)
  public String create(Client client, HttpServletRequest request, RedirectAttributes redirectAttributes) {

    ShiroUser user = getCurrUser();
    if (user != null) {
      client.setCreated_user(user.loginName);
      client.setUser_id(user.id);
    }
    client.setCreated_at(new Date());
    client.setStatus("Active");
    clientService.save(client);
    redirectAttributes.addFlashAttribute("message", CommonUtil.getProperty(request, "message.create.success"));
    return "redirect:/client";
  }

  @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
  public String view(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
    Map<String, Object> searchMap = new HashMap<String, Object>();
    searchMap.put("client_id", id);
    Client client = clientService.get(id);
    client.setContacts(clientContactService.getList(searchMap));
    model.addAttribute("client", client);
    clientService.setSelect(request);
    return "client/clientView";
  }

  @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
  public String toUpdate(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
    Map<String, Object> searchMap = new HashMap<String, Object>();
    searchMap.put("client_id", id);
    Client client = clientService.get(id);
    client.setContacts(clientContactService.getList(searchMap));
    model.addAttribute("client", client);
    model.addAttribute("action", "update");
    clientService.setSelect(request);
    return "client/clientForm";
  }

  @RequestMapping(value = "update", method = RequestMethod.POST)
  public String update(@ModelAttribute("client") Client client, HttpServletRequest request, RedirectAttributes redirectAttributes) {
    client.setUpdated_at(new Date());
    clientService.update(client);
    redirectAttributes.addFlashAttribute("message", CommonUtil.getProperty(request, "message.update.success"));
    return "redirect:/client";
  }

  @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
  public String delete(@PathVariable("id") Long id, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
    clientService.delete(id);
    redirectAttributes.addFlashAttribute("message", CommonUtil.getProperty(request, "message.delete.success"));
    return "redirect:/client";
  }

  @ModelAttribute
  public void getClient(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
    if (id != -1) {
      model.addAttribute("client", clientService.get(id));
    }
  }

  @RequestMapping(value = "addContact/{index}", method = RequestMethod.POST)
  @ResponseBody
  public String addContact(@PathVariable("index") int index, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("index", index);
    return "client/include/clientContact";
  }

  private ShiroUser getCurrUser() {
    Subject currentUser = SecurityUtils.getSubject();
    if (null == currentUser)
      return null;
    return (ShiroUser) currentUser.getPrincipal();
  }

}
