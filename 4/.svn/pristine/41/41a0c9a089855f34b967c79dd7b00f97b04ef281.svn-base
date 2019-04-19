package com.ck.springboot.service;

import java.util.List;

import com.ck.springboot.pojo.AuditLevel;
import com.ck.springboot.pojo.AuditRulesFold;
import com.ck.springboot.pojo.AuditState;
import com.ck.springboot.pojo.Category;
import com.ck.springboot.pojo.Classify1;
import com.ck.springboot.pojo.Classify2;
import com.ck.springboot.pojo.CompatibilitySelect;
import com.ck.springboot.pojo.CrowdRestriction;
import com.ck.springboot.pojo.HosDept;
import com.ck.springboot.pojo.HosStaff;
import com.ck.springboot.pojo.OperatorDictionary;
import com.ck.springboot.pojo.Reaction;
import com.ck.springboot.pojo.Type;
import com.ck.springboot.pojo.UnitDictionary;
import com.ck.springboot.pojo.UnitName;

public interface DictionariesService {
	public List<AuditLevel> selectAllAuditLevel();//
	
	public List<AuditState> selectAllAuditState();//
	
	public List<UnitName> selectAllHospital();//医院
	
	public List<Classify1> selectAllClassify1(Integer type);//大分类
	
	public List<Classify2> selectAllClassify2(Integer type,Integer classify1);//小分类
	
	public List<Type> selectAllType();//处方类型
	
	public List<HosDept> selectAllHosDept();//医院科室
	
	public List<HosStaff> selectAllHosStaff();//医院医师和药师
	
	public List<AuditRulesFold> selectAllAuditRulesFold();//问题代码
	
	public List<Type> selectAllType2();//精麻毒药品
	
	public List<CrowdRestriction> selectAllCrowd();//人群
	
	public List<OperatorDictionary> selectAllOperator();//运算符
	
	public List<UnitDictionary> selectAllUnit();//药品单位
	
	public List<Category> selectCategory();//药品分类
	
	public List<Reaction> selectReaction(Integer id);//配伍管理作用机制与结果智能筛选
	
	
}
