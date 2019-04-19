/**
 * 
 */
package com.ck.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.springboot.mapper.PermissionMapper;
import com.ck.springboot.pojo.Permission;
import com.ck.springboot.service.PermissionService;

/**  
* <p>Description: </p>  
* @author 胡高翔  
* @date 2019年3月7日  
*/
@Service
public class PermissionServicelpml implements PermissionService{

	@Autowired PermissionMapper permissionmapper;

	@Override
	public List<Permission> getPermission() {
		// TODO Auto-generated method stub
		return permissionmapper.getPermission();
	}

	@Override
	public List<Permission> getPermissionByRoleId(Integer roleid) {
		return permissionmapper.getPermissionByRoleId(roleid);
	}
}
