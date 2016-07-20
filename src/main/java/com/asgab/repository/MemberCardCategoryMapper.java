package com.asgab.repository;

import java.util.List;

import com.asgab.entity.MemberCardCategory;
import com.asgab.entity.RecordCateService;
import com.asgab.repository.mybatis.MyBatisRepository;

@MyBatisRepository
public interface MemberCardCategoryMapper {

	List<MemberCardCategory> getMemberCardCategoryByEnterpriseId(String enterpriseId);
	
	List<RecordCateService> getRecordCateServicesByEnterpriseId(String enterpriseId);

}
