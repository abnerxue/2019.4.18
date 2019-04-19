package com.ck.springboot.mapper;

import com.ck.springboot.pojo.Label;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LabelManagerMapper {
	//添加标签
	public int addLabel(@Param("name") String name);
	//根据姓名查询是否有重复的标签
	public int getLabelCountsByName(@Param("name") String name);
	//修改标签名称
	public int updateLabelName(@Param("id") int id, @Param("name") String name);
	//根据id获取名称
	public String getNameById(@Param("id") Integer id);
	/* 查询标签数量 */
	public int getLabelCount(@Param("name") String name);
	//获取标签列表
	public List<Label> getLabelList(@Param("name") String name,
			@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);
	//通过id删除标签
	public int deleteLabelById(@Param("id") Integer id);
	//根据标签id获取药品列表
	public List<Label> getMedicineListByLabelId(@Param("id") Integer id,
                                                @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);
	//获取分页的信息总数
	public int getMedicineCountsByLabelId(@Param("labelId") Integer labelId);

	//删除某标签下的所有药品
	public int deleteLabelAndMedicine(@Param("lid") int lid);
	//添加（修改）标签下的药品
	public int addLabelAndMedicine(@Param("medcineId") int medcineId, @Param("labelId") int labelId);
	
}
