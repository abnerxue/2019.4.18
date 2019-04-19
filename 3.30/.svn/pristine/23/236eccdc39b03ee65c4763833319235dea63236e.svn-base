package com.ck.springboot.mapper;

import com.ck.springboot.pojo.Diagnosis;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DiagnosisMapper {
    int insert(Diagnosis record);

    int insertSelective(Diagnosis record);
    
    public List<Diagnosis> getAllDiagnosisFilter(@Param("pageNo") Integer pageNo,
                                                 @Param("pageSize") Integer pageSize,
                                                 @Param("filter") String filter);
    
    public int getDiagnosisCount();
    
    public int getDiagnosisFilterCount(String filter);
    
    public List<Diagnosis> getAllDiagnosis(@Param("filter") String filter);
    
}