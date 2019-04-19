package com.ck.prescriptionAudit.web;

import com.ck.springboot.pojo.*;
import com.ck.springboot.service.CompatibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CompatibilityController {
	@Autowired
    CompatibilityService compatibilityService;
	
	@GetMapping(value = "/selectcompatibilitybyid")
	public Map<String, Object> selectMedcine(Integer Id,Integer sign){
		
		   Map<String, Object> map = new LinkedHashMap<>();

		   Map<String, Object> map2 = compatibilityMed(Id,sign);

		   if (map2.size()==0){
			   map.put("code",0);
			   map.put("msg","无配伍信息");
			   map.put("result", map2);
		   }else if(map2.size()>0) {
			   map.put("code",0);
			   map.put("msg","success");
			   map.put("result", map2);
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取配伍信息失败");
		   }
		   return map;
	}
	
    @RequestMapping(value = "/deletecompatibilitybyid", method = RequestMethod.POST)
    public RespBean deleteCompatibilityById(Integer id) {
        if (compatibilityService.deleteCompatibilityById(id) == 1) {
            return RespBean.ok("删除成功!");
        }
        	return RespBean.error("删除失败!");
    }
    
    @RequestMapping(value = "/insertcompatibility", method = RequestMethod.POST)
    public RespBean insertCompatibility(Integer medcinePairId, Integer medcineId) {
    	String reactionDescription = "作用效果及其出处";
    	if (compatibilityService.repeatVerification(medcinePairId, medcineId) > 0 || medcinePairId==medcineId) {
			return RespBean.error("添加失败,该药物相互作用中已存在该药物!");
		} else {
    	Integer action =2;
        if (compatibilityService.insertCompatibility(medcinePairId,medcineId,reactionDescription,action) == 1) {
            return RespBean.ok("添加成功!");
        }{
        	return RespBean.error("添加失败!");
        }
		}
    }
    
    @RequestMapping(value = "/savecompatibility", method = RequestMethod.POST)
    public RespBean saveCompatibility(@RequestBody List<CompatibilityMdM> com) {
        if (compatibilityService.saveCompatibility(com) > 0 ) {
            return RespBean.ok("保存成功!");
        }
        	return RespBean.error("保存失败!");
    }
    
    public Map<String, Object> compatibilityMed(Integer Id,Integer sign) {
    	 Map<String, Object> map2 = new LinkedHashMap<>();
		   
		   List<Object> comC = new ArrayList<Object>();
		   List<Object> comI = new ArrayList<Object>();
		   List<Object> comR = new ArrayList<Object>();
		   List<CompatibilityMdM> com1 = new ArrayList<CompatibilityMdM>();
		   List<CompatibilityMdC> com2= new ArrayList<CompatibilityMdC>();
		   List<CompatibilityMdL> com3= new ArrayList<CompatibilityMdL>();
		   
		   List<CompatibilityMdC> com4= new ArrayList<CompatibilityMdC>();
		   List<CompatibilityCdC> com5= new ArrayList<CompatibilityCdC>();
		   List<CompatibilityCdL> com6= new ArrayList<CompatibilityCdL>();
		   
		  if(sign == 0) {
			  com1 = compatibilityService.selectMdM(Id);
			  com2 = compatibilityService.selectMdC(Id);
			  com3 = compatibilityService.selectMdL(Id);
			  for(int i = 0;i<com1.size();i++) {
				   if(com1.get(i).getType()==1) {
					   comC.add(com1.get(i));
				   }else if(com1.get(i).getType()==2) {
					   comI.add(com1.get(i));
				   }else if(com1.get(i).getType()==3) {
					   comR.add(com1.get(i)); 
				   }
			   }
			   
			   for(int i = 0;i<com2.size();i++) {
				   if(com2.get(i).getType()==1) {
					   comC.add(com2.get(i));
				   }else if(com2.get(i).getType()==2) {
					   comI.add(com2.get(i));
				   }else if(com2.get(i).getType()==3) {
					   comR.add(com2.get(i)); 
				   }
			   }
			   
			   for(int i = 0;i<com3.size();i++) {
				   if(com1.get(i).getType()==1) {
					   comC.add(com3.get(i));
				   }else if(com3.get(i).getType()==2) {
					   comI.add(com3.get(i));
				   }else if(com3.get(i).getType()==3) {
					   comR.add(com3.get(i)); 
				   }
			   }
		  }else if(sign == 1){
			  com4 = compatibilityService.selectCdM(Id);
			  com5 = compatibilityService.selectCdC(Id);
			  com6 = compatibilityService.selectCdL(Id);
			  for(int i = 0;i<com4.size();i++) {
				   if(com4.get(i).getType()==1) {
					   comC.add(com4.get(i));
				   }else if(com4.get(i).getType()==2) {
					   comI.add(com4.get(i));
				   }else if(com4.get(i).getType()==3) {
					   comR.add(com4.get(i)); 
				   }
			   }
			  for(int i = 0;i<com5.size();i++) {
				   if(com5.get(i).getType()==1) {
					   comC.add(com5.get(i));
				   }else if(com5.get(i).getType()==2) {
					   comI.add(com5.get(i));
				   }else if(com5.get(i).getType()==3) {
					   comR.add(com5.get(i)); 
				   }
			   }
			  for(int i = 0;i<com6.size();i++) {
				   if(com6.get(i).getType()==1) {
					   comC.add(com6.get(i));
				   }else if(com6.get(i).getType()==2) {
					   comI.add(com6.get(i));
				   }else if(com6.get(i).getType()==3) {
					   comR.add(com6.get(i)); 
				   }
			   }
		  }
		   
		   map2.put("compatibility", comC);
		   map2.put("interaction", comI);
		   map2.put("repetition", comR);
		   
		   return map2;

    }
}

