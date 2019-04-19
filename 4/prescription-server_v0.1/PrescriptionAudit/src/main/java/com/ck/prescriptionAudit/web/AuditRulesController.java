package com.ck.prescriptionAudit.web;

import com.ck.springboot.pojo.AuditRules;
import com.ck.springboot.pojo.RespBean;
import com.ck.springboot.service.AuditRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuditRulesController {
	
@Autowired
AuditRulesService auditRulesService;
	

   @RequestMapping(value = "/selectallaudit", method = RequestMethod.GET)
	 public Map<String, Object> getAllAuditRules() throws Exception {
	   Map<String, Object> map = new LinkedHashMap<>();
	   List<AuditRules> auditRulePages = auditRulesService.getAllAuditRules();

	   if (auditRulePages.size()==0){
		   map.put("code",0);
		   map.put("msg","无审核项目与级别设置信息");
		   map.put("result", auditRulePages);
	   }else if (auditRulePages.size()>0){
	   map.put("code",0);
	   map.put("msg","success");
	   map.put("result", auditRulePages);
	   }else {
		   map.put("code",10000);
		   map.put("msg","获取审核项目与级别设置失败"); 
	   }
	   return map;
  }
   
   @GetMapping(value = "/hello")
	 public String findAuditNameById(int id) throws Exception {
	  
//		return auditRulesService.findAuditNameById(id);
	   return "Hello";
	}
   
   @RequestMapping(value = "/updateauditrules", method = RequestMethod.POST)
   public RespBean updateAuditRules(@RequestBody List<AuditRules> auditrules) {

       if (auditRulesService.updateAuditRules(auditrules) > 0) {
           return RespBean.ok("更新成功!");
       }else {
       	return RespBean.error("更新失败!");
       }
   }
}