package com.ck.PAEngine.thread;

import com.ck.springboot.dto.MedcineDTO;
import com.ck.springboot.dto.PrescriptionDTO;
import com.ck.springboot.pojo.AuditRules;
import com.ck.springboot.pojo.pojomore.MedcineMore;
import com.ck.springboot.service.MedcineService;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author lx
 * @date 2019/4/1
 * @desc
 */
public class Thread00004 implements Callable<AuditRules> {

    private PrescriptionDTO prescriptionDTO;

    private AuditRules auditRules;

    public Thread00004(PrescriptionDTO prescriptionDTO,AuditRules auditRules) {
        this.prescriptionDTO = prescriptionDTO;
        this.auditRules = auditRules;
    }

    @Override
    public AuditRules call() throws Exception {
        return null;
    }

}
