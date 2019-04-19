package com.ck.springboot.mapper;

import com.ck.springboot.pojo.MentalStupefacientToxicDic;
import com.ck.springboot.pojo.MentalStupefacientToxicDicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MentalStupefacientToxicDicMapper {
    long countByExample(MentalStupefacientToxicDicExample example);

    int deleteByExample(MentalStupefacientToxicDicExample example);

    int insert(MentalStupefacientToxicDic record);

    int insertSelective(MentalStupefacientToxicDic record);

    List<MentalStupefacientToxicDic> selectByExample(MentalStupefacientToxicDicExample example);

    int updateByExampleSelective(@Param("record") MentalStupefacientToxicDic record, @Param("example") MentalStupefacientToxicDicExample example);

    int updateByExample(@Param("record") MentalStupefacientToxicDic record, @Param("example") MentalStupefacientToxicDicExample example);
}