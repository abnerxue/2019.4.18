package com.ck.PAEngine.web;

import com.ck.PAEngine.biz.PrescriptionBiz;
import com.ck.springboot.dto.PrescriptionDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lx
 * @date 2019/3/29
 * @desc
 */
@RestController
public class HandlePreScriptionController {

    @Resource
    private PrescriptionBiz prescriptionBiz;

    /**
     * 审核处方，返回值格式待定
     * @param prescriptionDTO
     */
    @PostMapping("/handleScription")
    public void handleScription(@RequestBody PrescriptionDTO prescriptionDTO){
        prescriptionBiz.handleScription(prescriptionDTO);
    }

}
