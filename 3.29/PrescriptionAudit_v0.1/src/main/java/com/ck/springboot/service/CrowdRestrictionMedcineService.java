package com.ck.springboot.service;

import java.util.List;
import com.ck.springboot.pojo.CrowdRestrictionMedcine;

public interface CrowdRestrictionMedcineService {
	
	public List<CrowdRestrictionMedcine> selectByMedcineId(Integer medcineId);
	
	
	public int updateByPrimaryKey(List<CrowdRestrictionMedcine> record);
	
	public int deleteCrowdById(Integer id);

}
