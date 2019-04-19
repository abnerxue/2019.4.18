package com.ck.springboot.service;

import java.util.List;

import com.ck.springboot.pojo.AuditLevel;
import com.ck.springboot.pojo.Diagnosis;
import com.ck.springboot.pojo.MedcineFold;
import com.ck.springboot.pojo.TageAangeIndication;
import com.ck.springboot.pojo.TgestationalWeekIndication;
import com.ck.springboot.pojo.TgroupIndication;
import com.ck.springboot.pojo.TpathologyPhysiologyIndication;

public interface IndicationLimitService {

	public List<Diagnosis> getDiagnosisList(int pageNo,int pageSize,String name);
	
	public int getDiagnosisCounts(String name);
	
	public List<MedcineFold> getMedicine(String name);
	
	public List<TageAangeIndication> getTageAangeIndicationListBydiagnosisId(int diagnosisId);
	public List<TgestationalWeekIndication> getTgestationalWeekIndicationListBydiagnosisId(int diagnosisId);
	public List<TgroupIndication> getTgroupIndicationListBydiagnosisId(int diagnosisId);
	public List<TpathologyPhysiologyIndication> getTpathologyPhysiologyIndicationListBydiagnosisId(int diagnosisId);

	public int deleteAgeIndication(int diagnosisId);
	public int insertAgeIndication(TageAangeIndication age);
	
	public int deleteTgestationalWeekIndication(int diagnosisId);
	public int addTgestationalWeekIndication(TgestationalWeekIndication week);
	
	public int deleteTgroupIndication(int diagnosisId);
	public int addTgroupIndication(TgroupIndication group);
	
	public int deleteTpathologyPhysiologyIndication(int diagnosisId);
	public int addTpathologyPhysiologyIndication(TpathologyPhysiologyIndication phy);
	
	public List<AuditLevel> getAuditLevelList();
	
}
