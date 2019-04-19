package com.ck.springboot.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ck.springboot.mapper.IndicationLimitMapper;
import com.ck.springboot.pojo.AuditLevel;
import com.ck.springboot.pojo.Diagnosis;
import com.ck.springboot.pojo.MedcineFold;
import com.ck.springboot.pojo.TageAangeIndication;
import com.ck.springboot.pojo.TgestationalWeekIndication;
import com.ck.springboot.pojo.TgroupIndication;
import com.ck.springboot.pojo.TpathologyPhysiologyIndication;
import com.ck.springboot.service.IndicationLimitService;

@Service
public class IndicationLimitServiceImpl implements IndicationLimitService {
	@Resource
	private IndicationLimitMapper indicationLimitMapper;

	@Override
	public List<Diagnosis> getDiagnosisList(int pageNo,int pageSize,String name) {
		// TODO Auto-generated method stub
		return indicationLimitMapper.getDiagnosisList(pageNo,pageSize,name);
	}

	@Override
	public int getDiagnosisCounts(String name) {
		// TODO Auto-generated method stub
		return indicationLimitMapper.getDiagnosisCounts(name);
	}

	@Override
	public List<MedcineFold> getMedicine(String name) {
		// TODO Auto-generated method stub
		return indicationLimitMapper.getMedicine(name);
	}
	
	@Override
	public List<TageAangeIndication> getTageAangeIndicationListBydiagnosisId(int diagnosisId) {
		// TODO Auto-generated method stub
		return indicationLimitMapper.getTageAangeIndicationListBydiagnosisId(diagnosisId);
	}

	@Override
	public List<TgestationalWeekIndication> getTgestationalWeekIndicationListBydiagnosisId(int diagnosisId) {
		// TODO Auto-generated method stub
		return indicationLimitMapper.getTgestationalWeekIndicationListBydiagnosisId(diagnosisId);
	}

	@Override
	public List<TgroupIndication> getTgroupIndicationListBydiagnosisId(int diagnosisId) {
		// TODO Auto-generated method stub
		return indicationLimitMapper.getTgroupIndicationListBydiagnosisId(diagnosisId);
	}

	@Override
	public List<TpathologyPhysiologyIndication> getTpathologyPhysiologyIndicationListBydiagnosisId(int diagnosisId) {
		// TODO Auto-generated method stub
		return indicationLimitMapper.getTpathologyPhysiologyIndicationListBydiagnosisId(diagnosisId);
	}

	@Override
	public int deleteAgeIndication(int diagnosisId) {
		// TODO Auto-generated method stub
		return indicationLimitMapper.deleteAgeIndication(diagnosisId);
	}

	@Override
	public int insertAgeIndication(TageAangeIndication age) {
		// TODO Auto-generated method stub
		return indicationLimitMapper.insertAgeIndication(age);
	}

	@Override
	public int deleteTgestationalWeekIndication(int diagnosisId) {
		// TODO Auto-generated method stub
		return indicationLimitMapper.deleteTgestationalWeekIndication(diagnosisId);
	}

	@Override
	public int addTgestationalWeekIndication(TgestationalWeekIndication week) {
		// TODO Auto-generated method stub
		return indicationLimitMapper.addTgestationalWeekIndication(week);
	}

	@Override
	public int deleteTgroupIndication(int diagnosisId) {
		// TODO Auto-generated method stub
		return indicationLimitMapper.deleteTgroupIndication(diagnosisId);
	}

	@Override
	public int addTgroupIndication(TgroupIndication group) {
		// TODO Auto-generated method stub
		return indicationLimitMapper.addTgroupIndication(group);
	}

	@Override
	public int deleteTpathologyPhysiologyIndication(int diagnosisId) {
		// TODO Auto-generated method stub
		return indicationLimitMapper.deleteTpathologyPhysiologyIndication(diagnosisId);
	}

	@Override
	public int addTpathologyPhysiologyIndication(TpathologyPhysiologyIndication phy) {
		// TODO Auto-generated method stub
		return indicationLimitMapper.addTpathologyPhysiologyIndication(phy);
	}

	@Override
	public List<AuditLevel> getAuditLevelList() {
		// TODO Auto-generated method stub
		return indicationLimitMapper.getAuditLevelList();
	}
}
