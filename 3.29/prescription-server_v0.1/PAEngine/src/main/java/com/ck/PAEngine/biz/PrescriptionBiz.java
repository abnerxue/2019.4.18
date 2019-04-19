package com.ck.PAEngine.biz;

import com.ck.springboot.dto.PrescriptionDTO;

/**
 * @author lx
 * @date 2019/3/29
 * @desc
 */
public interface PrescriptionBiz {
    void handleScription(PrescriptionDTO prescriptionDTO);
}
