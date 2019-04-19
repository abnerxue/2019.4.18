package com.ck.springboot.service;

import com.ck.springboot.pojo.Label;

import java.util.List;

public interface LabelManagerService {
	public int addLabel(String name);
	
	public int getLabelCountsByName(String name);
	
	public int updateLabelName(int id, String name);

	public String getNameById(Integer id);

	public List<Label> getLabelList(String name, Integer pageNo, Integer pageSize);

	/* 查询标签数量 */
	public int getLabelCount(String name);

	public int deleteLabelById(Integer id);

	public List<Label> getMedicineListByLabelId(Integer id, Integer pageNo, Integer pageSize);

	public int getMedicineCountsByLabelId(Integer labelId);

	public int deleteLabelAndMedicine(int lid);

	public int addLabelAndMedicine(int medcineId, int labelId);
}
