package com.ck.springboot.mapper;

import com.ck.springboot.pojo.LiverKidneyDic;
import com.ck.springboot.pojo.LiverKidneyDicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LiverKidneyDicMapper {
    long countByExample(LiverKidneyDicExample example);

    int deleteByExample(LiverKidneyDicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LiverKidneyDic record);

    int insertSelective(LiverKidneyDic record);

    List<LiverKidneyDic> selectByExample(LiverKidneyDicExample example);

    LiverKidneyDic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LiverKidneyDic record, @Param("example") LiverKidneyDicExample example);

    int updateByExample(@Param("record") LiverKidneyDic record, @Param("example") LiverKidneyDicExample example);

    int updateByPrimaryKeySelective(LiverKidneyDic record);

    int updateByPrimaryKey(LiverKidneyDic record);
}