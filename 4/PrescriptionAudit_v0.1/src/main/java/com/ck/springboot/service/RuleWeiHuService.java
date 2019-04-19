package com.ck.springboot.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ck.springboot.pojo.CheckItems;
import com.ck.springboot.pojo.MedicineLimit;
import com.ck.springboot.pojo.RuleName;

public interface RuleWeiHuService {
	public List<MedicineLimit> getMedicineLimit(int medicineId,int type);
	
	public List<RuleName> getMedicineLimitGroupBy(int medicineId,int type);
	
	public int addMedicineLimit(MedicineLimit medicineLimit);
	
	public int getMedicineLimitCountsByRuleName(String ruleName);
	
	public int deleteMedicineLimitByRuleName(String ruleName);
	
	public int updateMedicineLimitByRuleName(MedicineLimit medicineLimit);
	
	public List<CheckItems> getCheckItemsList();
	
	public List<MedicineLimit> getMedicineLimitDetilByRuleName(int medicineId,int type,String ruleName);
	
	
	///////////----------------------
	public String getRuleNameByid(Integer id);
}
