package com.ck.prescriptionAudit.web;


import com.ck.springboot.pojo.Config;
import com.ck.springboot.pojo.RespBean;
import com.ck.springboot.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ConfigController {
	@Autowired
    ConfigService configService;
	@GetMapping(value = "/selectallconfig")
	 public Map<String, Object> selectAllConfig() throws Exception {
//	  	return  configService.selectAllConfig();
		   Map<String, Object> map = new LinkedHashMap<>();
		   List<Config> config = configService.selectAllConfig();
//		   List<String> list = new ArrayList<String>();
		   
		   Map<String, Object> map1 = new LinkedHashMap<>();
		   
		   for(int i = 0;i<config.size();i++) {			  
//			  String key = "name" + String.valueOf(i);
//			  String _keyname = config.get(i).getName();		  
//			  String _strValue = "value" + String.valueOf(i);
//			  String _value = config.get(i).getValues();	  
//			  list.add( key + ":" + _keyname + _strValue + ":" +_value);
			   map1.put("name" + String.valueOf(i),config.get(i).getName());
			   map1.put("value" + String.valueOf(i),config.get(i).getValues());
		   }
		   
		   if (map1.size()==0){
			   map.put("code",0);
			   map.put("msg","无系统设置信息");
			   map.put("result", map1);
		   }else if(map1.size()>0){
		   map.put("code",0);
		   map.put("msg","success");
		   map.put("result", map1);  
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取系统设置信息失败"); 
		   }
		   return map;  
  }

	@GetMapping(value = "/selectallrangeconfig")
	 public Map<String, Object> selectAllRangeConfig() throws Exception {
		   Map<String, Object> map = new LinkedHashMap<>();
		   List<Config> config = configService.selectAllRangeConfig();
		   Map<String, Object> map1 = new LinkedHashMap<>();
		   
		   for(int i = 0;i<config.size();i++) {			  
			   map1.put("name" + String.valueOf(i),config.get(i).getName());
			   map1.put("value" + String.valueOf(i),config.get(i).getValues());
		   }
		   
		   if (map1.size()==0){
			   map.put("code",0);
			   map.put("msg","无审核范围设置信息");
			   map.put("result", map1);
		   }else if(map1.size()>0){
		   map.put("code",0);
		   map.put("msg","success");
		   map.put("result", map1);  
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取审核范围设置信息失败"); 
		   }
		   return map;  
 }
	@RequestMapping(value = "/updateconfig", method = RequestMethod.POST)
    public RespBean updateConfig(@RequestBody List<Config> configs) {
        if (configService.updateConfig(configs) > 0) {
            return RespBean.ok("更新成功!");
        }else{
        	return RespBean.error("更新失败!");
        }
    }
	@RequestMapping(value = "/updaterangeconfig", method = RequestMethod.POST)
    public RespBean updateRangeConfig(@RequestBody List<Config> configs) {
        if (configService.updateRangeConfig(configs) > 0) {
            return RespBean.ok("更新成功!");
        }else{
        	return RespBean.error("更新失败!");
        }
    }

	/* 超日用量时间设置 */
	@GetMapping(value = "/selectsetuptime")
	 public Map<String, Object> selectSetUpTime() throws Exception {

		   Map<String, Object> map = new LinkedHashMap<>();
		   List<Config> config = configService.selectSetUpTime();
		   
		   Map<String, Object> map1 = new LinkedHashMap<>();
		   
		   for(int i = 0;i<config.size();i++) {
			   map1.put("name" + String.valueOf(i),config.get(i).getName());
			   map1.put("value" + String.valueOf(i),config.get(i).getValues());
		   }
		   
		   if (map1.size()==0){
			   map.put("code",0);
			   map.put("msg","无系统设置信息");
			   map.put("result", map1);
		   }else if(map1.size()>0){
		   map.put("code",0);
		   map.put("msg","success");
		   map.put("result", map1);  
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取系统设置信息失败"); 
		   }
		   return map;  
  }
	/* 超日用量时间设置 */
	@GetMapping(value = "/selectsetuprule")
	 public Map<String, Object> selectSetUpRule() throws Exception {

		   Map<String, Object> map = new LinkedHashMap<>();
		   List<Config> config = configService.selectSetUpRule();
		   
		   Map<String, Object> map1 = new LinkedHashMap<>();
		   for(int i = 0;i<config.size();i++) {
			   map1.put("name" + String.valueOf(i),config.get(i).getName());
			   map1.put("value" + String.valueOf(i),config.get(i).getValues());
		   }
		   if (map1.size()==0){
			   map.put("code",0);
			   map.put("msg","无系统设置信息");
			   map.put("result", map1);
		   }else if(map1.size()>0){
		   map.put("code",0);
		   map.put("msg","success");
		   map.put("result", map1);  
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取系统设置信息失败"); 
		   }
		   return map;  
 }
}
