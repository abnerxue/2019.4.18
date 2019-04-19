package com.ck.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.ck.springboot.pojo.Config;

public interface ConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Integer id);
    
    List<Config> selectAllConfig();
	List<Config> selectAllRangeConfig();

    int updateByPrimaryKeySelective(Config record);
    
    int updateConfig(@Param("config") List<Config> configs);//批量更新
    
    int updateRangeConfig(@Param("config") List<Config> config);//批量更新---范围 设置
}