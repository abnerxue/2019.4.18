package com.ck.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.ck.springboot.pojo.Diagnosis;

public interface DiagnosisMapper {
    int insert(Diagnosis record);

    int insertSelective(Diagnosis record);
    
    public List<Diagnosis> getAllDiagnosisFilter(@Param("pageNo") Integer pageNo,
    		@Param("pageSize")Integer pageSize,
    		@Param("filter") String filter);
    
    public int getDiagnosisCount();
    
    public int getDiagnosisFilterCount(String filter);
    
    public List<Diagnosis> getAllDiagnosis(@Param("filter") String filter);
    
}