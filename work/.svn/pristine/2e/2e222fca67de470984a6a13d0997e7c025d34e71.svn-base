package com.ck.prescriptionAudit.web;

import com.ck.springboot.pojo.Label;
import com.ck.springboot.pojo.Page;
import com.ck.springboot.pojo.RespBean;
import com.ck.springboot.service.LabelManagerService;
import com.ck.springboot.utils.GsonLabelMedicine;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LabelManagerController {
	@Resource
	private LabelManagerService labelManagerService;
	
	//添加
	@RequestMapping(value="/addlabel",method = RequestMethod.POST)
	public RespBean addLabel(String name) {
		if(name==null||"".equals(name)) {
			return RespBean.error("内容不能为空，添加失败!");
		}else if(labelManagerService.getLabelCountsByName(name)>0) {
			return RespBean.error("标签名重复，添加失败!");
		}
		if(labelManagerService.addLabel(name)>0) {
			return RespBean.ok("添加成功!");
		}
		return RespBean.error("添加失败!");
	}
	
	//修改
	@RequestMapping(value="updatelabelname",method = RequestMethod.POST)
	public RespBean updateLabelName(Integer id, String name) {
		String realName = labelManagerService.getNameById(id);
		if(!name.equals(realName)&&labelManagerService.getLabelCountsByName(name)>0) {
			return RespBean.error("标签名重复，修改失败!");
		}
		if(name==null||"".equals(name)) {
			return RespBean.error("内容不能为空，修改失败!");
		}else if(labelManagerService.updateLabelName(id, name)>0) {
			return RespBean.ok("修改成功!");
		}
		return RespBean.error("修改失败!");
	}
	
	//查询
	@RequestMapping(value="getlabellist",method = RequestMethod.GET)
	public Map<String, Object> getLabelList(String name){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Label> labelList = labelManagerService.getLabelList(name);
		if (labelList.size()==0){
			   map.put("code",0);
			   map.put("msg","无标签信息");
			   map.put("result", labelList);
		   }else if (labelList.size()>0){
		   map.put("code",0);
		   map.put("msg","success");
		   map.put("result", labelList);
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取标签失败"); 
		   }
		return map;
	}
	
	//删除
	@RequestMapping(value = "deletelabelbyid",method = RequestMethod.POST)
	public RespBean deleteLabelById(Integer id) {
		labelManagerService.deleteLabelAndMedicine(id);
		if(labelManagerService.deleteLabelById(id)>0) {
			return RespBean.ok("删除成功!");
		}
		return RespBean.error("删除失败!");
	}
	
	//查看单个标签下的药品
	@RequestMapping(value = "getmedicinelistbylabelid",method = RequestMethod.GET)
	public Map<String, Object> getMedicineListByLabelId(Integer id,
			@RequestParam(defaultValue = "1") Integer pageNo,
	        @RequestParam(defaultValue = "10") Integer pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Label> labelMedicineList = labelManagerService.getMedicineListByLabelId(id, pageNo, pageSize);
		int totleCounts = labelManagerService.getMedicineCountsByLabelId(id);
		int totalPage=0;
		  if (totleCounts%pageSize == 0){
			   totalPage = totleCounts/pageSize;
		   }else {
			   totalPage = totleCounts/pageSize + 1;
		   }

		   Page p = new Page(pageNo,pageSize,totalPage,totleCounts);
		
		if (labelMedicineList.size()==0){
			   map.put("code",0);
			   map.put("msg","无药品信息");
			   map.put("result", labelMedicineList);
		   }else if (labelMedicineList.size()>0){
			   map.put("code",0);
			   map.put("msg","success");
			   map.put("page", p);
			   map.put("result", labelMedicineList);
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取药品信息失败"); 
		   }
		return map;
	}
	
	//编辑标签下的药品
	@PostMapping("updatelabelmadicinedetail")
	public RespBean updateLabelMadicineDetail(@RequestBody String jsonStr) {
		GsonLabelMedicine gsonlabel = new Gson().fromJson(jsonStr,GsonLabelMedicine.class);
		Integer labelId = gsonlabel.getLabelId();
		List<Integer> medicineId = gsonlabel.getMedicineId();
		labelManagerService.deleteLabelAndMedicine(labelId);
		if(medicineId.size()>0) {
			for (int i = 0; i < medicineId.size(); i++) {
				if(labelManagerService.addLabelAndMedicine(medicineId.get(i), labelId)!=1) {
					return RespBean.error("编辑失败!");
				}
			}
		}
		return RespBean.ok("编辑成功!");
	}
	
	
	
}
