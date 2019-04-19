package com.ck.springboot.service;

import com.ck.springboot.dto.DetailDto;
import com.ck.springboot.dto.HospitalDataDto;
import com.ck.springboot.vo.PageVo;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @author lx
 * @date 2019/3/18
 * @desc
 */
public interface IndexService {
    /**
     * 查询所有医院数据
     * @return
     */
    PageInfo<HospitalDataDto> hospitalDataList(PageVo vo);

    /**
     * 查询某个医院信息
     * @param hospitalId
     * @return
     */
    HospitalDataDto hospitalDataByHospitalId(Integer hospitalId);

    /**
     * 详细数据
     * @return
     */
    DetailDto detail();

    /**
     * 各时间段处方数
     * @return
     */
    Map<String,Object> totalPerPeriod();

    /**
     * 各时间段复审未通过处方数
     * @return
     */
    Map<String,Object> notPassPerPeriod();
}
