package com.ck.springboot.mapper;

import com.ck.springboot.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompatibilityMapper {
    int deleteCompatibilityById(Integer id);

    int insertCompatibility(
            @Param("medcinePairId") Integer medcinePairId,
            @Param("medcineId") Integer medcineId,
            @Param("reactionDescription") String reactionDescription,
            @Param("action") Integer action);

    int insertSelective(CompatibilityMdM record);

	public List<CompatibilityMdM> selectMdM(Integer Id);//药品对药品
	public List<CompatibilityMdC> selectMdC(Integer Id);//药品对分类
	public List<CompatibilityMdL> selectMdL(Integer Id);//药品对标签

	public List<CompatibilityMdC> selectCdM(Integer Id);//分类对药品
	public List<CompatibilityCdC> selectCdC(Integer Id);//分类对分类
	public List<CompatibilityCdL> selectCdL(Integer Id);//分类对标签

    int updateByPrimaryKeySelective(CompatibilityMdM record);

    int updateByPrimaryKey(CompatibilityMdM record);

    int saveCompatibility(List<CompatibilityMdM> com);

    int repeatVerification(@Param("medcinePairId") Integer medcinePairId, @Param("medcineId") Integer medcineId);

}