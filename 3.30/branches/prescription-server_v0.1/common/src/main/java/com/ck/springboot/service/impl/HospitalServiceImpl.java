package com.ck.springboot.service.impl;

import com.ck.springboot.mapper.HospitalMapper;
import com.ck.springboot.pojo.Hospital;
import com.ck.springboot.service.HospitalService;
import com.ck.springboot.vo.PageVo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lx
 * @date 2019/3/19
 * @desc
 */
@Service
public class HospitalServiceImpl implements HospitalService {

    @Resource
    private HospitalMapper hospitalMapper;

    @Override
    public PageInfo<Hospital> getHospitalList(PageVo vo) {
        int total = hospitalMapper.total();
        List<Hospital> hospitalList = hospitalMapper.selectHospitalList(vo);
        PageInfo<Hospital> page = new PageInfo<>();
        page.setTotal(total);
        page.setList(hospitalList);
        return page;
    }
}
