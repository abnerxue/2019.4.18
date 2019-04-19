package com.ck.springboot.mapper;

import com.ck.springboot.pojo.PathologyPhysiologyIndication;
import com.ck.springboot.pojo.PathologyPhysiologyIndicationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PathologyPhysiologyIndicationMapper {
    long countByExample(PathologyPhysiologyIndicationExample example);

    int deleteByExample(PathologyPhysiologyIndicationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PathologyPhysiologyIndication record);

    int insertSelective(PathologyPhysiologyIndication record);

    List<PathologyPhysiologyIndication> selectByExample(PathologyPhysiologyIndicationExample example);

    PathologyPhysiologyIndication selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PathologyPhysiologyIndication record, @Param("example") PathologyPhysiologyIndicationExample example);

    int updateByExample(@Param("record") PathologyPhysiologyIndication record, @Param("example") PathologyPhysiologyIndicationExample example);

    int updateByPrimaryKeySelective(PathologyPhysiologyIndication record);

    int updateByPrimaryKey(PathologyPhysiologyIndication record);
}