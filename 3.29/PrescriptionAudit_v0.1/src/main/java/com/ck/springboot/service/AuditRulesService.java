package com.ck.springboot.service;

import java.util.List;
import com.ck.springboot.pojo.AuditRules;



public interface AuditRulesService {

	public List<AuditRules> getAllAuditRules();
	
	public String findAuditNameById(int id);
	
	public int updateAuditRules(List<AuditRules> auditrules);
	
	public int getAuditRulesCount();

}
