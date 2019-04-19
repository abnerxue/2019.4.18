package com.ck.springboot.mapper;

import com.ck.springboot.pojo.AuditRules;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuditRulesMapper {
	
	public List<AuditRules> getAllAuditRules();
	
	public String findAuditNameById(int id);
	
	int updateAuditRules(@Param("auditrules") List<AuditRules> auditrules);
	
	int getAuditRulesCount();
}
