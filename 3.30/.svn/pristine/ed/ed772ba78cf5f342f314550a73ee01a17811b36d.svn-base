package com.ck.springboot.mapper;

import com.ck.springboot.pojo.MedcineRestriction;
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
    
    List<MedcineRestriction> selectByDiagnosisId(@PathParam("diagnosisId") Integer diagnosisId);
    
    List<CourseOfTreatment> selectTreatmentByDiagnosisId(@Param("diagnosisId") Integer diagnosisId);
    
    int updateTreatmentById(CourseOfTreatment course);
    
    int insertDiagnsis(
            @Param("diagnosisId") Integer diagnosisId,
            @Param("medcineId") Integer medcineId,
            @Param("action") Integer action);

    int updateDiagnsisRestriction(@Param("diagnsisrestriction") List<MedcineRestriction> diagnsisrestriction);

	int deleteDiagnosisidMedcine(Integer id);

	int repeatVerification(@Param("diagnosisId") Integer diagnosisId, @Param("medcineId") Integer medcineId);
}