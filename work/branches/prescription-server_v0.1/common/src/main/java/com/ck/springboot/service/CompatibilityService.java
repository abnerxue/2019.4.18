package com.ck.springboot.service;

import com.ck.springboot.pojo.*;

import java.util.List;

public interface CompatibilityService {
	
	public List<CompatibilityMdM> selectMdM(Integer Id);//药品对药品
	public List<CompatibilityMdC> selectMdC(Integer Id);//药品对分类
	public List<CompatibilityMdL> selectMdL(Integer Id);//药品对标签
	
	public List<CompatibilityMdC> selectCdM(Integer Id);//分类对药品
	public List<CompatibilityCdC> selectCdC(Integer Id);//分类对分类
	public List<CompatibilityCdL> selectCdL(Integer Id);//分类对标签

	
	public int deleteCompatibilityById(Integer id);
	
	public int insertCompatibility(Integer medcinePairId, Integer medcineId, String reactionDescription, Integer action);

	public int saveCompatibility(List<CompatibilityMdM> com);

	int repeatVerification(Integer medcinePairId, Integer medcineId);
	
}
