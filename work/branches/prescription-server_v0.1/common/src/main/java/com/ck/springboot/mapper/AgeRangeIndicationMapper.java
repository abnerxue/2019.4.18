package com.ck.springboot.mapper;

import com.ck.springboot.pojo.AgeRangeIndication;
import com.ck.springboot.pojo.AgeRangeIndicationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AgeRangeIndicationMapper {
    long countByExample(AgeRangeIndicationExample example);

    int deleteByExample(AgeRangeIndicationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AgeRangeIndication record);

    int insertSelective(AgeRangeIndication record);

    List<AgeRangeIndication> selectByExample(AgeRangeIndicationExample example);

    AgeRangeIndication selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AgeRangeIndication record, @Param("example") AgeRangeIndicationExample example);

    int updateByExample(@Param("record") AgeRangeIndication record, @Param("example") AgeRangeIndicationExample example);

    int updateByPrimaryKeySelective(AgeRangeIndication record);

    int updateByPrimaryKey(AgeRangeIndication record);
}