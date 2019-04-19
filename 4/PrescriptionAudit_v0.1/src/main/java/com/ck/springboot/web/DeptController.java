package com.ck.springboot.web;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ck.springboot.pojo.Dept;
import com.ck.springboot.pojo.RespBean;
import com.ck.springboot.service.DeptService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DeptController {
	@Autowired DeptService deptService;
	
	@GetMapping(value = "/selectalldept")
	 public Map<String, Object> selectAllDept() throws Exception {
		   Map<String, Object> map = new LinkedHashMap<>();
		   List<Dept> dept = deptService.selectAllDept();
		   if (dept.size()==0){
			   map.put("code",0);
			   map.put("msg","无平台科室信息");
			   map.put("result", dept);
		   }else if (dept.size()>0) {
		   map.put("code",0);
		   map.put("msg","success");
		   map.put("result", dept);
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取平台科室信息失败"); 
		   }
		   return map; 
 }
	
	@RequestMapping(value = "/updatedept", method = RequestMethod.POST)
   public RespBean updateDept(Dept dept) throws IOException {
       if (deptService.updateDept(dept) == 1) {
           return RespBean.ok("更新成功!");
       }else if(deptService.updateDept(dept)==-1) {
    	   return RespBean.error("该科室名称已存在，更新失败!");
       }
       		return RespBean.error("更新失败!");
   }
	
	@RequestMapping(value = "/deletedept", method = RequestMethod.POST)
	 public RespBean deleteDept(Integer deptId) {
	        if (deptService.deleteDept(deptId) == 1) {
	            return RespBean.ok("删除成功!");
	        }
	        	return RespBean.error("删除失败!");
	    }
    @RequestMapping(value = "/insertdept", method = RequestMethod.POST)
    public RespBean insertDept(Dept dept) throws IOException{
        if (deptService.insertDept(dept) == 1) {
            return RespBean.ok("添加成功!");
        }else if(deptService.insertDept(dept) == -1) {
        	 return RespBean.error("该科室名称已存在，添加失败!");
        }
        return RespBean.error("添加失败!");
    }
}
