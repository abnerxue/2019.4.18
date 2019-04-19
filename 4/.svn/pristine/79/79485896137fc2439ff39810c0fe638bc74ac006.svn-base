package com.ck.springboot.mapper;

import com.ck.springboot.pojo.DurationLimit;

import javax.websocket.server.PathParam;
import java.util.List;

public interface DurationLimitMapper {
//    int deleteByPrimaryKey(Integer id);

    int insert(DurationLimit record);

//    int insertSelective(DurationLimit record);

//    DurationLimit selectByPrimaryKey(Integer id);

//    int updateByPrimaryKeySelective(DurationLimit record);

    int updateByPrimaryKey(DurationLimit record);
    
	public List<DurationLimit> selectByMedcineId(@PathParam("medcineId") Integer medcineId);
	
	public List<DurationLimit> selectById(@PathParam("id") Integer id);//编辑时通过主键获取用药天数限用信息
	
	public int deleteDurationById(Integer id);
	
	public int updateDurationById(DurationLimit dur);
	
	public int insertDuration(DurationLimit dur);
}