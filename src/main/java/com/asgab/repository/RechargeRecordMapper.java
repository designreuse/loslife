package com.asgab.repository;

import java.util.List;

import com.asgab.entity.RechargeRecord;
import com.asgab.repository.mybatis.MyBatisRepository;

@MyBatisRepository
public interface RechargeRecordMapper {

	RechargeRecord get(String id);

	List<RechargeRecord> getByEnterpriseId(String enterpriseId);

}
