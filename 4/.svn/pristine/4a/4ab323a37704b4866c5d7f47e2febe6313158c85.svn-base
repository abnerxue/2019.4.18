package com.ck.springboot.service.impl;

import com.ck.springboot.mapper.MedcineRestrictionMapper;
import com.ck.springboot.pojo.MedcineRestriction;
import com.ck.springboot.pojo.ApplicableMedicine;
import com.ck.springboot.pojo.CourseOfTreatment;
import com.ck.springboot.service.MedcineRestrictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedcineRestrictionServicelpml implements MedcineRestrictionService {
	
	@Autowired
    MedcineRestrictionMapper medcineRestrictionMapper;

	@Override
	public List<MedcineRestriction> selectByDiagnosisId(Integer diagnosisId) {
		// TODO Auto-generated method stub
		return medcineRestrictionMapper.selectByDiagnosisId(diagnosisId);
	}

	@Override
	public int insertDiagnsis(Integer diagnosisId, Integer medcineId, Integer action) {
		// TODO Auto-generated method stub
		return medcineRestrictionMapper.insertDiagnsis(diagnosisId, medcineId, action);
	}

	@Override
	public int updateDiagnsisRestriction(List<MedcineRestriction> diagnsisrestriction) {
		// TODO Auto-generated method stub
		return medcineRestrictionMapper.updateDiagnsisRestriction(diagnsisrestriction);
	}

	@Override
	public List<CourseOfTreatment> selectTreatmentByDiagnosisId(Integer diagnosisId) {
		// TODO Auto-generated method stub
		return medcineRestrictionMapper.selectTreatmentByDiagnosisId(diagnosisId);
	}

	@Override
	public int updateTreatmentById(CourseOfTreatment course) {
		// TODO Auto-generated method stub
		return medcineRestrictionMapper.updateTreatmentById(course);
	}

	@Override
	public int deleteDiagnosisidMedcine(Integer id) {
		// TODO Auto-generated method stub
		return medcineRestrictionMapper.deleteDiagnosisidMedcine(id);
	}

	@Override
	public int repeatVerification(Integer diagnosisId, Integer medcineId) {
		// TODO Auto-generated method stub
		return medcineRestrictionMapper.repeatVerification(diagnosisId, medcineId);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.MedcineRestrictionService#selectApplicableByDiagnosisId(java.lang.Integer)
	 */
	@Override
	public List<ApplicableMedicine> selectApplicableByDiagnosisId(Integer diagnosisId) {
		// TODO Auto-generated method stub
		return medcineRestrictionMapper.selectApplicableByDiagnosisId(diagnosisId);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.MedcineRestrictionService#deleteApplicableById(java.lang.Integer)
	 */
	@Override
	public int deleteApplicableById(Integer id) {
		// TODO Auto-generated method stub
		return medcineRestrictionMapper.deleteApplicableById(id);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.MedcineRestrictionService#insertDiagnsisApplicable(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public int insertDiagnsisApplicable(Integer diagnosisId, Integer medcineId) {
		// TODO Auto-generated method stub
		return medcineRestrictionMapper.insertDiagnsisApplicable(diagnosisId, medcineId);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.MedcineRestrictionService#repeatVerificationApp(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public int repeatVerificationApp(Integer diagnosisId, Integer medcineId) {
		// TODO Auto-generated method stub
		return medcineRestrictionMapper.repeatVerificationApp(diagnosisId, medcineId);
	}
	

}
