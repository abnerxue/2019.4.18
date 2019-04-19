package com.ck.springboot.service.impl;

import com.ck.springboot.mapper.CrowdRestrictionMedcineMapper;
import com.ck.springboot.pojo.CrowdRestrictionMedcine;
import com.ck.springboot.service.CrowdRestrictionMedcineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrowdRestrictionMedcineServicelpml implements CrowdRestrictionMedcineService {
	
	@Autowired CrowdRestrictionMedcineMapper crowdRestrictionMedcineMapper;
	
	@Override
	public List<CrowdRestrictionMedcine> selectByMedcineId(Integer medcineId) {
		// TODO Auto-generated method stub
		return crowdRestrictionMedcineMapper.selectCrowdByMedcineId(medcineId);
	}

	@Override
	public int updateByPrimaryKey(List<CrowdRestrictionMedcine> record) {
		// TODO Auto-generated method stub
		return crowdRestrictionMedcineMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteCrowdById(Integer id) {
		// TODO Auto-generated method stub
		return crowdRestrictionMedcineMapper.deleteCrowdById(id);
	}



}
