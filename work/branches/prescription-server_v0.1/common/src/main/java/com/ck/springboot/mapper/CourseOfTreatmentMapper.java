package com.ck.springboot.mapper;

import com.ck.springboot.pojo.CourseOfTreatment;
import com.ck.springboot.pojo.CourseOfTreatmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseOfTreatmentMapper {
    long countByExample(CourseOfTreatmentExample example);

    int deleteByExample(CourseOfTreatmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CourseOfTreatment record);

    int insertSelective(CourseOfTreatment record);

    List<CourseOfTreatment> selectByExample(CourseOfTreatmentExample example);

    CourseOfTreatment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CourseOfTreatment record, @Param("example") CourseOfTreatmentExample example);

    int updateByExample(@Param("record") CourseOfTreatment record, @Param("example") CourseOfTreatmentExample example);

    int updateByPrimaryKeySelective(CourseOfTreatment record);

    int updateByPrimaryKey(CourseOfTreatment record);
}