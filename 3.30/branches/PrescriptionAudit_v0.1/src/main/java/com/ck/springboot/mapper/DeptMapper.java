package com.ck.springboot.mapper;

import java.util.List;

import com.ck.springboot.pojo.Dept;

public interface DeptMapper {
    int deleteDept(Integer deptId);

    int insertDept(Dept dept);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(Integer deptId);
    
    List<Dept> selectAllDept();

    int updateByPrimaryKeySelective(Dept record);

    int updateDept(Dept record);
}