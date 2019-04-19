package com.ck.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ck.springboot.pojo.DosageLimit;

public interface DosageLimitMapper {

    int insert(DosageLimit record);

    List<DosageLimit> selectByMedcineId(@Param("medcineId") Integer medcineId);//通过药品ID获取总剂量限用
    
	public List<DosageLimit> selectById(@Param("id") Integer id);//编辑时通过主键获取限用信息
    
	public int deleteDosageById(@Param("id") Integer id);
	
	public int updateDosageById(DosageLimit dos);
	
	public int insertDosage(DosageLimit dos);


    int updateByPrimaryKey(DosageLimit record);
}