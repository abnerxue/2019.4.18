package com.ck.springboot.mapper;

import com.ck.springboot.pojo.HospitalStaff;

public interface HospitalStaffMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HospitalStaff record);

    int insertSelective(HospitalStaff record);

    HospitalStaff selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HospitalStaff record);

    int updateByPrimaryKey(HospitalStaff record);
}