package com.ck.springboot.mapper;

import com.ck.springboot.pojo.CheckLimit;
import com.ck.springboot.pojo.CheckLimitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CheckLimitMapper {
    long countByExample(CheckLimitExample example);

    int deleteByExample(CheckLimitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CheckLimit record);

    int insertSelective(CheckLimit record);

    List<CheckLimit> selectByExample(CheckLimitExample example);

    CheckLimit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CheckLimit record, @Param("example") CheckLimitExample example);

    int updateByExample(@Param("record") CheckLimit record, @Param("example") CheckLimitExample example);

    int updateByPrimaryKeySelective(CheckLimit record);

    int updateByPrimaryKey(CheckLimit record);
}