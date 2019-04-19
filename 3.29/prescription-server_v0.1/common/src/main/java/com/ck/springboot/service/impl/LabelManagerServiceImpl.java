package com.ck.springboot.service.impl;

import com.ck.springboot.mapper.LabelManagerMapper;
import com.ck.springboot.pojo.Label;
import com.ck.springboot.service.LabelManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LabelManagerServiceImpl implements LabelManagerService {
	@Resource
	private LabelManagerMapper labelManagerMapper;

	@Override
	public int addLabel(String name) {
		// TODO Auto-generated method stub
		return labelManagerMapper.addLabel(name);
	}

	@Override
	public int getLabelCountsByName(String name) {
		// TODO Auto-generated method stub
		return labelManagerMapper.getLabelCountsByName(name);
	}

	@Override
	public int updateLabelName(int id, String name) {
		// TODO Auto-generated method stub
		return labelManagerMapper.updateLabelName(id, name);
	}

	@Override
	public String getNameById(Integer id) {
		// TODO Auto-generated method stub
		return labelManagerMapper.getNameById(id);
	}

	@Override
	public List<Label> getLabelList(String name) {
		// TODO Auto-generated method stub
		return labelManagerMapper.getLabelList(name);
	}

	@Override
	public int deleteLabelById(Integer id) {
		// TODO Auto-generated method stub
		return labelManagerMapper.deleteLabelById(id);
	}

	@Override
	public List<Label> getMedicineListByLabelId(Integer id,Integer pageNo,Integer pageSize) {
		// TODO Auto-generated method stub
		return labelManagerMapper.getMedicineListByLabelId(id,pageNo,pageSize);
	}

	@Override
	public int getMedicineCountsByLabelId(Integer labelId) {
		// TODO Auto-generated method stub
		return labelManagerMapper.getMedicineCountsByLabelId(labelId);
	}

	@Override
	public int deleteLabelAndMedicine(int lid) {
		// TODO Auto-generated method stub
		return labelManagerMapper.deleteLabelAndMedicine(lid);
	}

	@Override
	public int addLabelAndMedicine(int medcineId, int labelId) {
		// TODO Auto-generated method stub
		return labelManagerMapper.addLabelAndMedicine(medcineId, labelId);
	}
}
