package com.ck.PAEngine.thread;

import com.ck.springboot.dto.PrescriptionDTO;
import com.ck.springboot.pojo.AuditRules;
import com.ck.springboot.utils.DateHelper;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @author lx
 * @date 2019/3/30
 * @desc
 */
public class Thread00003 implements Callable<AuditRules> {

    private PrescriptionDTO prescriptionDTO;

    private AuditRules auditRules;

    public Thread00003(PrescriptionDTO prescriptionDTO, AuditRules auditRules) {
        this.prescriptionDTO = prescriptionDTO;
        this.auditRules = auditRules;
    }

    @Override
    public AuditRules call() throws Exception {
        //新生儿出生未满一月的要写明天数
        Date birthday = DateHelper.parseDate(prescriptionDTO.getPatientInfo().getBirthday());
        Calendar day30 = Calendar.getInstance();
        day30.add(Calendar.DAY_OF_YEAR,-30);
        Date beforeMonth = day30.getTime();
        if (beforeMonth.before(birthday) && prescriptionDTO.getPatientInfo().getBirthDays() == null){
            return auditRules;
        }
        //未满2年，标示月数
        Calendar year2 = Calendar.getInstance();
        year2.add(Calendar.YEAR,-2);
        Date beforeYear = year2.getTime();
        if (beforeYear.before(birthday) && prescriptionDTO.getPatientInfo().getBirthMonths() == null){
            return auditRules;
        }
        return null;
    }
}
