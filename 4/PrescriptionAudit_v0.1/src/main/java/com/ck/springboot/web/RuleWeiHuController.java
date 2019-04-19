package com.ck.springboot.web;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ck.springboot.pojo.CheckItems;
import com.ck.springboot.pojo.CheckRules;

import com.ck.springboot.pojo.MedicineLimit;
import com.ck.springboot.pojo.MedicineLimit2;
import com.ck.springboot.pojo.RespBean;
import com.ck.springboot.pojo.RuleName;
import com.ck.springboot.service.RuleWeiHuService;
import com.ck.springboot.utils.CheckLimit;
import com.ck.springboot.utils.CheckRule;
import com.google.gson.Gson;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RuleWeiHuController {
	@Resource
	private RuleWeiHuService ruleWeiHuService;
	
	@GetMapping(value = "/getmedicinelimit")
	 public Map<String, Object> getMedicineLimit(int medicineId,@RequestParam(defaultValue = "1") int type){
		 
		 Map<String, Object> map = new LinkedHashMap<>();
		 List<MedicineLimit> medicinelimit = ruleWeiHuService.getMedicineLimit(medicineId,type);
		 List<RuleName> ruleNameList=ruleWeiHuService.getMedicineLimitGroupBy(medicineId,type);
		 List<MedicineLimit2> list= new ArrayList<MedicineLimit2>();
		 int ruleId=1;
		 for(RuleName rn:ruleNameList) {
			 MedicineLimit2 m2 = new MedicineLimit2();
			 for(MedicineLimit ml:medicinelimit) {
				if(ml.getRuleName().equals(rn.getRuleName())) {
					m2.setRuleId(ruleId);
					m2.setRuleName(ml.getRuleName());
					m2.setMedicineId(ml.getMedicineId());
					m2.setLevel(ml.getLevel());
					m2.setType(ml.getType());
					CheckRules check = new CheckRules();//--
					check.setId(ml.getId());
					check.setCheckItemId(ml.getCheckItemId());
					check.setLowerbound(ml.getLowerbound());
					check.setUpperbound(ml.getUpperbound());
					check.setUnitName(ml.getUnitName());
					check.setName(ml.getName());
					if(m2.getCheckRules()!=null) {
						m2.getCheckRules().add(check);
					}else {
						List<CheckRules> li=new ArrayList<CheckRules>();
						m2.setCheckRules(li);
						m2.getCheckRules().add(check);
					}
				}
			 }
			 list.add(m2);
			ruleId++;
		 }
		 if(medicinelimit.size()==0) {
			 map.put("code",0);
			 map.put("msg","无限用信息");
			 map.put("result", list);
		 }else if(medicinelimit.size()>0) {
			 map.put("code",0);
			 map.put("msg","success");
			 map.put("result", list);
		 }else {
			 map.put("code",10000);
			 map.put("msg","获取诊断信息失败");
		 }
		return map;
	}
	
	
	
	@PostMapping(value="/addmedicinelimit")
	public RespBean addmedicinelimit(@RequestBody String jsonStr) {
		CheckLimit checkLimit = new Gson().fromJson(jsonStr,CheckLimit.class);
		int ruleId = checkLimit.getRuleId();
		int medicineId = checkLimit.getMedicineId();
		String level = checkLimit.getLevel();
		String ruleName = checkLimit.getRuleName();
		int type = checkLimit.getType();
		List<CheckRule> checkRule = checkLimit.getCheckRules();
		for(int i=0;i<checkRule.size();i++) {
			for(int j=0;j<checkRule.size();j++) {
				if(i==j) {
					continue;
				}else if(checkRule.get(i).getCheckItemId()==checkRule.get(j).getCheckItemId()) {
					return RespBean.error("检查项目名相同，添加失败!");
				}
			}
		}
		
		for(CheckRule cek:checkRule) {
			if(cek.getLowerbound()==null||cek.getUpperbound()==null) {
				return RespBean.error("内容不能为空,添加失败!");
			}else if(cek.getLowerbound()>cek.getUpperbound()) {
				return RespBean.error("最小值不能大于最大值,添加失败!");
			}else if(cek.getLowerbound()<0) {
				return RespBean.error("值不能为负数,添加失败!");
			}
		}
		if(ruleWeiHuService.getMedicineLimitCountsByRuleName(ruleName)>0) {
			return RespBean.error("有重复姓名,添加失败!");
		}else if(ruleName==null||"".equals(ruleName)) {
			return RespBean.error("内容不能为空!");
		}
		for(CheckRule check:checkRule) {
			MedicineLimit medic=new MedicineLimit();
			medic.setMedicineId(medicineId);
			medic.setLevel(level);
			medic.setRuleName(ruleName);
			medic.setType(type);
			medic.setLowerbound(check.getLowerbound());//-
			medic.setUpperbound(check.getUpperbound());
		//	medic.setUnitName(check.getUnitName());
			medic.setCheckItemId(check.getCheckItemId());
			if(ruleWeiHuService.addMedicineLimit(medic)!=1) {
				return RespBean.error("添加失败!");
			}
		}
		return RespBean.ok("添加成功!");
	}
	

	
	@PostMapping(value="/deletemedicinelimitbyrulename")
	public RespBean deleteMedicineLimitById(String ruleName) {
		if(ruleWeiHuService.deleteMedicineLimitByRuleName(ruleName)>0) {
			return RespBean.ok("删除成功!");
		}else {
			return RespBean.error("删除失败!");
		}
	}

//	@PostMapping(value="/updatemedicinelimitbyrulename")
//	public RespBean updateMedicineLimitByRuleName(@RequestBody String jsonStr) {
//		CheckLimit checkLimit = new Gson().fromJson(jsonStr,CheckLimit.class);
//		int ruleId = checkLimit.getRuleId();
//		int medicineId = checkLimit.getMedicineId();
//		String level = checkLimit.getLevel();
//		String ruleName = checkLimit.getRuleName();
//		int type = checkLimit.getType();
//		List<CheckRule> checkRule = checkLimit.getCheckRules();
//		for(CheckRule check:checkRule) {
//			if(check.getId()!=null) {
//				MedicineLimit medic=new MedicineLimit();
//				medic.setMedicineId(medicineId);
//				medic.setLevel(level);
//				medic.setRuleName(ruleName);
//				medic.setType(type);
//				medic.setId(check.getId());		//-
//				medic.setLowerbound(check.getLowerbound());
//				medic.setUpperbound(check.getUpperbound());
//		//		medic.setUnitName(check.getUnitName());
//				medic.setCheckItemId(check.getCheckItemId());
//				if(ruleWeiHuService.updateMedicineLimitByRuleName(medic)!=1) {
//					return RespBean.error("更新失败!");
//				}
//			}else if(check.getId()==null) {
//				MedicineLimit medic=new MedicineLimit();
//				medic.setMedicineId(medicineId);
//				medic.setLevel(level);
//				medic.setRuleName(ruleName);
//				medic.setType(type);
//				medic.setLowerbound(check.getLowerbound());//---
//				medic.setUpperbound(check.getUpperbound());
//				medic.setCheckItemId(check.getCheckItemId());
//				if(ruleWeiHuService.addMedicineLimit(medic)!=1) {
//					return RespBean.error("修改失败!");
//				}
//			}
//		}
//		return RespBean.ok("更新成功!");
//	}
	@PostMapping(value="/updatemedicinelimitbyrulename")
	public RespBean updateMedicineLimitByRuleName(@RequestBody String jsonStr) {
		CheckLimit checkLimit = new Gson().fromJson(jsonStr,CheckLimit.class);
		int ruleId = checkLimit.getRuleId();
		int medicineId = checkLimit.getMedicineId();
		String level = checkLimit.getLevel();
		String ruleName = checkLimit.getRuleName();
		int type = checkLimit.getType();
		List<CheckRule> checkRule = checkLimit.getCheckRules();
		//查找是否有重复的检查项目
		for(int i=0;i<checkRule.size();i++) {
			for(int j=0;j<checkRule.size();j++) {
				if(i==j) {
					continue;
				}else if(checkRule.get(i).getCheckItemId()==checkRule.get(j).getCheckItemId()) {
					return RespBean.error("检查项目名相同，添加失败!");
				}
			}
		}
		//逻辑判断
		for(CheckRule cek:checkRule) {
			if(cek.getLowerbound()==null||cek.getUpperbound()==null) {
				return RespBean.error("内容不能为空,添加失败!");
			}else if(cek.getLowerbound()>cek.getUpperbound()) {
				return RespBean.error("最小值不能大于最大值,添加失败!");
			}else if(cek.getLowerbound()<0) {
				return RespBean.error("值不能为负数,添加失败!");
			}
		}
		
		int id=checkRule.get(0).getId();//拿到传过来的id
		String rName=ruleWeiHuService.getRuleNameByid(id);//通过id拿到规则名称
		if(!ruleName.equals(rName)&&ruleWeiHuService.getMedicineLimitCountsByRuleName(ruleName)>0) {
				return RespBean.error("有重复名称,修改失败!");
		}
		System.out.println(rName+"----------------------->");
		ruleWeiHuService.deleteMedicineLimitByRuleName(rName);
		
		for(CheckRule check:checkRule) {
			MedicineLimit medic=new MedicineLimit();
			medic.setMedicineId(medicineId);
			medic.setLevel(level);
			medic.setRuleName(ruleName);
			medic.setType(type);
			medic.setLowerbound(check.getLowerbound());//-
			medic.setUpperbound(check.getUpperbound());
			medic.setCheckItemId(check.getCheckItemId());
			if(ruleWeiHuService.addMedicineLimit(medic)!=1) {
				return RespBean.error("修改失败!");
			}
		}
		
		return RespBean.ok("更新成功!");
	}

	
	
	
	@GetMapping(value = "/getcheckitemslist")
	public Map<String, Object> getCheckItems(){
		 Map<String, Object> map = new LinkedHashMap<>();
		 List<CheckItems> checkItems=ruleWeiHuService.getCheckItemsList();
		 if(checkItems.size()==0) {
			 map.put("code",0);
			 map.put("msg","无检查项目信息");
			 map.put("result", checkItems); 
		 }else if(checkItems.size()>0) {
			 map.put("code",0);
			 map.put("msg","success");
			 map.put("result", checkItems);
		 }else {
			 map.put("code",10000);
			 map.put("msg","获取信息失败");
		 }
		return map;
	}
	
	@SuppressWarnings("unused")
	@GetMapping(value = "/getmedicinelimitdetilbyrulename")
	 public Map<String, Object> getMedicineLimitDetilByRuleName(int medicineId,int type,
			 String ruleName,Integer ruleId){
		Map<String, Object> map = new LinkedHashMap<>();
		List<MedicineLimit> medicinelimitDetail = ruleWeiHuService.getMedicineLimitDetilByRuleName(medicineId, type, ruleName);
		MedicineLimit2 medicinelistLimit2 = new MedicineLimit2();
		for(MedicineLimit medic:medicinelimitDetail) {
			if(ruleId!=null) {
				medicinelistLimit2.setRuleId(ruleId);
			}
			medicinelistLimit2.setRuleName(medic.getRuleName());
			medicinelistLimit2.setMedicineId(medic.getMedicineId());
			medicinelistLimit2.setLevel(medic.getLevel());
			medicinelistLimit2.setType(medic.getType());
			CheckRules check = new CheckRules();//--
			check.setId(medic.getId());
			check.setLowerbound(medic.getLowerbound());
			check.setUpperbound(medic.getUpperbound());
			check.setUnitName(medic.getUnitName());
			check.setCheckItemId(medic.getCheckItemId());
			check.setName(medic.getName());
			if(medicinelistLimit2.getCheckRules()!=null) {
				medicinelistLimit2.getCheckRules().add(check);
			}else {
				List<CheckRules> li=new ArrayList<CheckRules>();
				medicinelistLimit2.setCheckRules(li);
				medicinelistLimit2.getCheckRules().add(check);
			}
		}
		 if(medicinelistLimit2 == null) {
			 map.put("code",0);
			 map.put("msg","无限用信息");
			 map.put("result", medicinelistLimit2);
		 }else if(medicinelistLimit2 != null) {
			 map.put("code",0);
			 map.put("msg","success");
			 map.put("result", medicinelistLimit2);
		 }else {
			 map.put("code",10000);
			 map.put("msg","获取诊断信息失败");
		 }
		return map;
	}
	
	
	
	
	
	
	
	
	
	
}
