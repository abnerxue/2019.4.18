package com.ck.springboot.web;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ck.springboot.pojo.AuditLevel;
import com.ck.springboot.pojo.AuditRulesFold;
import com.ck.springboot.pojo.AuditState;
import com.ck.springboot.pojo.Category;
import com.ck.springboot.pojo.Classify1;
import com.ck.springboot.pojo.Classify2;
import com.ck.springboot.pojo.CompatibilitySelect;
import com.ck.springboot.pojo.CrowdRestriction;
import com.ck.springboot.pojo.HosDept;
import com.ck.springboot.pojo.HosStaff;
import com.ck.springboot.pojo.OperatorDictionary;
import com.ck.springboot.pojo.Reaction;
import com.ck.springboot.service.DictionariesService;
import com.ck.springboot.service.LabelManagerService;
import com.ck.springboot.service.MedcineService;
import com.ck.springboot.pojo.Type;
import com.ck.springboot.pojo.UnitDictionary;
import com.ck.springboot.pojo.UnitName;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DictionariesController {
	@Autowired DictionariesService dictionariesService;
	@Autowired MedcineService medcineService;
	@Autowired LabelManagerService labelManagerService;
	@GetMapping("/selectallauditlevel")
	public Map<String, Object> selectAllAuditLevel(){
		
		   Map<String, Object> map = new LinkedHashMap<>();
		   List<AuditLevel> auditlevel = dictionariesService.selectAllAuditLevel();
		   
		   if (auditlevel.size()==0){
			   map.put("code",0);
			   map.put("msg","无审核水平字典");
			   map.put("result", auditlevel);
		   }else if(auditlevel.size()>0){
			   map.put("code",0);
			   map.put("msg","success");
			   map.put("result", auditlevel);  
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取审核水平字典失败"); 

		   }
		   return map; 
		}
	
	@GetMapping("/selectallauditstate")
	public Map<String, Object> selectAllAuditState(){
		   Map<String, Object> map = new LinkedHashMap<>();
		   List<AuditState> auditstate = dictionariesService.selectAllAuditState();
		   
		   if (auditstate.size()==0){
			   map.put("code",0);
			   map.put("msg","无审核状态字典");
			   map.put("result", auditstate);
		   }else if (auditstate.size()>0){
			   map.put("code",0);
			   map.put("msg","success");
			   map.put("result", auditstate);  
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取审核状态字典失败"); 
		   }
		   return map; 
		}
	@GetMapping("/selectallhospital")
	public Map<String, Object> selectAllHospital(){
		   Map<String, Object> map = new LinkedHashMap<>();
		   List<UnitName> unitname = dictionariesService.selectAllHospital();
		   
		   if (unitname.size()==0){
			   map.put("code",0);
			   map.put("msg","无医院字典");
			   map.put("result", unitname);
		   }else if(unitname.size()>0){
			   map.put("code",0);
			   map.put("msg","success");
			   map.put("result", unitname);  
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取医院失败"); 
		   }
		   return map; 
		}
	@GetMapping("/selectallclassify1")
	public Map<String, Object> selectallclassify1(Integer type){
		   Map<String, Object> map = new LinkedHashMap<>();
		   List<Classify1> classify1 = dictionariesService.selectAllClassify1(type);
		   
		   if (classify1.size()==0){
			   map.put("code",0);
			   map.put("msg","无大分类字典");
			   map.put("result", classify1);
		   }else if(classify1.size()>0) {
			   map.put("code",0);
			   map.put("msg","success");
			   map.put("result", classify1);  
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取大分类失败"); 
		   }
		   return map; 
		}
	@GetMapping("/selectallclassify2")
	public Map<String, Object> selectAllClassify2(Integer type,Integer classify1){
		   Map<String, Object> map = new LinkedHashMap<>();
		   List<Classify2> classify2 = dictionariesService.selectAllClassify2(type,classify1);
		   
		   if (classify2.size()==0){
			   map.put("code",0);
			   map.put("msg","无小分类字典");
			   map.put("result", classify2);
		   }else if(classify2.size()>0) {
			   map.put("code",0);
			   map.put("msg","success");
			   map.put("result", classify2);  
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取小分类失败"); 
		   }
		   return map; 
		}
	
	@GetMapping("/selectalltype")
	public Map<String, Object> selectAllType(){
		
		   Map<String, Object> map = new LinkedHashMap<>();
		   List<Type> type = dictionariesService.selectAllType();
		   
		   if (type.size()==0){
			   map.put("code",0);
			   map.put("msg","无处方类型字典");
			   map.put("result", type);
		   }else if(type.size()>0){
			   map.put("code",0);
			   map.put("msg","success");
			   map.put("result", type);  
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取处方类型失败"); 
		   }
		   return map; 
		}
	@GetMapping("/selectallauditrulesfold")
	public Map<String, Object> selectAllAuditRulesFold(){
		
		   Map<String, Object> map = new LinkedHashMap<>();
		   List<AuditRulesFold> auditrulesfold = dictionariesService.selectAllAuditRulesFold();
		   
		   if (auditrulesfold.size()==0){
			   map.put("code",0);
			   map.put("msg","无问题代码字典");
			   map.put("result", auditrulesfold);
		   }else if(auditrulesfold.size()>0) {
			   map.put("code",0);
			   map.put("msg","success");
			   map.put("result", auditrulesfold);  
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取问题代码失败"); 
		   }
		   return map; 
		}
	@GetMapping("/selectallhosdept")
	public Map<String, Object> selectAllHosDept(){
		
		   Map<String, Object> map = new LinkedHashMap<>();
		   List<HosDept> hosdept = dictionariesService.selectAllHosDept();
		   
		   if (hosdept.size()==0){
			   map.put("code",0);
			   map.put("msg","无医院科室"); 
			   map.put("result", hosdept);
		   }else if(hosdept.size()>0){
			   map.put("code",0);
			   map.put("msg","success");
			   map.put("result", hosdept);  
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取医院科室失败"); 
		   }
		   return map; 
		}
	@GetMapping("/selectallhosstaff")
	public Map<String, Object> selectAllHosStaff(){
		
		   Map<String, Object> map = new LinkedHashMap<>();
		   List<HosStaff> hosstaff = dictionariesService.selectAllHosStaff();
		   
		   if (hosstaff.size()==0){
			   map.put("code",0);
			   map.put("msg","无医院医师和药师");
			   map.put("result", hosstaff);
		   }else if(hosstaff.size()>=0) {
			   map.put("code",0);
			   map.put("msg","success");
			   map.put("result", hosstaff);  
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取医院医师和药师失败"); 
		   }
		   return map; 
		}
	
	@GetMapping("/selectalltype2")
	public Map<String, Object> selectAllType2(){
		
		   Map<String, Object> map = new LinkedHashMap<>();
		   List<Type> type2 = dictionariesService.selectAllType2();
		   
		   if (type2.size()==0){
			   map.put("code",0);
			   map.put("msg","无精麻毒字典");
			   map.put("result", type2);
		   }else if(type2.size()>0){
			   map.put("code",0);
			   map.put("msg","success");
			   map.put("result", type2);  
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取精麻毒字典失败"); 
		   }
		   return map; 
		}

	@GetMapping("/selectallcrowd")
	public Map<String, Object> selectAllCrowd(){
		
		   Map<String, Object> map = new LinkedHashMap<>();
		   List<CrowdRestriction> crowd = dictionariesService.selectAllCrowd();
		   
		   if (crowd.size()==0){
			   map.put("code",0);
			   map.put("msg","无人群类型字典");
			   map.put("result", crowd);
		   }else if(crowd.size()>0){
			   map.put("code",0);
			   map.put("msg","success");
			   map.put("result", crowd);  
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取人群类型字典失败"); 
		   }
		   return map; 
		}
	@GetMapping("/selectalloperator")
	public Map<String, Object> selectAllOperator(){
		
		   Map<String, Object> map = new LinkedHashMap<>();
		   List<OperatorDictionary> operator = dictionariesService.selectAllOperator();
		   
		   if (operator.size()==0){
			   map.put("code",0);
			   map.put("msg","无运算符字典");
			   map.put("result", operator);
		   }else if(operator.size()>0){
			   map.put("code",0);
			   map.put("msg","success");
			   map.put("result", operator);  
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取运算符字典失败"); 
		   }
		   return map; 
		}
	@GetMapping("/selectallunit")
	public Map<String, Object> selectAllUnit(){
		
		   Map<String, Object> map = new LinkedHashMap<>();
		   List<UnitDictionary> unit = dictionariesService.selectAllUnit();
		   
		   if (unit.size()==0){
			   map.put("code",0);
			   map.put("msg","无药品单位字典");
			   map.put("result", unit);
		   }else if(unit.size()>0){
			   map.put("code",0);
			   map.put("msg","success");
			   map.put("result", unit);  
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取药品单位字典失败"); 
		   }
		   return map; 
		}
	
	@GetMapping("/selectcategory")
	public Map<String, Object> selectCategory(){
		
		   Map<String, Object> map = new LinkedHashMap<>();
		   List<Category> category = dictionariesService.selectCategory();
		   
		   if (category.size()==0){
			   map.put("code",0);
			   map.put("msg","无分类字典");
			   map.put("result", category);
		   }else if(category.size()>0){
			   map.put("code",0);
			   map.put("msg","success");
			   map.put("result", category);  
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取分类字典失败"); 
		   }
		   return map; 
		}
	
	@GetMapping("/selectallcompatibility")
	public Map<String, Object> selectAllCompatibility(){
		
		   Map<String, Object> map = new LinkedHashMap<>();
		   List<CompatibilitySelect> medcine = medcineService.selectMedcine();
		   List<CompatibilitySelect> category = medcineService.selectCategory();
		   List<CompatibilitySelect> labelList = medcineService.selectLabel();
		   List<CompatibilitySelect> obj = new ArrayList<CompatibilitySelect>();
			 for(int i = 0;i<medcine.size();i++) {
				 obj.add(medcine.get(i));
			 }
			 for(int i = 0;i<category.size();i++) {
				 obj.add(category.get(i));
			 }
			 for(int i = 0;i<labelList.size();i++) {
				 obj.add(labelList.get(i));
			 }
		   if (obj.size()==0){
			   map.put("code",0);
			   map.put("msg","无药品、标签、分类信息");
			   map.put("result", obj);
		   }else if(obj.size()>0){
			   map.put("code",0);
			   map.put("msg","success");
			   map.put("result", obj);  
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取无药品、标签、分类信息"); 
		   }
		   return map; 
		}
	@GetMapping("/selectreaction")
	public Map<String, Object> selectReaction(Integer type){
		
		   Map<String, Object> map = new LinkedHashMap<>();
		   List<Reaction> reaction = dictionariesService.selectReaction(type);
		   
		   if (reaction.size()==0){
			   map.put("code",0);
			   map.put("msg","无配伍管理描述内容");
			   map.put("result", reaction);
		   }else if(reaction.size()>0){
			   map.put("code",0);
			   map.put("msg","success");
			   map.put("result", reaction);  
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取配伍管理描述内容"); 
		   }
		   return map; 
		}
}
