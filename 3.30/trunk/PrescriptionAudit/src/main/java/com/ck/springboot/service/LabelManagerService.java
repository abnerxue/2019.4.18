package com.ck.springboot.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ck.springboot.pojo.Label;

public interface LabelManagerService {
	public int addLabel(String name);
	
	public int getLabelCountsByName(String name);
	
	public int updateLabelName(int id,String name);
	
	public String getNameById(Integer id);
	
	public List<Label> getLabelList(String name);
	
	public int deleteLabelById(Integer id);
	
	public List<Label> getMedicineListByLabelId(Integer id,Integer pageNo,Integer pageSize);
	
	public int getMedicineCountsByLabelId(Integer labelId);
	
	public int deleteLabelAndMedicine(int lid);
	
	public int addLabelAndMedicine(int medcineId,int labelId);
}
