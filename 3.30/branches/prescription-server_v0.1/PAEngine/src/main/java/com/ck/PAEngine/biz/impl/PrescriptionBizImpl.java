package com.ck.PAEngine.biz.impl;

import com.ck.PAEngine.thread.Thread00001;
import com.ck.PAEngine.thread.Thread000013;
import com.ck.PAEngine.thread.Thread00002;
import com.ck.PAEngine.thread.Thread00003;
import com.ck.PAEngine.biz.PrescriptionBiz;
import com.ck.PAEngine.thread.Thread00004;
import com.ck.springboot.dto.PrescriptionDTO;
import com.ck.springboot.pojo.AuditRules;
import com.ck.springboot.service.MedcineService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource
    private MedcineService medcineService;
    

    @Override
    public void handleScription(PrescriptionDTO prescriptionDTO) {

        //查询所有规则
        List<AuditRules> auditRulesList = new ArrayList<>();
        //所有线程执行结果
        List<Future> futureList = new ArrayList<>();
        for(AuditRules auditRules : auditRulesList){
            //所有规则验证
            Future<AuditRules> result = null;
            if (auditRules.getCode().equals("00001")){//处方未单独开具西药、中成药和中药
                Thread00001 thread00001 = new Thread00001(prescriptionDTO,medcineService,auditRules);
                result = fixedThreadPool.submit(thread00001);
            }else if (auditRules.getCode().equals("00002")){//无适应症用药
                Thread00002 thread00002 = new Thread00002(prescriptionDTO,auditRules);
                result = fixedThreadPool.submit(thread00002);
            }else if (auditRules.getCode().equals("00003")){//新生儿婴幼儿出生日月龄填写不规范
                Thread00003 thread00003 = new Thread00003(prescriptionDTO,auditRules);
                result = fixedThreadPool.submit(thread00003);
            }else if (auditRules.getCode().equals("00004")){//处方有其他未填项目
                Thread00004 thread00004 = new Thread00004(prescriptionDTO,auditRules);
                result = fixedThreadPool.submit(thread00004);
            }


            else if (auditRules.getCode().equals("000013")){//重复用药、配伍禁忌和相互作用检查
                Thread000013 thread00002 = new Thread000013(prescriptionDTO, medcineService);
                result = fixedThreadPool.submit(thread00002);
            }
            // ...
            futureList.add(result);
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