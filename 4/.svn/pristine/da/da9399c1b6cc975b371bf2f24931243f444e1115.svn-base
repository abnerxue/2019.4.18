package com.ck.springboot.mapper;

import com.ck.springboot.pojo.IntervalDic;
import com.ck.springboot.pojo.IntervalDicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IntervalDicMapper {
    long countByExample(IntervalDicExample example);

    int deleteByExample(IntervalDicExample example);

    int insert(IntervalDic record);

    int insertSelective(IntervalDic record);

    List<IntervalDic> selectByExample(IntervalDicExample example);

    int updateByExampleSelective(@Param("record") IntervalDic record, @Param("example") IntervalDicExample example);

    int updateByExample(@Param("record") IntervalDic record, @Param("example") IntervalDicExample example);
}