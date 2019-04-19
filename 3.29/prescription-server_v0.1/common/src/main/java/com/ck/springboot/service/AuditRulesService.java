package com.ck.springboot.service;

import com.ck.springboot.pojo.AuditRules;

import java.util.List;


public interface AuditRulesService {

	public List<AuditRules> getAllAuditRules();
	
	public String findAuditNameById(int id);
	
	public int updateAuditRules(List<AuditRules> auditrules);
	
	public int getAuditRulesCount();

}
