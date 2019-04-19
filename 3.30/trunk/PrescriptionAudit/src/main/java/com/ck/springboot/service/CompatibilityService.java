package com.ck.springboot.service;

import java.util.List;

import com.ck.springboot.pojo.Compatibility;
import com.ck.springboot.pojo.CompatibilityCdC;
import com.ck.springboot.pojo.CompatibilityCdL;
import com.ck.springboot.pojo.CompatibilityLdL;
import com.ck.springboot.pojo.CompatibilityMdC;
import com.ck.springboot.pojo.CompatibilityMdL;
import com.ck.springboot.pojo.CompatibilityMdM;

public interface CompatibilityService {
	
	public List<CompatibilityMdM> selectMdM(Integer id);//药品对药品
	public List<CompatibilityMdC> selectMdC(Integer id);//药品对分类
	public List<CompatibilityMdL> selectMdL(Integer id);//药品对标签
	
	public List<CompatibilityMdC> selectCdM(Integer id);//分类对药品
	public List<CompatibilityCdC> selectCdC(Integer id);//分类对分类
	public List<CompatibilityCdL> selectCdL(Integer id);//分类对标签
	
	public List<CompatibilityMdL> selectLdM(Integer id);//标签对药品
	public List<CompatibilityCdL> selectLdC(Integer id);//标签对分类
	public List<CompatibilityLdL> selectLdL(Integer id);//标签对标签

	
	public int deleteCompatibilityMdM(Integer id,Integer pairId);
	public int deleteCompatibilityMdC(Integer id,Integer pairId);
	public int deleteCompatibilityMdL(Integer id,Integer pairId);
	public int deleteCompatibilityCdC(Integer id,Integer pairId);
	public int deleteCompatibilityCdL(Integer id,Integer pairId);
	public int deleteCompatibilityLdL(Integer id,Integer pairId);
	
	public int insertCompatibilityMdM(Integer id,Integer pairId,Integer type,String medcineName,String medcinePairName);
	public int insertCompatibilityMdC(Integer id,Integer pairId,Integer type,String medcineName);
	public int insertCompatibilityMdL(Integer id,Integer pairId,Integer type,String medcineName);
	public int insertCompatibilityCdC(Integer id,Integer pairId,Integer type);
	public int insertCompatibilityCdL(Integer id,Integer pairId,Integer type);
	public int insertCompatibilityLdL(Integer id,Integer pairId,Integer type);

	
	public int saveCompatibilityMdM(Compatibility com);
	public int saveCompatibilityMdC(Compatibility com);
	public int saveCompatibilityMdL(Compatibility com);
	public int saveCompatibilityCdC(Compatibility com);
	public int saveCompatibilityCdL(Compatibility com);
	public int saveCompatibilityLdL(Compatibility com);
	
	int repeatVerification(Integer medcinePairId,Integer medcineId);
	
}
