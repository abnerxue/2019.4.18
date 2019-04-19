/**
 * 
 */
package com.ck.springboot.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ck.springboot.pojo.JsonRolePermission;
import com.ck.springboot.pojo.Permission;
import com.ck.springboot.pojo.RespBean;
import com.ck.springboot.pojo.Role;
import com.ck.springboot.pojo.RolePermission;
import com.ck.springboot.service.RoleService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


/**  
* <p>Description: </p>  
* @author 胡高翔  
* @date 2019年3月7日  
*/
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

public class RoleController {
	@Autowired RoleService roleService;
	
	@GetMapping(value="/getrolename")
	public Map<String, Object> getRoleName(Integer roleId){

		Map<String, Object> map = new LinkedHashMap<>();
		List<Role> role = roleService.getRole(roleId);
		   if (role.size()==0){
			   map.put("code",0);
			   map.put("msg","无角色名称信息");
			   map.put("result", role);
		   }else if (role.size()>0){
		   map.put("code",0);
		   map.put("msg","success");
		   map.put("result", role);
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取角色名称失败"); 
		   }
		   return map;
	}

	
	@GetMapping(value="/getrolepermission")
	public Map<String, Object> getRole(Integer roleId){
		
		Map<String, Object> map = new LinkedHashMap<>();
		List<Role> role = roleService.getRole(roleId);
		List<Object> list = new ArrayList<Object>();
		if(role.size()>0) {

		for(int i = 0;i<role.size();i++) {
			Map<String, Object> map1 = new LinkedHashMap<>();
//			List<RolePermission> rolepermission = roleService.getRolePermission(role.get(i).getRoleId());
			List<Permission> permission = roleService.getPermission();
			map1.put("roleId",role.get(i).getRoleId());
			map1.put("roleName",role.get(i).getRoleName());
			for(int j = 0;j<permission.size();j++) {
//				map1.put("permissionId" + String.valueOf(j+1),rolepermission.get(j).getRoleId());		
//				map1.put("permissionName" + String.valueOf(j+1),rolepermission.get(j).getState().toString());
				map1.put("permissionName" + String.valueOf(j+1),roleService.judgeRolePermission(role.get(i).getRoleId(), j+1));
			}
//			map.put(role.get(i).getRoleName(), map1);
			list.add(map1);
//			return map1;
		}
		 map.put("code",0);
		 map.put("msg","success");
		 map.put("result",list);
		}
		
		   return map;
	}
	
	@GetMapping(value="/getrolepermissionbyid")
	public Map<String, Object> getRoleById(Integer roleId){
		
		Map<String, Object> map = new LinkedHashMap<>();
		List<Role> role = roleService.getRole(roleId);

		List<RolePermission> rolepermission = roleService.getRolePermission(roleId);
		long[] pers = new long[rolepermission.size()];
		if(role.size()>0) {
		for(int i = 0;i<rolepermission.size();i++) {
			pers[i]= rolepermission.get(i).getPermissionId();
		}
		 map.put("code",0);
		 map.put("msg","success");
		 map.put("roleName",role.get(0).getRoleName());
		 map.put("pres",pers);
		}else {
			map.put("code", 0);
			map.put("msg", "不存在该角色");
		}
		 return map;
	}
    @RequestMapping(value = "/updaterole", method = RequestMethod.POST)
    public RespBean updateRole(String roleName,Integer roleId,long[] pers) throws IOException{
    	if (roleService.updateRoleName(roleId,roleName)>=0 &&  roleService.deleteRolePermission(roleId) >=0 &&
    	roleService.insertRolePermission(roleId,pers)>=0) {
    		return RespBean.ok("更新成功!");
    	}
    	return RespBean.ok("更新失败!");
    }
    @RequestMapping(value = "/deleterole", method = RequestMethod.POST)
    public RespBean deleteRole(Integer roleId){
    	if(roleService.judgeRoleExistUser(roleId) == 0) {
	    	if (roleService.deleteRole(roleId) > 0){
	    		return RespBean.ok("删除成功!");
	    	}
	    	return RespBean.error("删除失败!");
    	}
    	return RespBean.error("该角色下面存在用户，删除失败!");
    }
    @RequestMapping(value = "/insertrole", method = RequestMethod.POST)
    public RespBean insertRole(String roleName,long[] pers) throws IOException {
    	
    	int result = roleService.insertRole(roleName);
		int roleId = roleService.getRoleIdByRoleName(roleName);
    	if ( result > 0){
        	if (roleService.insertRolePermission(roleId,pers)> 0) {
        		return RespBean.ok("新增成功!");
        	}else {
	        	roleService.deleteRole(roleId);
	        	return RespBean.error("新增失败,请检查权限参数是否正确!");
        	}
    	}else if(result == -1) {
    		roleService.deleteRole(roleId);
    		return RespBean.error("该角色名称已存在，新增失败!");
    	}
    	
    		return RespBean.error("新增失败!");
    	
    }
	
	
	/*
	 * @RequestMapping(value = "/updaterolepermission", method = RequestMethod.POST)
	 * public RespBean updateRolePermission(@RequestBody String json) throws
	 * IOException{ JsonObject jsonObject = new
	 * JsonParser().parse(json).getAsJsonObject(); JsonArray jsonArray =
	 * jsonObject.getAsJsonArray("result"); // Gson gson = new Gson();
	 * ArrayList<JsonRolePermission> jsonRole1 = new ArrayList<>();
	 * 
	 * for (JsonElement role : jsonArray) { JsonRolePermission jsonRole2 = new
	 * Gson().fromJson(role, new TypeToken<JsonRolePermission>() {}.getType());
	 * jsonRole1.add(jsonRole2); } List<RolePermission> list = new
	 * ArrayList<RolePermission>(); RolePermission role = new RolePermission();
	 * 
	 * role.setRoleId(jsonRole1.get(0).getRoleId()); role.setPermissionId(1); //
	 * role.setState(jsonRole1.get(0).getPermissionName1()); list.add(role); Integer
	 * roleId = jsonRole1.get(0).getRoleId(); long[] permission =
	 * {1,2,3,4,5,6,7,8,9,10}; long[] states =
	 * {jsonRole1.get(0).getPermissionName1(),
	 * jsonRole1.get(0).getPermissionName2(), jsonRole1.get(0).getPermissionName3(),
	 * jsonRole1.get(0).getPermissionName4(), jsonRole1.get(0).getPermissionName5(),
	 * jsonRole1.get(0).getPermissionName6(), jsonRole1.get(0).getPermissionName7(),
	 * jsonRole1.get(0).getPermissionName8(), jsonRole1.get(0).getPermissionName9(),
	 * jsonRole1.get(0).getPermissionName10()}; // //
	 * System.err.println(permissions.length);
	 * 
	 * // roleService.updateRolePermission(roleId,states); //
	 * roleService.updateRolePermission(roleId, permission,states); return
	 * RespBean.ok("更新成功!");
	 * 
	 * }
	 */
	 
	 
	 
}
