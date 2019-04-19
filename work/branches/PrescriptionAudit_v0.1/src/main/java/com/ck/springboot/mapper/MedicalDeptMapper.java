package com.ck.springboot.mapper;

import com.ck.springboot.pojo.MedicalDept;

public interface MedicalDeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MedicalDept record);

    int insertSelective(MedicalDept record);

    MedicalDept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MedicalDept record);

    int updateByPrimaryKey(MedicalDept record);
}