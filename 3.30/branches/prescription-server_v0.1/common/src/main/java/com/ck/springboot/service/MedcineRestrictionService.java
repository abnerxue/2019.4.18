package com.ck.springboot.service;

import com.ck.springboot.pojo.MedcineRestriction;
import com.ck.springboot.pojo.ApplicableMedicine;
import com.ck.springboot.pojo.CourseOfTreatment;

import java.util.List;

public interface MedcineRestrictionService {
	
	List<MedcineRestriction> selectByDiagnosisId(Integer diagnosisId);//通过诊断ID获取限用药品设置
	
	List<ApplicableMedicine> selectApplicableByDiagnosisId(Integer diagnosisId);//通过诊断ID获取适用药品设置
	
	List<CourseOfTreatment> selectTreatmentByDiagnosisId(Integer diagnosisId);
	
	int updateTreatmentById(CourseOfTreatment course);
	/* 插入限用药品 */
	int insertDiagnsis(Integer diagnosisId, Integer medcineId, Integer action);

	/* 插入适用药品 */
	int insertDiagnsisApplicable(Integer diagnosisId, Integer medcineId);
	
	int updateDiagnsisRestriction(List<MedcineRestriction> diagnsisrestriction);

	/* 通过主键删除限用药品设置 */
	int deleteDiagnosisidMedcine(Integer id);
	/* 通过主键删除适用药品设置 */
	int deleteApplicableById(Integer id);
	
	int repeatVerification(Integer diagnosisId, Integer medcineId);
	
	int repeatVerificationApp(Integer diagnosisId, Integer medcineId);
}
