package com.ck.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.ck.springboot.dto.DetailDto;
import com.ck.springboot.dto.HospitalDataDto;
import com.ck.springboot.pojo.Hospital;
import com.ck.springboot.service.HospitalService;
import com.ck.springboot.service.IndexService;
import com.ck.springboot.service.RedisService;
import com.ck.springboot.vo.PageVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lx
 * @date 2019/3/18
 * @desc
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private HospitalService hospitalService;

    @Override
    public PageInfo<HospitalDataDto> hospitalDataList(PageVo vo) {
        //分页查询医院信息
        PageInfo<Hospital> hospitalPageInfo = hospitalService.getHospitalList(vo);
        PageInfo<HospitalDataDto> hospitalDataDtoPageInfo = new PageInfo<>();
        hospitalDataDtoPageInfo.setTotal(hospitalPageInfo.getTotal());
        List<HospitalDataDto> hospitalDataDtoList = new ArrayList<>();
        hospitalDataDtoPageInfo.setList(hospitalDataDtoList);
        //遍历医院，获取每个医院的处方信息
        for(Hospital hospital : hospitalPageInfo.getList()){
            HospitalDataDto hospitalDataDto = new HospitalDataDto();
            hospitalDataDtoList.add(hospitalDataDto);
            hospitalDataDto.setHospitalId(hospital.getId());
            hospitalDataDto.setHospitalName(hospital.getName());
            //查询缓存

        }
        return hospitalDataDtoPageInfo;
    }

    @Override
    public HospitalDataDto hospitalDataByHospitalId(Integer hospitalId) {
        return null;
    }

    @Override
    public DetailDto detail() {
        return null;
    }

    @Override
    public Map<String, Object> totalPerPeriod() {
        String mapString = redisService.get("prescriptionIncrement").toString();
        JSON.parseObject(mapString,Map.class);
        return null;
    }

    @Override
    public Map<String, Object> notPassPerPeriod() {
        return null;
    }
}
