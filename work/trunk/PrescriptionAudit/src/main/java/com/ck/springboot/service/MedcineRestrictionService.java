package com.ck.springboot.service;

import java.util.List;

import com.ck.springboot.pojo.MedcineRestriction;
import com.ck.springboot.pojo.courseOfTreatment;

public interface MedcineRestrictionService {
	
	List<MedcineRestriction> selectByDiagnosisId(Integer diagnosisId);
	
	List<courseOfTreatment> selectTreatmentByDiagnosisId(Integer diagnosisId);
	
	int updateTreatmentById(courseOfTreatment course);
	
	int insertDiagnsis(Integer diagnosisId,Integer medcineId,Integer action);
	
	int updateDiagnsisRestriction(List<MedcineRestriction> diagnsisrestriction);
	
	int deleteDiagnosisidMedcine(Integer id);
		
	int repeatVerification(Integer diagnosisId,Integer medcineId);
}
