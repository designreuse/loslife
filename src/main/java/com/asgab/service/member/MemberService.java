package com.asgab.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.entity.Member;
import com.asgab.repository.MemberMapper;

@Component
@Transactional
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	public List<Member> getMembersByEnterpriseId(String enterpriseId){
		return memberMapper.getMembersByEnterpriseId(enterpriseId);
	}

}
