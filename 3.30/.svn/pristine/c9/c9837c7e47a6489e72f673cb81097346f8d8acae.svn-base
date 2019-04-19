package com.ck.springboot.mapper;

import com.ck.springboot.pojo.GroupIndication;
import com.ck.springboot.pojo.GroupIndicationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupIndicationMapper {
    long countByExample(GroupIndicationExample example);

    int deleteByExample(GroupIndicationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GroupIndication record);

    int insertSelective(GroupIndication record);

    List<GroupIndication> selectByExample(GroupIndicationExample example);

    GroupIndication selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GroupIndication record, @Param("example") GroupIndicationExample example);

    int updateByExample(@Param("record") GroupIndication record, @Param("example") GroupIndicationExample example);

    int updateByPrimaryKeySelective(GroupIndication record);

    int updateByPrimaryKey(GroupIndication record);
}