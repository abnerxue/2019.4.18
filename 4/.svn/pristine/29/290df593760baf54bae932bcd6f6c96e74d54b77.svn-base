package com.ck.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
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

public interface DictionariesMapper {
	
	public List<AuditLevel> selectAllAuditLevel();
	
	public List<AuditState> selectAllAuditState();
	
	public List<UnitName> selectAllHospital();
	
	public List<Classify1> selectAllClassify1(@Param("type") Integer type);
	
	public List<Classify2> selectAllClassify2(
			@Param("type") Integer type,
			@Param("classify1") Integer classify1);
	
	public List<Type> selectAllType();
	
	public List<HosDept> selectAllHosDept();//医院科室
	
	public List<HosStaff> selectAllHosStaff();//医院医师和药师
	
	public List<AuditRulesFold> selectAllAuditRulesFold();//问题代码
	
	public List<Type> selectAllType2();
	
	public List<CrowdRestriction> selectAllCrowd();//人群
	
	public List<OperatorDictionary> selectAllOperator();//运算符
	
	public List<UnitDictionary> selectAllUnit();//药品单位
	
	public List<Category> selectCategory();//药品分类
	
	public List<Reaction> selectReaction(Integer id);//配伍管理作用机制与结果智能筛选
	
	public String selectCategoryNameById(@Param("id") Integer id);//通过分类ID获取分类名
	

}
