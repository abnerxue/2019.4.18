package com.ck.springboot.mapper;

import com.ck.springboot.pojo.CrowdRestrictionMedcine;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CrowdRestrictionMedcineMapper {
    int insert(CrowdRestrictionMedcine record);

    int insertSelective(CrowdRestrictionMedcine record);
    
    List<CrowdRestrictionMedcine> selectCrowdByMedcineId(Integer medcineId);
    
	
	public int updateByPrimaryKey(@Param("record") List<CrowdRestrictionMedcine> record);
	
	public int deleteCrowdById(Integer id);
}