package com.ck.springboot.mapper;

import com.ck.springboot.pojo.FrequencyDic;
import com.ck.springboot.pojo.FrequencyDicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FrequencyDicMapper {
    long countByExample(FrequencyDicExample example);

    int deleteByExample(FrequencyDicExample example);

    int insert(FrequencyDic record);

    int insertSelective(FrequencyDic record);

    List<FrequencyDic> selectByExample(FrequencyDicExample example);

    int updateByExampleSelective(@Param("record") FrequencyDic record, @Param("example") FrequencyDicExample example);

    int updateByExample(@Param("record") FrequencyDic record, @Param("example") FrequencyDicExample example);
}