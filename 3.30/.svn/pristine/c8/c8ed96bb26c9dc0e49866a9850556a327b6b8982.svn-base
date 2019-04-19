package com.ck.springboot.mapper;

import com.ck.springboot.pojo.CheckItems;
import com.ck.springboot.pojo.MedicineLimit;
import com.ck.springboot.pojo.RuleName;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RuleWeiHuMapper {
	//获取药物限制列表
	public List<MedicineLimit> getMedicineLimit(@Param("medicineId") int medicineId, @Param("type") int type);

	//根据规则名称分组
	public List<RuleName> getMedicineLimitGroupBy(@Param("medicineId") int medicineId, @Param("type") int type);

	//添加规则
	public int addMedicineLimit(MedicineLimit medicineLimit);

	//查询是否相同的规则名称
	public int getMedicineLimitCountsByRuleName(@Param("ruleName") String ruleName);

	//删除药物限制
	public int deleteMedicineLimitByRuleName(@Param("ruleName") String ruleName);

	//修改药物限制
	public int updateMedicineLimitByRuleName(MedicineLimit medicineLimit);

	//获取检查项目列表
	public List<CheckItems> getCheckItemsList();
	//点击添加按钮获取详情
	public List<MedicineLimit> getMedicineLimitDetilByRuleName(@Param("medicineId") int medicineId,
                                                               @Param("type") int type, @Param("ruleName") String ruleName);


	//////////////////////////////////////
	public String getRuleNameByid(@Param("id") Integer id);
	
	
}
