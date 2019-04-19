package com.ck.springboot.mapper;

import com.ck.springboot.pojo.Hospital;
import com.ck.springboot.vo.PageVo;

import java.util.List;

public interface HospitalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hospital record);

    int insertSelective(Hospital record);

    Hospital selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hospital record);

    int updateByPrimaryKey(Hospital record);

    int total();

    List<Hospital> selectHospitalList(PageVo vo);
}