package com.ck.springboot.service;

import java.util.List;

import com.ck.springboot.pojo.Diagnosis;

public interface DiagnosisService {
	public List<Diagnosis> getAllDiagnosisFilter(Integer page,Integer pageSize,String filter);
	
	public List<Diagnosis> getAllDiagnosis(String filter);
	
	public int getDiagnosisCount();
	
	public int getDiagnosisFilterCount(String filter);

}
