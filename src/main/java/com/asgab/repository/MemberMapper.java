package com.asgab.repository;

import java.util.List;

import com.asgab.entity.Member;
import com.asgab.entity.MemberCard;
import com.asgab.entity.MemberCardAttrMap;
import com.asgab.repository.mybatis.MyBatisRepository;

@MyBatisRepository
public interface MemberMapper {

	List<Member> getMembersByEnterpriseId(String enterpriseId);
	
	List<MemberCard> getMemberCardsByEnterpriseId(String enterpriseId);
	
	List<MemberCardAttrMap> getRecordTimeMemberCardsByEnterpriseId(String enterpriseId);

}
