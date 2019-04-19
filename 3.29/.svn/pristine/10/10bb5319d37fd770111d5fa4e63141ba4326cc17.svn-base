package com.ck.springboot.service.impl;

import com.ck.springboot.mapper.AuditRulesMapper;
import com.ck.springboot.pojo.AuditRules;
import com.ck.springboot.service.AuditRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditRulesServicelpml implements AuditRulesService{
	@Autowired
	AuditRulesMapper auditRulesMapper;

	@Override
	public List<AuditRules> getAllAuditRules() {
		// TODO Auto-generated method stub
		return auditRulesMapper.getAllAuditRules();
	}

	@Override
	public String findAuditNameById(int id) {
		// TODO Auto-generated method stub
		return auditRulesMapper.findAuditNameById(id);
	}

	@Override
	public int updateAuditRules(List<AuditRules> auditrules) {
		// TODO Auto-generated method stub
		return auditRulesMapper.updateAuditRules(auditrules);
	}

	@Override
	public int getAuditRulesCount() {
		// TODO Auto-generated method stub
		return auditRulesMapper.getAuditRulesCount();
	}
}
