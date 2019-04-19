/**
 * 
 */
package com.ck.springboot.web;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ck.springboot.pojo.DosageLimit;
import com.ck.springboot.pojo.RespBean;
import com.ck.springboot.service.DosageLimitService;


/**  
* <p>Description: </p>  
* @author 胡高翔  
* @date 2019年3月11日  
*/
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DosageLimitController {
	
	@Autowired DosageLimitService dosageLimitService;
	
	@GetMapping(value = "/selectdosagebymedcineid")
	public Map<String, Object> selectByMedcineId(@PathParam("medcineId") Integer medcineId) {
		
		   Map<String, Object> map = new LinkedHashMap<>();
		   List<DosageLimit> dosage = dosageLimitService.selectByMedcineId(medcineId);
		   if (dosage.size()==0){
			   map.put("code",0);
			   map.put("msg","无总剂量限用信息");
			   map.put("result", dosage);
		   }else if(dosage.size()>0){
		   map.put("code",0);
		   map.put("msg","success");
		   map.put("result", dosage);  
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取总剂量限用信息失败"); 
		   }
		   return map; 
	}
	@GetMapping(value = "/selectdosagebyid")
	public Map<String, Object> selectById(@PathParam("id") Integer id) {
		
		   Map<String, Object> map = new LinkedHashMap<>();
		   List<DosageLimit> dosage = dosageLimitService.selectById(id);
		   if (dosage.size()==0){
			   map.put("code",0);
			   map.put("msg","无总剂量限用信息");
			   map.put("result", dosage);
		   }else if(dosage.size()>0){
		   map.put("code",0);
		   map.put("msg","success");
		   map.put("result", dosage);  
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取总剂量限用信息失败"); 
		   }
		   return map; 
	}
    @RequestMapping(value = "/deletedosagebyid", method = RequestMethod.POST)
    public RespBean deleteDosageById(Integer id) {
        if (dosageLimitService.deleteDosageById(id) == 1) {
            return RespBean.ok("删除成功!");
        }
        	return RespBean.error("删除失败!");
    }
    
    @RequestMapping(value = "/updatedosagebyid", method = RequestMethod.POST)
    public RespBean updateDosageById(DosageLimit dos) throws IOException{
    	int result = dosageLimitService.updateDosageById(dos);
        if ( result== 1) {
            return RespBean.ok("更新成功!");
        }else if (result == -1) {
        	return RespBean.error("更新失败,范围输入不正确!");
        }else if(result == -2)
        {
        	return RespBean.error("更新失败,总剂量不能小于0!");
        }else if(result == -3)
        {
        	return RespBean.error("更新失败,该规则名称已存在!");
        }
        return RespBean.error("更新失败!");
    }
    
    @RequestMapping(value = "/insertdosage", method = RequestMethod.POST)
    public RespBean insertDosage(DosageLimit dos) throws IOException{
    	int result = dosageLimitService.insertDosage(dos);
    	if (dos.getRuleName()!=null && dos.getLevel()!=null)  {
	        if (result == 1) {
	            return RespBean.ok("添加成功!");
	        }else if (result ==-1) {
	     	   return RespBean.error("更新失败,范围输入不正确!");
	     }else if(result == -2)
        {
        	return RespBean.error("更新失败,总剂量不能小于0!");
        }else if(result == -3)
        {
        	return RespBean.error("更新失败,该规则名称已存在!");
        }
	     return RespBean.error("添加失败!");
    }
    	 return RespBean.error("添加失败,规则存在空数据!");
    }
}
