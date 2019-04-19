package com.ck.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ck.springboot.pojo.DeptRestriction;

public interface DeptRestrictionMapper {
	int deleteDeptById(Integer id);

    int insert(DeptRestriction record);

    int insertSelective(DeptRestriction record);

    DeptRestriction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeptRestriction record);

    int updateByPrimaryKey(@Param("record") List<DeptRestriction> record);
    
    List<DeptRestriction> selectByMedcineId(Integer medcineId);
}