package com.ck.PAEngine.biz.impl;

import com.ck.PAEngine.Thread.Thread00001;
import com.ck.PAEngine.biz.PrescriptionBiz;
import com.ck.springboot.dto.PrescriptionDTO;
import com.ck.springboot.pojo.AuditLevel;
import com.ck.springboot.pojo.AuditRules;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author lx
 * @date 2019/3/29
 * @desc
 */
@Service
public class PrescriptionBizImpl implements PrescriptionBiz {

    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

    @Override
    public void handleScription(PrescriptionDTO prescriptionDTO) {

        //查询所有规则
        List<AuditRules> auditRulesList = new ArrayList<>();
        //所有线程执行结果
        List<Future> futureList = new ArrayList<>();
        for(AuditRules auditRules : auditRulesList){
            //所有规则验证
            if (auditRules.getCode().equals("00001")){//处方未单独开具西药、中成药和中药
                Thread00001 thread00001 = new Thread00001();
                Future<AuditRules> result = fixedThreadPool.submit(thread00001);
                futureList.add(result);
            }
            // ...

        }
        fixedThreadPool.shutdown();
        //获取线程中的执行结果
        List<AuditRules> handleAuditRulesList = new ArrayList<>();
        for (Future future : futureList){
            try {
                Object o = future.get();
                //不为空，则规则不通过
                if (o != null){
                    handleAuditRulesList.add((AuditRules)o);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        //处方放入缓存

    }
}
