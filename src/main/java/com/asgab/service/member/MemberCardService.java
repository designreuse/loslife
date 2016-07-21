package com.asgab.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.entity.MemberCard;
import com.asgab.entity.MemberCardAttrMap;
import com.asgab.repository.MemberMapper;

@Component
@Transactional
public class MemberCardService {

  @Autowired
  private MemberMapper memberMapper;

  public List<MemberCard> getMemberCardsByEnterpriseId(String enterpriseId) {
    List<MemberCard> membercards = memberMapper.getMemberCardsByEnterpriseId(enterpriseId);
    if (membercards != null && membercards.size() > 0) {
      List<MemberCardAttrMap> recordTimeMemberCardsAttrMap = memberMapper.getRecordTimeMemberCardsByEnterpriseId(enterpriseId);
      for (MemberCard memberCard : membercards) {
        if ("recordTimeCard".equalsIgnoreCase(memberCard.getBaseInfo_type())) {
          setMemberCardAttr(memberCard, recordTimeMemberCardsAttrMap);
        }
      }
    }
    return membercards;
  }

  private void setMemberCardAttr(MemberCard memberCard, List<MemberCardAttrMap> recordTimeMemberCardsAttrMap) {
    if (recordTimeMemberCardsAttrMap != null && recordTimeMemberCardsAttrMap.size() > 0) {
      for (MemberCardAttrMap cardAttr : recordTimeMemberCardsAttrMap) {
        if (cardAttr.getMemberCardId().equals(memberCard.getId())) {
          memberCard.getMemberCardAttrMaps().add(cardAttr);
        }
      }
    }
  }

  private List<MemberCardAttrMap> getRecordTimeMemberCardsByEnterpriseId(String enterpriseId) {
    return memberMapper.getRecordTimeMemberCardsByEnterpriseId(enterpriseId);
  }

}
