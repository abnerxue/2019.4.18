package com.ck.springboot.mapper;

import com.ck.springboot.pojo.DoseDic;
import com.ck.springboot.pojo.DoseDicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DoseDicMapper {
    long countByExample(DoseDicExample example);

    int deleteByExample(DoseDicExample example);

    int insert(DoseDic record);

    int insertSelective(DoseDic record);

    List<DoseDic> selectByExample(DoseDicExample example);

    int updateByExampleSelective(@Param("record") DoseDic record, @Param("example") DoseDicExample example);

    int updateByExample(@Param("record") DoseDic record, @Param("example") DoseDicExample example);
}