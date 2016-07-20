package com.asgab.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.entity.MemberCardCategory;
import com.asgab.entity.RecordCateService;
import com.asgab.repository.MemberCardCategoryMapper;

@Component
@Transactional
public class MemberCardCategoryService {

	@Autowired
	private MemberCardCategoryMapper memberCardCategoryMapper;
	
	public List<MemberCardCategory> getMemberCardCategoryByEnterpriseId(String enterpriseId){
		return memberCardCategoryMapper.getMemberCardCategoryByEnterpriseId(enterpriseId);
	}
	
	public List<RecordCateService> getRecordCateServicesByEnterpriseId(String enterpriseId){
		return memberCardCategoryMapper.getRecordCateServicesByEnterpriseId(enterpriseId);
	}


}
