package com.ck.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ck.springboot.pojo.CompatibilitySelect;
import com.ck.springboot.pojo.Medcine;
import com.ck.springboot.pojo.MedcineOnly;

@Mapper
public interface MedcineMapper {
	public List<MedcineOnly> getAllMedcine(
			@Param("pageNo") Integer pageNo,
    		@Param("pageSize") Integer pageSize,
			@Param("standardSource") Integer standardSource,
    		@Param("type") Integer type, 
    		@Param("category") Integer category, 
    		@Param("subcategory") Integer subcategory,
    		@Param("name") String name
    	);
	public List<Medcine> getAllMedcineHos(
			@Param("pageNo") Integer pageNo,
    		@Param("pageSize") Integer pageSize,
			@Param("standardSource") Integer standardSource,
    		@Param("type") Integer type, 
    		@Param("category") Integer category, 
    		@Param("subcategory") Integer subcategory,
    		@Param("name") String name
    	);
	
	public List<Medcine> getAllMedcineFold(@Param("name") String name);
	
	public String getMedcineNameById(@Param("id") Integer id);//通过药品ID获取药品通用名
	
	public int getMedcineFilterCount(
			@Param("standardSource") Integer standardSource,
    		@Param("type") Integer type, 
    		@Param("category") Integer category, 
    		@Param("subcategory") Integer subcategory,
    		@Param("name") String name);
	
	public int getMedcineHosFilterCount(
			@Param("standardSource") Integer standardSource,
    		@Param("type") Integer type, 
    		@Param("category") Integer category, 
    		@Param("subcategory") Integer subcategory,
    		@Param("name") String name);
	
	public int saveMedcineHos(@Param("med") List<Medcine> med);//更新机构药品
	
	public int saveMedcine(@Param("med") List<Medcine> med);//更新平台药品
	
	public List<Medcine> getMedcineById(@Param("name") String name,@Param("specification") String specification);//选取某药品作为平台规范标准
	
	public List<CompatibilitySelect> selectMedcine();//选择药品添加配伍禁忌
	
	public List<CompatibilitySelect> selectCategory();//选择分类添加配伍禁忌
	
	public List<CompatibilitySelect> selectLabel();//选择标签添加配伍禁忌
}
