package com.ck.prescriptionAudit.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ck.springboot.pojo.AuditLevel;
import com.ck.springboot.pojo.Diagnosis;
import com.ck.springboot.pojo.GsonTageAangeIndication;
import com.ck.springboot.pojo.GsonTgestationalWeekIndication;
import com.ck.springboot.pojo.GsonTgroupIndication;
import com.ck.springboot.pojo.GsonTpathologyPhysiologyIndication;
import com.ck.springboot.pojo.MedcineFold;
import com.ck.springboot.pojo.Page;
import com.ck.springboot.pojo.RespBean;
import com.ck.springboot.pojo.TageAangeIndication;
import com.ck.springboot.pojo.TgestationalWeekIndication;
import com.ck.springboot.pojo.TgroupIndication;
import com.ck.springboot.pojo.TpathologyPhysiologyIndication;
import com.ck.springboot.service.IndicationLimitService;
import com.google.gson.Gson;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class IndicationLimitController {
	@Resource
	private IndicationLimitService indicationLimitService;
	
	//获取诊断列表
	@RequestMapping(value = "getdiagnosislist",method = RequestMethod.GET)
	public Map<String,Object> getDiagnosisList(@RequestParam(defaultValue = "1") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,String name){
		Map <String,Object> map = new HashMap();
		List<Diagnosis> list = indicationLimitService.getDiagnosisList(pageNo,pageSize,name);
		int totleCount = indicationLimitService.getDiagnosisCounts(name);
		int totlePage = totleCount%pageSize==0 ? totleCount/pageSize : totleCount/pageSize + 1;
		Page page = new Page(pageNo, pageSize, totlePage, totleCount);
		
		if(list.size()==0) {
			map.put("code",0);
			map.put("msg","无诊断信息");
			map.put("result", list);
		}else if(list.size()>0) {
			map.put("code",0);
			map.put("msg","success");
			map.put("result", list);
			map.put("page", page);
		}else {
			map.put("code",10000);
			map.put("msg","获取诊断信息失败");
		}
		
		return map;
	}
	
	//搜索药品
	@GetMapping("getmedicine")
	public Map<String,Object> getMedicine(String name){
		Map<String,Object> map = new HashMap();
		List<MedcineFold> list = indicationLimitService.getMedicine(name);
		if(list.size()==0) {
			map.put("code",0);
			map.put("msg","无药品信息");
			map.put("result", list);
		}else if(list.size()>0) {
			map.put("code",0);
			map.put("msg","success");
			map.put("result", list);
		}else {
			map.put("code",10000);
			map.put("msg","获取药品信息失败");
		}
		return map;
	}
	
	//获取适用症列表
	@GetMapping("getallindicationbydiagnosisid")
	public Map<String,Object> getAllIndicationByDiagnosisId(int diagnosisId){
		Map<String,Object> map = new HashMap();
		List<TageAangeIndication> ageList = indicationLimitService.getTageAangeIndicationListBydiagnosisId(diagnosisId);
		List<TgestationalWeekIndication> gestationaList = indicationLimitService.getTgestationalWeekIndicationListBydiagnosisId(diagnosisId);
		List<TgroupIndication> groupList = indicationLimitService.getTgroupIndicationListBydiagnosisId(diagnosisId);
		List<TpathologyPhysiologyIndication> physiologyList = indicationLimitService.getTpathologyPhysiologyIndicationListBydiagnosisId(diagnosisId);
		Map<String,Object> map2 = new HashMap();
		map2.put("ageList", ageList);
		map2.put("gestationaList", gestationaList);
		map2.put("groupList", groupList);
		map2.put("physiologyList", physiologyList);
		if(ageList.size()==0&&gestationaList.size()==0&&groupList.size()==0&&physiologyList.size()==0) {
			map.put("code",0);
			map.put("msg","无适应症信息信息");
			map.put("result", map2);
		}else if(ageList.size()>0||gestationaList.size()>0||groupList.size()>0||physiologyList.size()>0) {
			map.put("code",0);
			map.put("msg","success");
			map.put("result", map2);
		}else {
			map.put("code",10000);
			map.put("msg","获取诊断信息失败");
		}
		
		return map;
	}
	
	//修改年龄适应症
	@PostMapping("updateageindication")
	public RespBean insertAgeIndication(@RequestBody String jsonStr) {
		GsonTageAangeIndication gsonage = new Gson().fromJson(jsonStr,GsonTageAangeIndication.class);
		int diagnosisId = gsonage.getDiagnosisId();
		indicationLimitService.deleteAgeIndication(diagnosisId);
		List<TageAangeIndication> tlist = gsonage.getAgeList();
		if(tlist.size()>0) {
			for(TageAangeIndication tage : tlist) {
				tage.setDiagnosisId(diagnosisId);
				if(indicationLimitService.insertAgeIndication(tage) !=1 ) {
					return RespBean.error("保存失败！");
				}
			}
		}
		return RespBean.ok("保存成功！");
	}
	
	//修改孕周适应症
	@PostMapping("updatetgestationalweekindication")
	public RespBean addTgestationalWeekIndication(@RequestBody String jsonStr) {
		GsonTgestationalWeekIndication gsonweek = new Gson().fromJson(jsonStr,GsonTgestationalWeekIndication.class);
		int diagnosisId = gsonweek.getDiagnosisId();
		indicationLimitService.deleteTgestationalWeekIndication(diagnosisId);
		if(gsonweek.getWeekList().size()>0) {
			for(TgestationalWeekIndication week : gsonweek.getWeekList()) {
				week.setDiagnosisId(diagnosisId);
				if(indicationLimitService.addTgestationalWeekIndication(week) != 1) {
					return RespBean.error("保存失败！");
				}
			}
		}
		return RespBean.ok("保存成功！");
	}
	
	//修改人群适应症
	@PostMapping("updatetgroupindication")
	public RespBean addTgroupIndication(@RequestBody String jsonStr) {
		GsonTgroupIndication gsongroup = new Gson().fromJson(jsonStr,GsonTgroupIndication.class);
		int diagnosisId = gsongroup.getDiagnosisId();
		indicationLimitService.deleteTgroupIndication(diagnosisId);
		if(gsongroup.getGroupList().size()>0) {
			for(TgroupIndication group : gsongroup.getGroupList()) {
				group.setDiagnosisId(diagnosisId);
				if(indicationLimitService.addTgroupIndication(group) != 1) {
					return RespBean.error("保存失败！");
				}
			}
		}
		return RespBean.ok("保存成功！");
	}
	
	//修改病生理
	@PostMapping("updatetpathologyphysiologyindication")
	public RespBean addTpathologyPhysiologyIndication(@RequestBody String jsonStr) {
		GsonTpathologyPhysiologyIndication gsonphy = new Gson().fromJson(jsonStr,GsonTpathologyPhysiologyIndication.class);
		int diagnosisId = gsonphy.getDiagnosisId();
		indicationLimitService.deleteTpathologyPhysiologyIndication(diagnosisId);
		if(gsonphy.getPhyList().size()>0) {
			for(TpathologyPhysiologyIndication phy : gsonphy.getPhyList()) {
				phy.setDiagnosisId(diagnosisId);
				if(indicationLimitService.addTpathologyPhysiologyIndication(phy) != 1) {
					return RespBean.error("保存失败！");
				}
			}
		}
		return RespBean.ok("保存成功！");
	}
	
	//获取审核状态列表
	@GetMapping("getauditlevellist")
	public Map<String,Object> getAuditLevelList(){
		Map<String,Object> map = new HashMap();
		List<AuditLevel> levelList = indicationLimitService.getAuditLevelList();
		if(levelList == null) {
			map.put("code",0);
			map.put("msg","无审核等级信息");
			map.put("result", levelList);
		}else if(null != levelList) {
			map.put("code",0);
			map.put("msg","success");
			map.put("result", levelList);
		}else {
			map.put("code",10000);
			map.put("msg","获取信息失败");
		}
		
		return map;
	}
	
	
	
	
	
	
	
	
	
}
