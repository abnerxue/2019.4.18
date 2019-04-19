package com.ck.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ck.springboot.pojo.CheckItems;
import com.ck.springboot.pojo.GsonZyyCheckLimit;
import com.ck.springboot.pojo.LiverKidneyDic;
import com.ck.springboot.pojo.ZyyCheckLimt;

public interface CheckValuesSetMapper {
	//获取列表
	public List<GsonZyyCheckLimit> getCheckLimitListByType(@Param("type")int type,@Param("type2")int type2);
	
	//通过肝肾id获取列表
	public List<GsonZyyCheckLimit> getCheckLimitListByliverKidneyDicId(@Param("liverKidneyDicId")int liverKidneyDicId);
	
	//添加规则
	public int addCheckLimit(ZyyCheckLimt check);
	
	//查询是否相同的肝肾功能项目
	public int getliverKidneyCount(@Param("liverKidneyDicId")int liverKidneyDicId);
	
	//通过id查询肝肾id
	public int getliverKidneyDicIdById(@Param("id")int id);
	
	//通过肝肾id 删除checklimit表
	public int deletecheckLimitByliverKidneyDicId(@Param("liverKidneyDicId")int liverKidneyDicId);
	
	//获取检查项目列表
	public List<CheckItems> getCheckItemsList();
	
	//获取肝肾功能列表
	public List<LiverKidneyDic> getLiverKidneyDicList(@Param("num1")int num1,@Param("num2")int num2); 
	
	
	
	
	
	
}
