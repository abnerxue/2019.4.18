package com.ck.springboot.service.impl;

import com.ck.springboot.mapper.DiagnosisMapper;
import com.ck.springboot.pojo.Diagnosis;
import com.ck.springboot.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisServicelpml implements DiagnosisService{

	@Autowired DiagnosisMapper diagnosisMapper;
	
	@Override
	public List<Diagnosis> getAllDiagnosisFilter(Integer pageNo,Integer pageSize,String filter) {
		// TODO Auto-generated method stub
		return diagnosisMapper.getAllDiagnosisFilter(pageNo,pageSize,filter);
	}

	@Override
	public int getDiagnosisCount() {
		// TODO Auto-generated method stub
		return diagnosisMapper.getDiagnosisCount();
	}

	@Override
	public int getDiagnosisFilterCount(String filter) {
		// TODO Auto-generated method stub
		return diagnosisMapper.getDiagnosisFilterCount(filter);
	}

	@Override
	public List<Diagnosis> getAllDiagnosis(String filter) {
		// TODO Auto-generated method stub
		return diagnosisMapper.getAllDiagnosis(filter);
	}
}
