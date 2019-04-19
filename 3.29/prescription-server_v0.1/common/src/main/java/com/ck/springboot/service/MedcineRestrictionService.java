package com.ck.springboot.service;

import com.ck.springboot.pojo.MedcineRestriction;
import com.ck.springboot.pojo.CourseOfTreatment;

import java.util.List;

public interface MedcineRestrictionService {
	
	List<MedcineRestriction> selectByDiagnosisId(Integer diagnosisId);
	
	List<CourseOfTreatment> selectTreatmentByDiagnosisId(Integer diagnosisId);
	
	int updateTreatmentById(CourseOfTreatment course);
	
	int insertDiagnsis(Integer diagnosisId, Integer medcineId, Integer action);

	int updateDiagnsisRestriction(List<MedcineRestriction> diagnsisrestriction);

	int deleteDiagnosisidMedcine(Integer id);

	int repeatVerification(Integer diagnosisId, Integer medcineId);
}
