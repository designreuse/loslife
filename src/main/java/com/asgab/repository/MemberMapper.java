package com.asgab.repository;

import java.util.List;

import com.asgab.entity.Member;
import com.asgab.repository.mybatis.MyBatisRepository;

@MyBatisRepository
public interface MemberMapper {

	List<Member> getMembersByEnterpriseId(String enterpriseId);

}
