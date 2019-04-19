package com.ck.springboot.service;

import java.util.List;

import com.ck.springboot.pojo.CompatibilitySelect;
import com.ck.springboot.pojo.Medcine;
import com.ck.springboot.pojo.MedcineOnly;
public interface MedcineService {
	
//	public List<Medcine> getAllMedcineFilter(Integer pageNo,Integer pageSize,Integer standardSource,Integer type,Integer category,Integer subcategory,String name);
	
//	public List<MedcineHos> getAllMedcineHosFilter(Integer pageNo,Integer pageSize,Integer standardSource,Integer type,Integer category,Integer subcategory,String name);
	
	public List<MedcineOnly> getAllMedcine(Integer pageNo,Integer pageSize,Integer standardSource,Integer type,Integer category,Integer subcategory,String name);
	
	public List<Medcine> getAllMedcineHos(Integer pageNo,Integer pageSize,Integer standardSource,Integer type,Integer category,Integer subcategory,String name);

//	public int getMedcineCount();
	
	public List<Medcine> getAllMedcineFold(String name);
	
	public String getMedcineNameById(Integer id);//通过药品ID获取药品通用名
	
	public List<CompatibilitySelect> selectMedcine();//选择药品添加配伍禁忌
	
	public List<CompatibilitySelect> selectCategory();//选择分类添加配伍禁忌
	
	public List<CompatibilitySelect> selectLabel();//选择标签添加配伍禁忌
	
	public int getMedcineFilterCount(Integer standardSource, Integer type, Integer category, Integer subcategory,String name);
	
	public int getMedcineHosFilterCount(Integer standardSource, Integer type, Integer category, Integer subcategory,String name);

	public int saveMedcineHos(List<Medcine> med) throws Exception;//更新机构药品
	
	public int saveMedcine(List<Medcine> med) throws Exception;//更新平台药品
	
	public List<Medcine> getMedcineById(String name,String specification);//选取某药品作为平台规范标准
	
}
