/**
 * 
 */
package com.ck.springboot.service;

import java.util.List;

import com.ck.springboot.pojo.Permission;

/**  
* <p>Description: </p>  
* @author 胡高翔  
* @date 2019年3月7日  
*/
public interface PermissionService {
	
	public List<Permission> getPermission();//获取权限列表

    List<Permission> getPermissionByRoleId(Integer roleid);
}
