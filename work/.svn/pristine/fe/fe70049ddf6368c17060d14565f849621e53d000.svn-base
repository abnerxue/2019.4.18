package com.ck.springboot.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ck.springboot.pojo.Diagnosis;
import com.ck.springboot.pojo.Page;
import com.ck.springboot.service.DiagnosisService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DiagnosisController {
	@Autowired DiagnosisService diagnosisService;
	
	@GetMapping("/selectalldiagnosis")
	public Map<String, Object> getAlldiagnosis(String filter,
			@RequestParam(defaultValue = "1")  Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			Integer  totalCount,Integer totalPage){
	   Map<String, Object> map = new LinkedHashMap<>();
//	   List<Diagnosis> diagnosis = diagnosisService.getAllDiagnosisFilter(pageNo,pageSize,filter);
//	   totalCount = diagnosisService.getDiagnosisCount();
	   List<Diagnosis> diagnosis = null;
	   if(filter!= null && filter!="")
	   {
		   diagnosis = diagnosisService.getAllDiagnosis(filter);
		   totalCount = diagnosisService.getDiagnosisFilterCount(filter);
	   }else
	   {
		   diagnosis = diagnosisService.getAllDiagnosisFilter(pageNo,pageSize,filter);
		   totalCount = diagnosisService.getDiagnosisCount();
	   }
	   if (totalCount%pageSize == 0){
		   totalPage = totalCount/pageSize;
	   }else {
		   totalPage = totalCount/pageSize + 1;
	   }
	   
	   Page p = new Page(pageNo,pageSize,totalPage,totalCount);
	   if (diagnosis.size()==0){
		   map.put("code",0);
		   map.put("msg","无诊断信息");
		   map.put("result", diagnosis);
	   }else if(diagnosis.size()>0){
		   map.put("code",0);
		   map.put("msg","success");
		   map.put("page", p);
		   map.put("result", diagnosis);  
	   }else {
		   map.put("code",10000);
		   map.put("msg","获取诊断信息失败"); 
	   }
	   return map; 
	}
}
