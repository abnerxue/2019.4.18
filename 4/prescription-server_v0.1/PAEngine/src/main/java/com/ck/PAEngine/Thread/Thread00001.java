package com.ck.PAEngine.thread;

import com.ck.springboot.dto.MedcineDTO;
import com.ck.springboot.dto.PrescriptionDTO;
import com.ck.springboot.pojo.AuditRules;
import com.ck.springboot.pojo.pojomore.MedcineMore;
import com.ck.springboot.service.MedcineService;
import lombok.Data;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author lx
 * @date 2019/3/29
 * @desc
 */
@Data
public class Thread00001 implements Callable<AuditRules> {

    private PrescriptionDTO prescriptionDTO;

    private MedcineService medcineService;

    private AuditRules auditRules;

    public Thread00001(PrescriptionDTO prescriptionDTO,MedcineService medcineService,AuditRules auditRules) {
        this.prescriptionDTO = prescriptionDTO;
        this.medcineService = medcineService;
        this.auditRules = auditRules;
    }

    @Override
    public AuditRules call() throws Exception {
        //处方未单独开具西药、中成药和中药
        List<MedcineDTO> medcineDTOList = prescriptionDTO.getMedcineItems();
        Integer type = null;
        for(MedcineDTO medcineDTO : medcineDTOList){
        	MedcineMore medcine = medcineService.getMedcineByName(medcineDTO.getCommonName(),prescriptionDTO.getMedicalUnit());
            if (medcine == null){
                continue;
            }
            if (type == null){
                continue;
            }
            if (type.intValue() != medcine.getType()){
                //不符合规则
                return auditRules;
            }
        }
        return null;
    }
}
