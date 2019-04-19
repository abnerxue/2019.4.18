package com.ck.springboot.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ck.springboot.pojo.CheckItems;
import com.ck.springboot.pojo.GsonZyyCheckLimit;
import com.ck.springboot.pojo.LiverKidneyDic;
import com.ck.springboot.pojo.ZyyCheckLimt;

public interface CheckValuesSetService {
	public List<GsonZyyCheckLimit> getCheckLimitListByType(int type,int type2);
	
	public List<GsonZyyCheckLimit> getCheckLimitListByliverKidneyDicId(int liverKidneyDicId);
	public int addCheckLimit(ZyyCheckLimt check);
	
	public int getliverKidneyCount(int liverKidneyDicId);
	
	public int getliverKidneyDicIdById(int id);
	
	public int deletecheckLimitByliverKidneyDicId(int liverKidneyDicId);
	
	public List<CheckItems> getCheckItemsList();
	
	public List<LiverKidneyDic> getLiverKidneyDicList(int num1,int num2);
}
