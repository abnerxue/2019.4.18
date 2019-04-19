package com.ck.springboot.mapper;

import com.ck.springboot.pojo.MedcineRestriction;
import com.ck.springboot.pojo.ApplicableMedicine;
import com.ck.springboot.pojo.CourseOfTreatment;
import org.apache.ibatis.annotations.Param;

import javax.websocket.server.PathParam;
import java.util.List;

public interface MedcineRestrictionMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(MedcineRestriction record);

    MedcineRestriction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MedcineRestriction record);

    int updateByPrimaryKey(MedcineRestriction record);

	/* 通过诊断ID获取限用药品设置 */
    List<MedcineRestriction> selectByDiagnosisId(@PathParam("diagnosisId") Integer diagnosisId);

	/* 通过诊断ID获取适用药品设置 */
	List<ApplicableMedicine> selectApplicableByDiagnosisId(Integer diagnosisId);
	
    List<CourseOfTreatment> selectTreatmentByDiagnosisId(@Param("diagnosisId") Integer diagnosisId);
    
    int updateTreatmentById(CourseOfTreatment course);
    /* 插入限用药品 */
    int insertDiagnsis(
            @Param("diagnosisId") Integer diagnosisId,
            @Param("medcineId") Integer medcineId,
            @Param("action") Integer action);

	/* 插入适用药品 */
	int insertDiagnsisApplicable(@Param("diagnosisId") Integer diagnosisId,
            @Param("medcineId") Integer medcineId);
	
    int updateDiagnsisRestriction(@Param("diagnsisrestriction") List<MedcineRestriction> diagnsisrestriction);
    
    /* 通过主键删除限用药品设置 */
	int deleteDiagnosisidMedcine(Integer id);
	/* 通过主键删除适用药品设置 */
	int deleteApplicableById(Integer id);
	
	int repeatVerification(@Param("diagnosisId") Integer diagnosisId, @Param("medcineId") Integer medcineId);
	int repeatVerificationApp(@Param("diagnosisId") Integer diagnosisId, @Param("medcineId") Integer medcineId);
}