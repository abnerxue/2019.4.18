package com.ck.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ck.springboot.pojo.Compatibility;
import com.ck.springboot.pojo.CompatibilityCdC;
import com.ck.springboot.pojo.CompatibilityCdL;
import com.ck.springboot.pojo.CompatibilityLdL;
import com.ck.springboot.pojo.CompatibilityMdC;
import com.ck.springboot.pojo.CompatibilityMdL;
import com.ck.springboot.pojo.CompatibilityMdM;

public interface CompatibilityMapper {

    int insertCompatibilityMdM(@Param("id")Integer id,@Param("pairId") Integer pairId,@Param("type") Integer type
    		,@Param("medcineName") String medcineName,@Param("medcinePairName") String medcinePairName);
    int insertCompatibilityMdC(@Param("id")Integer id,@Param("pairId") Integer pairId,@Param("type") Integer type
    		,@Param("medcineName") String medcineName);
    int insertCompatibilityMdL(@Param("id")Integer id,@Param("pairId") Integer pairId,@Param("type") Integer type
    		,@Param("medcineName") String medcineName);
    int insertCompatibilityCdC(@Param("id")Integer id,@Param("pairId") Integer pairId,@Param("type") Integer type);
    int insertCompatibilityCdL(@Param("id")Integer id,@Param("pairId") Integer pairId,@Param("type") Integer type);
    int insertCompatibilityLdL(@Param("id")Integer id,@Param("pairId") Integer pairId,@Param("type") Integer type);

    int insertSelective(CompatibilityMdM record);

	List<CompatibilityMdM> selectMdM(Integer id);//药品对药品
	List<CompatibilityMdC> selectMdC(Integer id);//药品对分类
	List<CompatibilityMdL> selectMdL(Integer id);//药品对标签
	
	List<CompatibilityMdC> selectCdM(Integer id);//分类对药品
	List<CompatibilityCdC> selectCdC(Integer id);//分类对分类
	List<CompatibilityCdL> selectCdL(Integer id);//分类对标签
	
	List<CompatibilityMdL> selectLdM(Integer id);//标签对药品
	List<CompatibilityCdL> selectLdC(Integer id);//标签对分类
	List<CompatibilityLdL> selectLdL(Integer id);//标签对标签
	
	int deleteCompatibilityMdM(@Param("id") Integer id,@Param("pairId") Integer pairId);
	int deleteCompatibilityMdC(@Param("id") Integer id,@Param("pairId") Integer pairId);
	int deleteCompatibilityMdL(@Param("id") Integer id,@Param("pairId") Integer pairId);
	int deleteCompatibilityCdC(@Param("id") Integer id,@Param("pairId") Integer pairId);
	int deleteCompatibilityCdL(@Param("id") Integer id,@Param("pairId") Integer pairId);
	int deleteCompatibilityLdL(@Param("id") Integer id,@Param("pairId") Integer pairId);

    int updateByPrimaryKeySelective(CompatibilityMdM record);

    int updateByPrimaryKey(CompatibilityMdM record);
    
    int saveCompatibilityMdM(Compatibility com);
	int saveCompatibilityMdC(Compatibility com);
	int saveCompatibilityMdL(Compatibility com);
	int saveCompatibilityCdC(Compatibility com);
	int saveCompatibilityCdL(Compatibility com);
	int saveCompatibilityLdL(Compatibility com);

    int repeatVerification(@Param("medcinePairId") Integer medcinePairId,@Param("medcineId") Integer medcineId);

}