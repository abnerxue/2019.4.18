package com.ck.springboot.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ck.springboot.mapper.RuleWeiHuMapper;
import com.ck.springboot.pojo.CheckItems;
import com.ck.springboot.pojo.MedicineLimit;
import com.ck.springboot.pojo.RuleName;
import com.ck.springboot.service.RuleWeiHuService;

@Service
public class RuleWeiHuServiceImpl implements RuleWeiHuService {
	
	@Resource
	private RuleWeiHuMapper ruleWeiHuMapper;

	@Override
	public List<MedicineLimit> getMedicineLimit(int medicineId,int type) {
		// TODO Auto-generated method stub
		return ruleWeiHuMapper.getMedicineLimit(medicineId,type);
	}

	@Override
	public int addMedicineLimit(MedicineLimit medicineLimit) {
		// TODO Auto-generated method stub
		return ruleWeiHuMapper.addMedicineLimit(medicineLimit);
	}

	@Override
	public int getMedicineLimitCountsByRuleName(String ruleName) {
		// TODO Auto-generated method stub
		return ruleWeiHuMapper.getMedicineLimitCountsByRuleName(ruleName);
	}

	@Override
	public int deleteMedicineLimitByRuleName(String ruleName) {
		// TODO Auto-generated method stub
		return ruleWeiHuMapper.deleteMedicineLimitByRuleName(ruleName);
	}

	@Override
	public int updateMedicineLimitByRuleName(MedicineLimit medicineLimit) {
		// TODO Auto-generated method stub
		return ruleWeiHuMapper.updateMedicineLimitByRuleName(medicineLimit);
	}

	@Override
	public List<CheckItems> getCheckItemsList() {
		// TODO Auto-generated method stub
		return ruleWeiHuMapper.getCheckItemsList();
	}

	@Override
	public List<RuleName> getMedicineLimitGroupBy(int medicineId,int type) {
		// TODO Auto-generated method stub
		return ruleWeiHuMapper.getMedicineLimitGroupBy(medicineId,type);
	}

	@Override
	public List<MedicineLimit> getMedicineLimitDetilByRuleName(int medicineId, int type, String ruleName) {
		// TODO Auto-generated method stub
		return ruleWeiHuMapper.getMedicineLimitDetilByRuleName(medicineId, type, ruleName);
	}

	
	//---------------------------------------
	@Override
	public String getRuleNameByid(Integer id) {
		// TODO Auto-generated method stub
		return ruleWeiHuMapper.getRuleNameByid(id);
	}
	
}
