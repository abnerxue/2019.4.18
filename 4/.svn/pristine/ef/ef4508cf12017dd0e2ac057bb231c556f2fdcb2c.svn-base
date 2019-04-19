package com.ck.prescriptionAudit.web;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ck.springboot.pojo.Compatibility;
import com.ck.springboot.pojo.CompatibilityCdC;
import com.ck.springboot.pojo.CompatibilityCdL;
import com.ck.springboot.pojo.CompatibilityLdL;
import com.ck.springboot.pojo.CompatibilityMdC;
import com.ck.springboot.pojo.CompatibilityMdL;
import com.ck.springboot.pojo.CompatibilityMdM;
import com.ck.springboot.pojo.RespBean;
import com.ck.springboot.service.CompatibilityService;
import com.ck.springboot.service.DictionariesService;
import com.ck.springboot.service.LabelManagerService;
import com.ck.springboot.service.MedcineService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CompatibilityController {
	@Autowired CompatibilityService compatibilityService;
	@Autowired MedcineService medcineService;
	@Autowired DictionariesService dictionariesService;
	@Autowired LabelManagerService labelManagerService;
	@GetMapping(value = "/selectcompatibilitybyid")
	public Map<String, Object> selectMedcine(Integer id,Integer sign){
		
		   Map<String, Object> map = new LinkedHashMap<>();

		   Map<String, Object> map2 = compatibilityMed(id,sign);

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
    public RespBean deleteCompatibilityById(@RequestBody(required = false) Compatibility com) {
    	if(com.getLabelId()!=null && com.getLabelPairId()!=null) {
	          if (compatibilityService.deleteCompatibilityLdL(com.getLabelId(),com.getLabelPairId()) == 1) {
	          return RespBean.ok("删除成功!");
	          }
    	}else if(com.getCategoryId()!=null && com.getLabelId()!=null) {
            if (compatibilityService.deleteCompatibilityCdL(com.getCategoryId(),com.getLabelId()) == 1) {
                return RespBean.ok("删除成功!");
                }
    	}else if(com.getMedcineId()!=null && com.getLabelId()!=null) {
            if (compatibilityService.deleteCompatibilityMdL(com.getMedcineId(),com.getLabelId()) == 1) {
                return RespBean.ok("删除成功!");
                }
    	}else if(com.getMedcineId()!=null && com.getCategoryId()!=null) {
            if (compatibilityService.deleteCompatibilityMdC(com.getMedcineId(),com.getCategoryId()) == 1) {
                return RespBean.ok("删除成功!");
                }
    	}else if(com.getCategoryId()!=null && com.getCategoryPairId()!=null) {
            if (compatibilityService.deleteCompatibilityCdC(com.getCategoryId(),com.getCategoryPairId()) == 1) {
                return RespBean.ok("删除成功!");
                }
    	}else if(com.getMedcineId()!=null && com.getMedcinePairId()!=null) {
            if (compatibilityService.deleteCompatibilityMdM(com.getMedcineId(),com.getMedcinePairId()) == 1) {
                return RespBean.ok("删除成功!");
                }
    	}
        	return RespBean.error("删除失败!");
    }
    
    @RequestMapping(value = "/insertcompatibility", method = RequestMethod.POST)
    public RespBean insertCompatibility(Integer id,Integer pairId,Integer type,Integer sign,Integer sign1) {
    	String medcineName = medcineService.getMedcineNameById(id);
    	String medcinePairName = medcineService.getMedcineNameById(pairId);
    	int result = 0;
    	if(sign == 0 && sign1 == 0) {
    		if(compatibilityService.repeatVerificationMdM(id, pairId) > 0) {
    			result = 0;
    		}else {
    		result = compatibilityService.insertCompatibilityMdM(id, pairId, type,medcineName,medcinePairName);
    		}
    	}else if(sign == 0 && sign1 == 1) {
    		if(compatibilityService.repeatVerificationMdC(id, pairId) > 0) {
    			result = 0;
    		}else {
    		result = compatibilityService.insertCompatibilityMdC(id, pairId, type,medcineName);
    		}
    	}else if(sign == 0 && sign1 == 2) {
    		if(compatibilityService.repeatVerificationMdL(id, pairId) > 0) {
    			result = 0;
    		}else {
    		result = compatibilityService.insertCompatibilityMdL(id, pairId, type,medcineName);
    		}
    	}else if(sign == 1 && sign1 == 0) {
    		if(compatibilityService.repeatVerificationCdM(id, pairId) > 0) {
    			result = 0;
    		}else {
    		result = compatibilityService.insertCompatibilityCdM(id, pairId, type,medcinePairName);
    		}
    	}else if(sign == 1 && sign1 == 1) {
    		if(compatibilityService.repeatVerificationCdC(id, pairId) > 0) {
    			result = 0;
    		}else {
    		result = compatibilityService.insertCompatibilityCdC(id, pairId, type);
    		}
    	}else if(sign == 1 && sign1 == 2) {
    		if(compatibilityService.repeatVerificationCdL(id, pairId) > 0) {
    			result = 0;
    		}else {
    		result = compatibilityService.insertCompatibilityCdL(id, pairId, type);
    		}
    	}else if(sign == 2 && sign1 == 0) {
    		if(compatibilityService.repeatVerificationLdM(id, pairId) > 0) {
    			result = 0;
    		}else {
    		result = compatibilityService.insertCompatibilityLdM(id, pairId, type,medcinePairName);
    		}
    	}else if(sign == 2 && sign1 == 1) {
    		if(compatibilityService.repeatVerificationLdC(id, pairId) > 0) {
    			result = 0;
    		}else {
    		result = compatibilityService.insertCompatibilityLdC(id, pairId, type);
    		}
    	}else if(sign == 2 && sign1 == 2) {
    		if(compatibilityService.repeatVerificationLdL(id, pairId) > 0) {
    			result = 0;
    		}else {
    		result = compatibilityService.insertCompatibilityLdL(id, pairId, type);
    		}
    	}
    	if(result == 1) {
    		return RespBean.ok("添加成功!");
    	}else if (result == 0) {
    		return RespBean.error("添加失败,该配伍组合已存在!");
    	}
    	return RespBean.error("添加失败!");
    }
    

    @RequestMapping(value = "/savecompatibility", method = RequestMethod.POST)
    public RespBean saveCompatibility(@RequestBody List<Compatibility> com) {
    	int result = 0;
    	for(int i = 0;i<com.size();i++) {
        	if(com.get(i).getLabelId()!=null && com.get(i).getLabelPairId()!=null) {
        		result = compatibilityService.saveCompatibilityLdL(com.get(i));
	      	}else if(com.get(i).getCategoryId()!=null && com.get(i).getLabelId()!=null) {
	      		result = compatibilityService.saveCompatibilityCdL(com.get(i));
	      	}else if(com.get(i).getMedcineId()!=null && com.get(i).getLabelId()!=null) {
	      		result = compatibilityService.saveCompatibilityMdL(com.get(i));
	      	}else if(com.get(i).getMedcineId()!=null && com.get(i).getCategoryId()!=null) {
	      		result = compatibilityService.saveCompatibilityMdC(com.get(i));
	      	}else if(com.get(i).getCategoryId()!=null && com.get(i).getCategoryPairId()!=null) {
	      		result = compatibilityService.saveCompatibilityCdC(com.get(i));
	      	}else if(com.get(i).getMedcineId()!=null && com.get(i).getMedcinePairId()!=null) {
	      		result = compatibilityService.saveCompatibilityMdM(com.get(i));
	      	}
    	}
    	if (result > 0) {
    		return RespBean.ok("保存成功!");
    	}
        	return RespBean.error("保存失败!");
    }
    
    public Map<String, Object> compatibilityMed(Integer id,Integer sign) {
    	 Map<String, Object> map2 = new LinkedHashMap<>();
		   
		   List<Object> comC = new ArrayList<Object>();
		   List<Object> comI = new ArrayList<Object>();
		   List<Object> comR = new ArrayList<Object>();
		   List<CompatibilityMdM> com1 = new ArrayList<CompatibilityMdM>();
		   List<CompatibilityMdC> com2= new ArrayList<CompatibilityMdC>();
		   List<CompatibilityMdL> com3= new ArrayList<CompatibilityMdL>();
		   
		   List<CompatibilityLdL> com4= new ArrayList<CompatibilityLdL>();
		   List<CompatibilityCdC> com5= new ArrayList<CompatibilityCdC>();
		   List<CompatibilityCdL> com6= new ArrayList<CompatibilityCdL>();
		   
		  if(sign == 0) {
			  com1 = compatibilityService.selectMdM(id);
			  com2 = compatibilityService.selectMdC(id);
			  com3 = compatibilityService.selectMdL(id);
			  for(int i = 0;i<com1.size();i++) {
				   if(com1.get(i).getType()==1) {
					   com1.get(i).setName(medcineService.getMedcineNameById(com1.get(i).getMedcinePairId()));
					   comC.add(com1.get(i));
				   }else if(com1.get(i).getType()==2) {
					   com1.get(i).setName(medcineService.getMedcineNameById(com1.get(i).getMedcinePairId()));
					   comI.add(com1.get(i));
				   }else if(com1.get(i).getType()==3) {
					   com1.get(i).setName(medcineService.getMedcineNameById(com1.get(i).getMedcinePairId()));
					   comR.add(com1.get(i)); 
				   }
			   }
			  
			   for(int i = 0;i<com2.size();i++) {
				   if(com2.get(i).getType()==1) {
					   com2.get(i).setName(dictionariesService.selectCategoryNameById(com2.get(i).getCategoryId()));
					   comC.add(com2.get(i));
				   }else if(com2.get(i).getType()==2) {
					   com2.get(i).setName(dictionariesService.selectCategoryNameById(com2.get(i).getCategoryId()));
					   comI.add(com2.get(i));
				   }else if(com2.get(i).getType()==3) {
					   com2.get(i).setName(dictionariesService.selectCategoryNameById(com2.get(i).getCategoryId()));
					   comR.add(com2.get(i)); 
				   }
			   }
			   
			   for(int i = 0;i<com3.size();i++) {
				   if(com3.get(i).getType()==1) {
					   com3.get(i).setName(labelManagerService.getNameById(com3.get(i).getLabelId()));
					   comC.add(com3.get(i));
				   }else if(com3.get(i).getType()==2) {
					   com3.get(i).setName(labelManagerService.getNameById(com3.get(i).getLabelId()));
					   comI.add(com3.get(i));
				   }else if(com3.get(i).getType()==3) {
					   com3.get(i).setName(labelManagerService.getNameById(com3.get(i).getLabelId()));
					   comR.add(com3.get(i)); 
				   }
			   }
		  }else if(sign == 1){
			  com2 = compatibilityService.selectCdM(id);
			  com5 = compatibilityService.selectCdC(id);
			  com6 = compatibilityService.selectCdL(id);
			  for(int i = 0;i<com2.size();i++) {
				   if(com2.get(i).getType()==1) {
					   com2.get(i).setName(medcineService.getMedcineNameById(com2.get(i).getMedcineId()));
					   comC.add(com2.get(i));
				   }else if(com2.get(i).getType()==2) {
					   com2.get(i).setName(medcineService.getMedcineNameById(com2.get(i).getMedcineId()));
					   comI.add(com2.get(i));
				   }else if(com2.get(i).getType()==3) {
					   com2.get(i).setName(medcineService.getMedcineNameById(com2.get(i).getMedcineId()));
					   comR.add(com2.get(i)); 
				   }
			   }
			  for(int i = 0;i<com5.size();i++) {
				   if(com5.get(i).getType()==1) {
					   com5.get(i).setName(dictionariesService.selectCategoryNameById(com5.get(i).getCategoryPairId()));
					   comC.add(com5.get(i));
				   }else if(com5.get(i).getType()==2) {
					   com5.get(i).setName(dictionariesService.selectCategoryNameById(com5.get(i).getCategoryPairId()));
					   comI.add(com5.get(i));
				   }else if(com5.get(i).getType()==3) {
					   com5.get(i).setName(dictionariesService.selectCategoryNameById(com5.get(i).getCategoryPairId()));
					   comR.add(com5.get(i)); 
				   }
			   }
			  for(int i = 0;i<com6.size();i++) {
				   if(com6.get(i).getType()==1) {
					   com6.get(i).setName(labelManagerService.getNameById(com6.get(i).getLabelId()));
					   comC.add(com6.get(i));
				   }else if(com6.get(i).getType()==2) {
					   com6.get(i).setName(labelManagerService.getNameById(com6.get(i).getLabelId()));
					   comI.add(com6.get(i));
				   }else if(com6.get(i).getType()==3) {
					   com6.get(i).setName(labelManagerService.getNameById(com6.get(i).getLabelId()));
					   comR.add(com6.get(i)); 
				   }
			   }
		  }else if(sign == 2){
			  com3 = compatibilityService.selectLdM(id);
			  com6 = compatibilityService.selectLdC(id);
			  com4 = compatibilityService.selectLdL(id);
			  for(int i = 0;i<com3.size();i++) {
				   if(com3.get(i).getType()==1) {
					   com3.get(i).setName(medcineService.getMedcineNameById(com3.get(i).getMedcineId()));
					   comC.add(com3.get(i));
				   }else if(com3.get(i).getType()==2) {
					   com3.get(i).setName(medcineService.getMedcineNameById(com3.get(i).getMedcineId()));
					   comI.add(com3.get(i));
				   }else if(com3.get(i).getType()==3) {
					   com3.get(i).setName(medcineService.getMedcineNameById(com3.get(i).getMedcineId()));
					   comR.add(com3.get(i)); 
				   }
			   }
			  for(int i = 0;i<com6.size();i++) {
				   if(com6.get(i).getType()==1) {
					   com6.get(i).setName(dictionariesService.selectCategoryNameById(com6.get(i).getCategoryId()));
					   comC.add(com6.get(i));
				   }else if(com6.get(i).getType()==2) {
					   com6.get(i).setName(dictionariesService.selectCategoryNameById(com6.get(i).getCategoryId()));
					   comI.add(com6.get(i));
				   }else if(com6.get(i).getType()==3) {
					   com6.get(i).setName(dictionariesService.selectCategoryNameById(com6.get(i).getCategoryId()));
					   comR.add(com6.get(i)); 
				   }
			   }
			  for(int i = 0;i<com4.size();i++) {
				   if(com4.get(i).getType()==1) {
					   com4.get(i).setName(labelManagerService.getNameById(com4.get(i).getLabelPairId()));
					   comC.add(com4.get(i));
				   }else if(com4.get(i).getType()==2) {
					   com4.get(i).setName(labelManagerService.getNameById(com4.get(i).getLabelPairId()));
					   comI.add(com4.get(i));
				   }else if(com4.get(i).getType()==3) {
					   com4.get(i).setName(labelManagerService.getNameById(com4.get(i).getLabelPairId()));
					   comR.add(com4.get(i)); 
				   }
			   }
		  }
		   map2.put("compatibility", comC);
		   map2.put("interaction", comI);
		   map2.put("repetition", comR);
		   
		   return map2;

    }
}

