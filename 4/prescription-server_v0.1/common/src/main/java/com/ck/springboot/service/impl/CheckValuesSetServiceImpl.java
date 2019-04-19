package com.ck.springboot.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ck.springboot.mapper.CheckValuesSetMapper;
import com.ck.springboot.pojo.CheckItems;
import com.ck.springboot.pojo.GsonZyyCheckLimit;
import com.ck.springboot.pojo.LiverKidneyDic;
import com.ck.springboot.pojo.ZyyCheckLimt;
import com.ck.springboot.service.CheckValuesSetService;
@Service
public class CheckValuesSetServiceImpl implements CheckValuesSetService {
	@Resource
	private CheckValuesSetMapper checkValuesSetMapper;

	@Override
	public List<GsonZyyCheckLimit> getCheckLimitListByType(int type,int type2) {
		// TODO Auto-generated method stub
		return checkValuesSetMapper.getCheckLimitListByType(type,type2);
	}

	@Override
	public int addCheckLimit(ZyyCheckLimt check) {
		// TODO Auto-generated method stub
		return checkValuesSetMapper.addCheckLimit(check);
	}

	@Override
	public int getliverKidneyCount(int liverKidneyDicId) {
		// TODO Auto-generated method stub
		return checkValuesSetMapper.getliverKidneyCount(liverKidneyDicId);
	}

	@Override
	public int getliverKidneyDicIdById(int id) {
		// TODO Auto-generated method stub
		return checkValuesSetMapper.getliverKidneyDicIdById(id);
	}

	@Override
	public int deletecheckLimitByliverKidneyDicId(int liverKidneyDicId) {
		// TODO Auto-generated method stub
		return checkValuesSetMapper.deletecheckLimitByliverKidneyDicId(liverKidneyDicId);
	}

	@Override
	public List<CheckItems> getCheckItemsList() {
		// TODO Auto-generated method stub
		return checkValuesSetMapper.getCheckItemsList();
	}

	@Override
	public List<LiverKidneyDic> getLiverKidneyDicList(int num1,int num2) {
		// TODO Auto-generated method stub
		return checkValuesSetMapper.getLiverKidneyDicList(num1,num2);
	}

	@Override
	public List<GsonZyyCheckLimit> getCheckLimitListByliverKidneyDicId(int liverKidneyDicId) {
		// TODO Auto-generated method stub
		return checkValuesSetMapper.getCheckLimitListByliverKidneyDicId(liverKidneyDicId);
	}
}
