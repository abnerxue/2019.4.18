/**
 * 
 */
package com.ck.springboot.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ck.springboot.pojo.Permission;
import com.ck.springboot.service.PermissionService;

/**  
* <p>Description: </p>  
* @author 胡高翔  
* @date 2019年3月7日  
*/
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PermissionController {
	@Autowired PermissionService permissionService;
	
	
	@GetMapping(value="/getpermission")
	public Map<String, Object> getPermission(){
		Map<String, Object> map = new LinkedHashMap<>();
		List<Permission> permission = permissionService.getPermission();
		
		if (permission.size()==0){
			   map.put("code",0);
			   map.put("msg","无权限信息");
			   map.put("result", permission);
		   }else if (permission.size()>0) {
		   map.put("code",0);
		   map.put("msg","success");
		   map.put("result", permission);
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取权限信息失败"); 
		   }
		   return map; 
	}
}
