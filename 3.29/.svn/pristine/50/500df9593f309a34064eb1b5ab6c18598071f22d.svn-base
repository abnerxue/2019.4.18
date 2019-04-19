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
import com.ck.springboot.pojo.CrowdRestrictionMedcine;
import com.ck.springboot.pojo.RespBean;
import com.ck.springboot.service.CrowdRestrictionMedcineService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

public class CrowdRestrictionMedcineController {
	@Autowired CrowdRestrictionMedcineService crowdRestrictionMedcineService;
	
	@GetMapping(value = "/selectcrowdbymedcineid")
	public Map<String, Object> selectByMedcineId(@PathParam("medcineId") Integer medcineId) {
		
		
//		return crowdRestrictionMedcineService.selectByMedcineId(medcineId);
		Map<String, Object> map = new LinkedHashMap<>();
		   List<CrowdRestrictionMedcine> crowdrestrictionmedcine = crowdRestrictionMedcineService.selectByMedcineId(medcineId);
		   if (crowdrestrictionmedcine.size()==0){
			   map.put("code",0);
			   map.put("msg","无人群限制信息");
			   map.put("result", crowdrestrictionmedcine);
		   }else if(crowdrestrictionmedcine.size()>0){
		   map.put("code",0);
		   map.put("msg","success");
		   map.put("result", crowdrestrictionmedcine);  
		   }else
		   {
			   map.put("code",10000);
			   map.put("msg","获取人群限制信息失败"); 
		   }
		   return map; 
	}
	
    @RequestMapping(value = "/deletecrowdbyid", method = RequestMethod.POST)
    public RespBean deleteCrowdById(Integer id) {
        if (crowdRestrictionMedcineService.deleteCrowdById(id) == 1) {
            return RespBean.ok("删除成功!");
        }
        	return RespBean.error("删除失败!");
    }
	@RequestMapping(value = "/savecrowdrestriction", method = RequestMethod.POST)
	 public RespBean updateByPrimaryKey(@RequestBody List<CrowdRestrictionMedcine> record) {
       if (crowdRestrictionMedcineService.updateByPrimaryKey(record) > 0) {
           return RespBean.ok("更新成功!");
       }
       	return RespBean.error("更新失败!");
   }
}
