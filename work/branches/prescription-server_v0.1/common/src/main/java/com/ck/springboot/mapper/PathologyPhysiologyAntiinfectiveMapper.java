package com.ck.springboot.mapper;

import com.ck.springboot.pojo.PathologyPhysiologyAntiinfective;
import com.ck.springboot.pojo.PathologyPhysiologyAntiinfectiveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PathologyPhysiologyAntiinfectiveMapper {
    long countByExample(PathologyPhysiologyAntiinfectiveExample example);

    int deleteByExample(PathologyPhysiologyAntiinfectiveExample example);

    int insert(PathologyPhysiologyAntiinfective record);

    int insertSelective(PathologyPhysiologyAntiinfective record);

    List<PathologyPhysiologyAntiinfective> selectByExample(PathologyPhysiologyAntiinfectiveExample example);

    int updateByExampleSelective(@Param("record") PathologyPhysiologyAntiinfective record, @Param("example") PathologyPhysiologyAntiinfectiveExample example);

    int updateByExample(@Param("record") PathologyPhysiologyAntiinfective record, @Param("example") PathologyPhysiologyAntiinfectiveExample example);
}