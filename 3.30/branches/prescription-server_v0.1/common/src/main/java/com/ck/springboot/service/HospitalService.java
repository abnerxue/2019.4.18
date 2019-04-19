package com.ck.springboot.service;


import com.ck.springboot.pojo.Hospital;
import com.ck.springboot.vo.PageVo;
import com.github.pagehelper.PageInfo;

/**
 * @author lx
 * @date 2019/3/19
 * @desc
 */
public interface HospitalService {


    PageInfo<Hospital> getHospitalList(PageVo vo);

}
