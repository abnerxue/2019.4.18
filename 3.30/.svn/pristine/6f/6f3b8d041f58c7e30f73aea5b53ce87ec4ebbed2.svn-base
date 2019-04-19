package com.ck.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ck.springboot.pojo.AuditLevel;
import com.ck.springboot.pojo.Diagnosis;
import com.ck.springboot.pojo.MedcineFold;
import com.ck.springboot.pojo.TageAangeIndication;
import com.ck.springboot.pojo.TgestationalWeekIndication;
import com.ck.springboot.pojo.TgroupIndication;
import com.ck.springboot.pojo.TpathologyPhysiologyIndication;

public interface IndicationLimitMapper {
	//获取诊断列表
	public List<Diagnosis> getDiagnosisList(@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,
			@Param("name")String name);
	
	//获取所有诊断数量
	public int getDiagnosisCounts(@Param("name")String name);
	
	//搜药药物
	public List<MedcineFold> getMedicine(@Param("name")String name);
	
	//通过诊断id获取年龄适应症
	public List<TageAangeIndication> getTageAangeIndicationListBydiagnosisId(@Param("diagnosisId")int diagnosisId);
	//通过诊断id获取孕周适应症
	public List<TgestationalWeekIndication> getTgestationalWeekIndicationListBydiagnosisId(@Param("diagnosisId")int diagnosisId);
	//通过诊断id获取人群适应症
	public List<TgroupIndication> getTgroupIndicationListBydiagnosisId(@Param("diagnosisId")int diagnosisId);
	//通过诊断id获取病生理适应症
	public List<TpathologyPhysiologyIndication> getTpathologyPhysiologyIndicationListBydiagnosisId(@Param("diagnosisId")int diagnosisId);

	//通过诊断删除年龄适应症
	public int deleteAgeIndication(@Param("diagnosisId")int diagnosisId);
	//添加年龄适应症
	public int insertAgeIndication(TageAangeIndication age);
	
	//通过诊断删除孕周适应症
	public int deleteTgestationalWeekIndication(@Param("diagnosisId")int diagnosisId);
	//添加孕周适应症
	public int addTgestationalWeekIndication(TgestationalWeekIndication week);
	
	//通过诊断id删除人群适应症
	public int deleteTgroupIndication(@Param("diagnosisId")int diagnosisId);
	//添加人群适应症
	public int addTgroupIndication(TgroupIndication group);
	
	//通过诊断id删除病生理适应症
	public int deleteTpathologyPhysiologyIndication(@Param("diagnosisId")int diagnosisId);
	//添加病生理适应症
	public int addTpathologyPhysiologyIndication(TpathologyPhysiologyIndication phy);
	
	//获取审核状态列表
	public List<AuditLevel> getAuditLevelList();
	
	
	
	
	
}
