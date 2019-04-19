package com.ck.prescriptionAudit.web;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.ck.springboot.pojo.GsonTageAangeIndication;
import com.ck.springboot.pojo.GsonZyyCheckLimit;
import com.ck.springboot.pojo.LiverKidneyDic;
import com.ck.springboot.pojo.RespBean;
import com.ck.springboot.pojo.ZyyCheckLimt;
import com.ck.springboot.service.CheckValuesSetService;
import com.google.gson.Gson;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@SuppressWarnings("all")
public class CheckValuesSetController {
	@Resource
	private CheckValuesSetService checkValuesSetService;
	
	@GetMapping("getchecklimitlist")
	public Map<String ,Object> getCheckLimitListByType(@RequestParam(defaultValue = "1")int type){
		Map<String,Object> map = new HashMap();
		List<GsonZyyCheckLimit> list = new ArrayList<GsonZyyCheckLimit>();
		if(type == 1) {
			list = checkValuesSetService.getCheckLimitListByType(1, 2);
		}else if(type == 2) {
			list = checkValuesSetService.getCheckLimitListByType(3, 4);
		}
		if(list == null) {
			map.put("code",0);
			map.put("msg","无检查信息");
			map.put("result", list);
		}else if(null != list) {
			map.put("code",0);
			map.put("msg","success");
			map.put("result", list);
		}else {
			map.put("code",10000);
			map.put("msg","获取检查信息失败");
		}
		return map;
	}
	
	//添加规则
	@PostMapping("addchecklimit")
	public RespBean addCheckLimit(@RequestBody String jsonStr) {
		GsonZyyCheckLimit checklist = new Gson().fromJson(jsonStr,GsonZyyCheckLimit.class);
		List<ZyyCheckLimt> list = checklist.getCheckLimit();
		int liverKidneyDicId = checklist.getLiverKidneyDicId();//肝肾id
		//判断肝肾是否重复
		if(checkValuesSetService.getliverKidneyCount(liverKidneyDicId)>0) {
			return RespBean.error("该肝肾功能已经存在，添加失败！");
		}
		//判断是否存在重复的检查项目
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if(i == j) {
					continue;
				}else if(list.get(i).getCheckItemsId() == list.get(j).getCheckItemsId()) {
					return RespBean.error("检查项目重复，添加失败！");
				}
			}
		}
		//添加检验项目
		for(ZyyCheckLimt zyy : checklist.getCheckLimit()) {
			zyy.setRuleName("检验项目");
			zyy.setLiverkidney(liverKidneyDicId);
			if(checkValuesSetService.addCheckLimit(zyy) != 1) {
				return RespBean.error("添加失败！");
			}
		}
		return RespBean.ok("添加成功！");
	}
	
	//修改规则
	@PostMapping("updatechecklimit")
	public RespBean updateChecklimit(@RequestBody String jsonStr) {
		GsonZyyCheckLimit checklist = new Gson().fromJson(jsonStr,GsonZyyCheckLimit.class);
		List<ZyyCheckLimt> list = checklist.getCheckLimit();
		int liverKidneyDicId = checklist.getLiverKidneyDicId();//肝肾id
		int id = list.get(0).getId();
		int realLiverKidney = checkValuesSetService.getliverKidneyDicIdById(id);
		if(!(liverKidneyDicId == realLiverKidney) && checkValuesSetService.getliverKidneyCount(liverKidneyDicId)>0) {
			return RespBean.error("该肝肾功能已经存在，修改失败！");
		}
		
		checkValuesSetService.deletecheckLimitByliverKidneyDicId(liverKidneyDicId);
		
		if(list != null) {
			//判断是否存在重复的检查项目
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < list.size(); j++) {
					if(i == j) {
						continue;
					}else if(list.get(i).getCheckItemsId() == list.get(j).getCheckItemsId()) {
						return RespBean.error("检查项目重复，修改失败！");
					}
				}
			}
		}
		
		if(list != null) {
			for(ZyyCheckLimt zyy : list) {
				zyy.setRuleName("检验项目");
				zyy.setLiverkidney(liverKidneyDicId);
				if(checkValuesSetService.addCheckLimit(zyy) != 1) {
					return RespBean.error("修改失败！");
				}
			}
		}
		
		return RespBean.ok("修改成功！");
	}
	
	//获取检查项目列表
	@GetMapping("getcheckitems")
	public Map<String,Object> getCheckItems(){
		Map<String,Object> map = new HashMap();
		List<CheckItems> itemList = checkValuesSetService.getCheckItemsList();
		
		if(null == itemList) {
			map.put("code",0);
			map.put("msg","无检查信息");
			map.put("result", itemList);
		}else if(null != itemList) {
			map.put("code",0);
			map.put("msg","success");
			map.put("result", itemList);
		}else {
			map.put("code",10000);
			map.put("msg","获取检查信息失败");
		}
		
		return map;
	}
	
	//获取肝功能列表
	@GetMapping("getliverlist")
	public Map<String,Object> getLiverList(){
		Map<String,Object> map = new HashMap();
		List<LiverKidneyDic> liverKidneyList = checkValuesSetService.getLiverKidneyDicList(1,2);
		
		if(null == liverKidneyList) {
			map.put("code",0);
			map.put("msg","无肝功能信息");
			map.put("result", liverKidneyList);
		}else if(null != liverKidneyList) {
			map.put("code",0);
			map.put("msg","success");
			map.put("result", liverKidneyList);
		}else {
			map.put("code",10000);
			map.put("msg","获取信息失败");
		}
		return map;
	}
	
	//获取肾功能列表
	@GetMapping("getkidneydiclist")
	public Map<String,Object> getKidneyDicList(){
		Map<String,Object> map = new HashMap();
		List<LiverKidneyDic> liverKidneyList = checkValuesSetService.getLiverKidneyDicList(3,4);
		
		if(null == liverKidneyList) {
			map.put("code",0);
			map.put("msg","无肾功能信息");
			map.put("result", liverKidneyList);
		}else if(null != liverKidneyList) {
			map.put("code",0);
			map.put("msg","success");
			map.put("result", liverKidneyList);
		}else {
			map.put("code",10000);
			map.put("msg","获取信息失败");
		}
		return map;
	}
	
	//删除规则
	@PostMapping("delatechecklimit")
	public RespBean deleteChecklimit(int liverKidneyDicId) {
		if(checkValuesSetService.deletecheckLimitByliverKidneyDicId(liverKidneyDicId) == 0) {
			return RespBean.error("删除失败！");
		}
		return RespBean.ok("删除成功！");
	}
	
	//点击修改按钮 获取详情
	@GetMapping("getchecklimitlistbyliverkidneydicId")
	public Map<String ,Object> getCheckLimitListByliverKidneyDicId(int liverKidneyDicId){
		Map<String,Object> map = new HashMap();
		List<GsonZyyCheckLimit> list = checkValuesSetService.getCheckLimitListByliverKidneyDicId(liverKidneyDicId);
		if(list == null) {
			map.put("code",0);
			map.put("msg","无检查信息");
			map.put("result", list);
		}else if(null != list) {
			map.put("code",0);
			map.put("msg","success");
			map.put("result", list);
		}else {
			map.put("code",10000);
			map.put("msg","获取检查信息失败");
		}
		return map;
	}
	
	
}
