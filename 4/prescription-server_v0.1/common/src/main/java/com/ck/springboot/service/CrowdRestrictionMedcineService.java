package com.ck.springboot.service;

import com.ck.springboot.pojo.CrowdRestrictionMedcine;

import java.util.List;

public interface CrowdRestrictionMedcineService {
	
	public List<CrowdRestrictionMedcine> selectByMedcineId(Integer medcineId);
	
	
	public int updateByPrimaryKey(List<CrowdRestrictionMedcine> record);
	
	public int deleteCrowdById(Integer id);

}
