/**
 * 
 */
package com.ck.springboot.service;

import com.ck.springboot.pojo.Permission;
import com.ck.springboot.pojo.Role;
import com.ck.springboot.pojo.RolePermission;

import java.io.IOException;
import java.util.List;


/**  
* <p>Description: </p>  
* @author 胡高翔  
* @date 2019年3月7日  
*/
public interface RoleService {
	
	public List<Role> getRole(Integer roleId);
	
	public List<RolePermission> getRolePermission(Integer roleId);
	
	public List<Permission> getPermission();//获取所有权限列表
	
	public int judgeRolePermission(int roleId, int permissionId);//判断该用户是否有指定权限

	public int updateRoleName(int roleId, String roleName);//更新角色权限信息

//	public int updateRolePermission(Integer roleId,long[] permission,long[] states);//更新角色权限

	public int deleteRole(Integer roleId);//删除角色信息

	public int judgeRoleExistUser(Integer roleId);//判断该角色下面时候有用户

	public int deleteRolePermission(Integer roleId);//删除角色权限

	public int insertRole(String roleName) throws IOException;//新增角色

	public int getRoleIdByRoleName(String roleName);//通过角色名称获取角色ID

	public int insertRolePermission(Integer roleId, long[] pers) throws IOException;//新增角色权限
	
}
