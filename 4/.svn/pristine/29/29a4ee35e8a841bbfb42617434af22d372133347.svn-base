package com.ck.springboot.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ck.springboot.pojo.DeptRestriction;
import com.ck.springboot.pojo.RespBean;
import com.ck.springboot.service.DeptRestrictionService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DeptRestrictionController {
	@Autowired DeptRestrictionService deptRestrictionService;
	
	@GetMapping(value = "/selectdeptbymedcineid")
	public Map<String, Object> selectByMedcineId(@PathParam("medcineId") Integer medcineId) {
		
		   Map<String, Object> map = new LinkedHashMap<>();
		   List<DeptRestriction> deptrestriction = deptRestrictionService.selectByMedcineId(medcineId);
		   if (deptrestriction.size()==0){
			   map.put("code",0);
			   map.put("msg","无科室限制信息");
			   map.put("result", deptrestriction);
		   }else if(deptrestriction.size()>0){
		   map.put("code",0);
		   map.put("msg","success");
		   map.put("result", deptrestriction);  
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取科室限制信息失败"); 
		   }
		   return map; 
	}
    @RequestMapping(value = "/deletedeptbyid", method = RequestMethod.POST)
    public RespBean deleteDeptById(Integer id) {
        if (deptRestrictionService.deleteDeptById(id) == 1) {
            return RespBean.ok("删除成功!");
        }
        	return RespBean.error("删除失败!");
    }
	@RequestMapping(value = "/savedeptrestriction", method = RequestMethod.POST)
	 public RespBean updateByPrimaryKey(@RequestBody List<DeptRestriction> record) {
        if (deptRestrictionService.updateByPrimaryKey(record) > 0) {
            return RespBean.ok("更新成功!");
        }
        	return RespBean.error("更新失败!");
    }
}
