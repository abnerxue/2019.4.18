package com.ck.PAEngine.thread;

import com.ck.springboot.dto.PrescriptionDTO;
import com.ck.springboot.pojo.AuditRules;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.Callable;

/**
 * @author lx
 * @date 2019/3/30
 * @desc
 */
public class Thread00002 implements Callable<AuditRules> {

    private PrescriptionDTO prescriptionDTO;

    private AuditRules auditRules;

    public Thread00002(PrescriptionDTO prescriptionDTO, AuditRules auditRules) {
        this.prescriptionDTO = prescriptionDTO;
        this.auditRules = auditRules;
    }

    @Override
    public AuditRules call() throws Exception {
        if (CollectionUtils.isEmpty(prescriptionDTO.getDiagnosis())){
            return auditRules;
        }
        return null;
    }
}
