package com.ck.prescriptionAudit.web;

import com.ck.springboot.pojo.AuditCenter;
import com.ck.springboot.pojo.District;
import com.ck.springboot.pojo.RespBean;
import com.ck.springboot.pojo.UnitName;
import com.ck.springboot.service.AuditCenterService;
import com.ck.springboot.utils.TaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuditCenterController {
	@Resource
    AuditCenterService auditCenterService;
	//获取全部区域
	@GetMapping("/selectdistrict")
	public Map<String, Object> selectDistrict() throws IOException {
	   Map<String, Object> map = new LinkedHashMap<>();
	   List<District> district = auditCenterService.selectDistrict();
	   
	   if (district.size()==0){
		   map.put("code",0);
		   map.put("msg","无审方区域信息");
		   map.put("result", district); 
	   }else if(district.size()>0){
		   map.put("code",0);
		   map.put("msg","success");
		   map.put("result", district);  
	   }
	   else {
		   map.put("code",10000);
		   map.put("msg","获取审方区域信息失败"); 
	   }
	   return map;
	}
	//增加区域
    @RequestMapping(value = "/insertdistrict", method = RequestMethod.POST)
    public RespBean insertDistrict(District district) throws IOException {
        if (auditCenterService.insertDistrict(district) == 1) {
            return RespBean.ok("添加成功!");
        }else if(auditCenterService.insertDistrict(district) == -1){
        	return RespBean.error("该区域名称已存在，或传入数据有空值，增加失败!");
        }
        return RespBean.error("添加失败!");
    }
	//删除区域
	@RequestMapping(value = "/deletedistrict", method = RequestMethod.POST)
	 public RespBean deleteDistrict(Integer districtId) {
	        if (auditCenterService.deleteDistrict(districtId) == 1) {
	            return RespBean.ok("删除成功!");
	        }
	        	return RespBean.error("删除失败!");
	    }
	//更新区域
    @RequestMapping(value = "/updatedistrict", method = RequestMethod.POST)
    public RespBean updateDistrict(District district) throws IOException {
        if (auditCenterService.updateDistrict(district) == 1) {
            return RespBean.ok("更新成功!");
        }else if(auditCenterService.updateDistrict(district) == -1){
        	return RespBean.error("该区域名称已存在，更新失败!");
        }
        return RespBean.error("更新失败!");
    }
	//获取区域医院
	@GetMapping("/selectunitname")
	public Map<String, Object> selectAllUnitName(Integer id){
	   Map<String, Object> map = new LinkedHashMap<>();
	   List<UnitName> district = auditCenterService.selectAllUnitName(id);
	   if (district.size()==0){
		   map.put("code",0);
		   map.put("msg","无审方中心医院信息"); 
		   map.put("result", district); 
	   }else if(district.size()>0){
		   map.put("code",0);
		   map.put("msg","success");
		   map.put("result", district);  
	   }
	   else {
		   map.put("code",10000);
		   map.put("msg","获取审方中心医院信息失败"); 
	   }
	   return map;
	}
	//获取区域医院审方中心
	@GetMapping("/selectauditcenter")
	public Map<String, Object> selectAuditCenter(Integer id){
	   Map<String, Object> map = new LinkedHashMap<>();
	   List<AuditCenter> center = auditCenterService.selectAuditCenter(id);
	   if (center.size()==0){
		   map.put("code",0);
		   map.put("msg","无医院审方中心信息");
		   map.put("result", center); 
	   }else if(center.size()>0){
		   map.put("code",0);
		   map.put("msg","success");
		   map.put("result", center);  
	   }
	   else {
		   map.put("code",10000);
		   map.put("msg","获取医院审方中心信息失败");
	   }
	   return map;
	}
	//增加医院
    @RequestMapping(value = "/inserthospital", method = RequestMethod.POST)
    public RespBean insertHospital(UnitName unitname) throws IOException {
        if (auditCenterService.insertHospital(unitname) == 1) {
            return RespBean.ok("添加成功!");
        }else if(auditCenterService.insertHospital(unitname) == -1) {
        	return RespBean.error("该医院名已存在，或传入数据有空值，添加失败!");
        }
        return RespBean.error("添加失败!");
    }
	//删除医院
	@RequestMapping(value = "/deletehospital", method = RequestMethod.POST)
	 public RespBean deleteHospital(Integer id) {
	        if (auditCenterService.deleteHospital(id) == 1) {
	            return RespBean.ok("删除成功!");
	        }
	        	return RespBean.error("删除失败!");
	    }
	//更新医院
    @RequestMapping(value = "/updatehospital", method = RequestMethod.POST)
    public RespBean updateHospital(UnitName unitname) throws IOException {
    	
//    	if(auditCenterService.selectHospitalCounts(unitname)) {
//    		return RespBean.error("更新失败!");
//    	}
        if (auditCenterService.updateHospital(unitname) == 1) {
            return RespBean.ok("更新成功!");
        }else if (auditCenterService.updateHospital(unitname) == -1) {
        	return RespBean.error("该医院名已存在，更新失败!");
        }
        	return RespBean.error("更新失败!");
    }
    //增加审方中心
    @RequestMapping(value = "/insertauditcenter", method = RequestMethod.POST)
    public RespBean insertAuditCenter(AuditCenter center)  throws IOException{
    	
        if (StringUtils.isNotBlank(center.getIp()) && !TaleUtils.isIpAddress(center.getIp())) {
        	return RespBean.error("添加失败!,请检查IP地址格式是否正确");
        }
        if (auditCenterService.insertAuditCenter(center) == 1) {
            return RespBean.ok("添加成功!");
        }
        else if (auditCenterService.insertAuditCenter(center) == -1) {
        	return RespBean.error("该审方中心名称已存在，或传入数据有空值，添加失败!");
        }
        	return RespBean.error("添加失败!");
    }
	//删除审方中心
	@RequestMapping(value = "/deleteauditcenter", method = RequestMethod.POST)
	 public RespBean deleteCenterByPrimaryKey(Integer id) {
	        if (auditCenterService.deleteCenterByPrimaryKey(id) == 1) {
	            return RespBean.ok("删除成功!");
	        }
	        	return RespBean.error("删除失败!");
	    }
	
	//更新审方中心
    @RequestMapping(value = "/updateauditcenter", method = RequestMethod.POST)
    public RespBean updateAuditCenter(AuditCenter center)  throws IOException {
        if (StringUtils.isNotBlank(center.getIp()) && !TaleUtils.isIpAddress(center.getIp())) {
        	return RespBean.error("更新失败!,请检查IP地址格式是否正确");
        }else if(center.getIp() == null) {
        	return RespBean.error("更新失败!,IP地址不能为空");
        }
        if (auditCenterService.updateAuditCenter(center) == 1) {
            return RespBean.ok("更新成功!");
        }else if (auditCenterService.updateAuditCenter(center) == -1) {
        	return RespBean.error("该审方中心名称已存在，更新失败!");
        }
        return RespBean.error("更新失败!");
    }
    
	//连接审方中心
    @RequestMapping(value = "/linkauditcenter", method = RequestMethod.POST)
    public RespBean linkAuditCenter(String ip) {
        if (StringUtils.isNotBlank(ip) && !TaleUtils.isIpAddress(ip)) {
        	return RespBean.error("连接失败!,请检查IP地址格式是否正确");
        }else if(ip == null) {
        	return RespBean.error("连接失败!,IP地址不能为空");
        }
            return RespBean.ok("连接成功!");
    }
}
