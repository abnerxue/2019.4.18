package com.ck.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ck.springboot.pojo.AuditRules;

@Mapper
public interface AuditRulesMapper {
	
	public List<AuditRules> getAllAuditRules();
	
	public String findAuditNameById(int id);
	
	int updateAuditRules(@Param("auditrules") List<AuditRules> auditrules);
	
	int getAuditRulesCount();
}
