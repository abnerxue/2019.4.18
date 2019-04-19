package com.ck.springboot.mapper;

import com.ck.springboot.pojo.Dept;

import java.util.List;

public interface DeptMapper {
    int deleteDept(Integer deptId);

    int insertDept(Dept dept);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(Integer deptId);
    
    List<Dept> selectAllDept();

    int updateByPrimaryKeySelective(Dept record);

    int updateDept(Dept record);
}