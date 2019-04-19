package com.ck.springboot.web;

import com.ck.springboot.constant.HttpResponseConstant;
import com.ck.springboot.dto.DetailDto;
import com.ck.springboot.dto.HospitalDataDto;
import com.ck.springboot.service.IndexService;
import com.ck.springboot.utils.ResponseResult;
import com.ck.springboot.vo.PageVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * @author lx
 * @date 2019/3/18
 * @desc 首页接口
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/index")
public class IndexController {

    @Resource
    private IndexService indexService;

    /**
     *  各医院数据列表
     * @return
     */
    @GetMapping("/hospitalDataList")
    public ResponseResult hospitalDataList(@ModelAttribute PageVo vo, HttpServletResponse response){
        //查询数据
        PageInfo<HospitalDataDto> hospitalDataDtoList = indexService.hospitalDataList(vo);
        //组装返回值
        ResponseResult responseResult = new ResponseResult();
        responseResult.setResult(hospitalDataDtoList.getList());
        responseResult.setTotal((int)hospitalDataDtoList.getTotal());
        return responseResult;
    }

    /**
     * 每个医院详细数据
     * @return
     */
    @GetMapping("/hospitalData/{hospitalId}")
    public ResponseResult hospitalData(@PathVariable("hospitalId") Integer hospitalId){
        //查询数据
        HospitalDataDto hospitalDataDto = indexService.hospitalDataByHospitalId(hospitalId);
        //组装返回值
        ResponseResult responseResult = new ResponseResult();
        responseResult.setResult(hospitalDataDto);
        return responseResult;
    }

    /**
     * 所有处方统计详细数据
     * @return
     */
    @GetMapping("/detail")
    public ResponseResult detail(){
        //查询数据
        DetailDto detailDto = indexService.detail();
        //组装返回
        ResponseResult responseResult = new ResponseResult();
        responseResult.setResult(detailDto);
        return responseResult;
    }

    /**
     * 各时间段处方数
     * @return
     */
    @GetMapping("/totalPerPeriod")
    public ResponseResult totalPerPeriod(){
        //查询数据
        Map<String,Object> map = indexService.totalPerPeriod();
        //组装返回
        ResponseResult responseResult = new ResponseResult();
        responseResult.setResult(map);
        return responseResult;
    }

    /**
     * 各时间段复审未通过处方数
     * @return
     */
    @GetMapping("/notPassPerPeriod")
    public ResponseResult notPassPerPeriod(){
        //查询数据
        Map<String,Object> map = indexService.notPassPerPeriod();
        //组装返回
        ResponseResult responseResult = new ResponseResult();
        responseResult.setResult(map);
        return responseResult;
    }

    @RequestMapping(value="/noToken")
    public ResponseResult noToken() {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(HttpResponseConstant.NO_TOKEN);
        responseResult.setMsg("token失效");
        return responseResult;
    }

}
