/**
 * 
 */
package com.ck.springboot.service;

import com.ck.springboot.pojo.Permission;

import java.util.List;

/**  
* <p>Description: </p>  
* @author 胡高翔  
* @date 2019年3月7日  
*/
public interface PermissionService {
	
	public List<Permission> getPermission();//获取权限列表

    List<Permission> getPermissionByRoleId(Integer roleid);
}
