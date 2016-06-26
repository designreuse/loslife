package com.asgab.service.recharge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.entity.RechargeRecord;
import com.asgab.repository.RechargeRecordMapper;

@Component
@Transactional
public class RechargeRecordService {

	@Autowired
	private RechargeRecordMapper rechargeRecordMapper;

	public RechargeRecord get(String id) {
		return rechargeRecordMapper.get(id);
	}

	public List<RechargeRecord> getByEnterpriseId(String enterpriseId) {
		return rechargeRecordMapper.getByEnterpriseId(enterpriseId);
	}

}
