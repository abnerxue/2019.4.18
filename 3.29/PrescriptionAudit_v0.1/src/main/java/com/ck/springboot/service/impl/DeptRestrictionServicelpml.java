package com.ck.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.springboot.mapper.DeptRestrictionMapper;
import com.ck.springboot.pojo.DeptRestriction;
import com.ck.springboot.service.DeptRestrictionService;
@Service
public class DeptRestrictionServicelpml  implements DeptRestrictionService{
	
	@Autowired DeptRestrictionMapper deptRestrictionMapper;
	
	@Override
	public List<DeptRestriction> selectByMedcineId(Integer medcineId) {
		// TODO Auto-generated method stub
		return deptRestrictionMapper.selectByMedcineId(medcineId);
	}

	@Override
	public int updateByPrimaryKey(List<DeptRestriction> record) {
		// TODO Auto-generated method stub
		return deptRestrictionMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteDeptById(Integer id) {
		// TODO Auto-generated method stub
		return deptRestrictionMapper.deleteDeptById(id);
	}

}
