package com.asgab.service.custMaster;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.core.pagination.Page;
import com.asgab.entity.CustBaseInfo;
import com.asgab.entity.CustMaster;
import com.asgab.repository.CustMasterMapper;
import com.asgab.repository.xmo.UserXMOMapper;
import com.asgab.service.account.ShiroDbRealm.ShiroUser;

/**
 * 客户管理类.
 * 
 * @author siuvan
 */
// Spring Service Bean的标识.
@Component
@Transactional
public class CustMasterService {

  @Resource
  private CustMasterMapper custMasterMapper;

  @Resource
  private UserXMOMapper userXMOMapper;

  public List<CustMaster> getAllCustMaster() {
    return (List<CustMaster>) custMasterMapper.search(null);
  }

  public Page<CustMaster> getAllCustMaster(Page<CustMaster> page) {
    List<CustMaster> list = custMasterMapper.search(page.getSearchMap(), page.getRowBounds());
    int count = custMasterMapper.count(page.getSearchMap());
    page.setContent(list);
    page.setTotal(count);
    return page;
  }

  public CustMaster getCustMaster(Long id) {
    return custMasterMapper.get(id);
  }

  public void addCustMaster(CustMaster custMaster) {
    custMasterMapper.save(custMaster);
  }

  public void updateCustMaster(CustMaster custMaster) {
    custMasterMapper.update(custMaster);
  }

  public void deleteCustMaster(Long id) {
    custMasterMapper.delete(id);
  }

  public boolean existByCustUsername(String custUsername) {
    return custMasterMapper.existByCustUsername(custUsername) >= 1 ? true : false;
  }

  public CustMaster findByCustUsername(String custUsername) {
    return custMasterMapper.findByCustUsername(custUsername);
  }

  public boolean existGroupByGroupName(String groupName) {
    return userXMOMapper.findGroupByGroupName(groupName).size() >= 1 ? true : false;
  }

  public String addCustMasters(List<CustMaster> list) {
    int addRows = 0, updateRows = 0;
    for (CustMaster custMaster : list) {

      CustMaster db_CustMaster = custMasterMapper.findByCustUsername(custMaster.getCustUsername());
      ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
      custMaster.setCreateDate(new Date());
      custMaster.setCreateBy(user == null ? "" : user.id + "");
      if (db_CustMaster == null) {
        addCustMaster(custMaster);
        addRows++;
      } else {
        custMaster.setId(db_CustMaster.getId());
        custMaster.setDeleted(1);
        updateCustMaster(custMaster);
        updateRows++;
      }

    }
    return addRows + ":" + updateRows;
  }

  public List<CustBaseInfo> findCustBaseInfos(Map<String, Object> parms) {
    return (List<CustBaseInfo>) custMasterMapper.findCustBaseInfos(parms);
  }

  public Page<CustBaseInfo> findCustBaseInfos(Page<CustBaseInfo> page) {
    List<CustBaseInfo> list = custMasterMapper.findCustBaseInfos(page.getSearchMap(), page.getRowBounds());
    int count = custMasterMapper.countCustBaseInfos(page.getSearchMap());
    page.setContent(list);
    page.setTotal(count);
    return page;
  }
}
