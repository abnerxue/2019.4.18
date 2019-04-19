package com.ck.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.springboot.mapper.DictionariesMapper;
import com.ck.springboot.pojo.AuditLevel;
import com.ck.springboot.pojo.AuditRulesFold;
import com.ck.springboot.pojo.AuditState;
import com.ck.springboot.pojo.Category;
import com.ck.springboot.pojo.Classify1;
import com.ck.springboot.pojo.Classify2;
import com.ck.springboot.pojo.CrowdRestriction;
import com.ck.springboot.pojo.HosDept;
import com.ck.springboot.pojo.HosStaff;
import com.ck.springboot.pojo.OperatorDictionary;
import com.ck.springboot.pojo.Reaction;
import com.ck.springboot.pojo.Type;
import com.ck.springboot.pojo.UnitDictionary;
import com.ck.springboot.pojo.UnitName;
import com.ck.springboot.service.DictionariesService;
@Service
public class DictionariesServicelmpl implements DictionariesService{
	@Autowired DictionariesMapper dictionariesMapper;
	
	@Override
	public List<AuditLevel> selectAllAuditLevel() {
		// TODO Auto-generated method stub
		return dictionariesMapper.selectAllAuditLevel();
	}

	@Override
	public List<AuditState> selectAllAuditState() {
		// TODO Auto-generated method stub
		return dictionariesMapper.selectAllAuditState();
	}

	@Override
	public List<UnitName> selectAllHospital() {
		// TODO Auto-generated method stub
		return dictionariesMapper.selectAllHospital();
	}

	@Override
	public List<Classify1> selectAllClassify1(Integer type) {
		// TODO Auto-generated method stub
		return dictionariesMapper.selectAllClassify1(type);
	}

	@Override
	public List<Classify2> selectAllClassify2(Integer type,Integer classify1) {
		// TODO Auto-generated method stub
		return dictionariesMapper.selectAllClassify2(type,classify1);
	}

	@Override
	public List<Type> selectAllType() {
		// TODO Auto-generated method stub
		return dictionariesMapper.selectAllType();
	}

	@Override
	public List<HosDept> selectAllHosDept() {
		// TODO Auto-generated method stub
		return dictionariesMapper.selectAllHosDept();
	}

	@Override
	public List<HosStaff> selectAllHosStaff() {
		// TODO Auto-generated method stub
		return dictionariesMapper.selectAllHosStaff();
	}

	@Override
	public List<AuditRulesFold> selectAllAuditRulesFold() {
		// TODO Auto-generated method stub
		return dictionariesMapper.selectAllAuditRulesFold();
	}

	@Override
	public List<Type> selectAllType2() {
		// TODO Auto-generated method stub
		return dictionariesMapper.selectAllType2();
	}

	@Override
	public List<CrowdRestriction> selectAllCrowd() {
		// TODO Auto-generated method stub
		return dictionariesMapper.selectAllCrowd();
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.DictionariesService#selectAllOperator()
	 */
	@Override
	public List<OperatorDictionary> selectAllOperator() {
		// TODO Auto-generated method stub
		return dictionariesMapper.selectAllOperator();
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.DictionariesService#selectAllUnit()
	 */
	@Override
	public List<UnitDictionary> selectAllUnit() {
		// TODO Auto-generated method stub
		return dictionariesMapper.selectAllUnit();
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.DictionariesService#selectCategory()
	 */
	@Override
	public List<Category> selectCategory() {
		// TODO Auto-generated method stub
		return dictionariesMapper.selectCategory();
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.DictionariesService#selectReaction(java.lang.Integer)
	 */
	@Override
	public List<Reaction> selectReaction(Integer id) {
		// TODO Auto-generated method stub
		return dictionariesMapper.selectReaction(id);
	}

}
