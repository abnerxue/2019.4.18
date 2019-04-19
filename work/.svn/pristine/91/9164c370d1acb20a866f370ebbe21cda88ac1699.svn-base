/**
 * 
 */
package com.ck.springboot.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ck.springboot.mapper.RoleMapper;
import com.ck.springboot.pojo.Permission;
import com.ck.springboot.pojo.Role;
import com.ck.springboot.pojo.RolePermission;
import com.ck.springboot.service.RoleService;

import lombok.extern.slf4j.Slf4j;

/**  
* <p>Description: </p>  
* @author 胡高翔  
* @date 2019年3月7日  
*/
@Service
@Slf4j
public class RoleServicelpml implements RoleService {

	@Autowired RoleMapper rolemapper;

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.RoleService#getRole()
	 */
	@Override
	public List<Role> getRole(Integer roleId) {
		// TODO Auto-generated method stub
		return rolemapper.getRole(roleId);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.RoleService#getRolePermission(java.lang.Integer)
	 */
	@Override
	public List<RolePermission> getRolePermission(Integer roleId) {
		// TODO Auto-generated method stub
		return rolemapper.getRolePermission(roleId);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.RoleService#updateRole(com.ck.springboot.pojo.Role)
	 */
	@Override
	public int updateRoleName(int roleId,String roleName) {
		// TODO Auto-generated method stub
		return rolemapper.updateRoleName(roleId,roleName);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.RoleService#deleteRole(java.lang.Integer)
	 */
	@Override
	public int deleteRole(Integer roleId) {
		// TODO Auto-generated method stub
		return rolemapper.deleteRole(roleId) + rolemapper.deleteRolePermission(roleId);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.RoleService#insertRole(java.lang.String, int[])
	 */
	@Override
	public int insertRole(String roleName) throws IOException{
		// TODO Auto-generated method stub
		try {
			return rolemapper.insertRole(roleName);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("exception toString and track space : {}", "\r\n" + e);
			log.error("---------------------------------------------");
			e.printStackTrace();
			return -1;
		}

	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.RoleService#insertRolePermission(java.util.List)
	 */
	@Override
	public int insertRolePermission(Integer roleId,long[] pers) throws IOException {
		// TODO Auto-generated method stub
		try {
			return rolemapper.insertRolePermission(roleId,pers);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("exception toString and track space : {}", "\r\n" + e);
			log.error("---------------------------------------------");
			e.printStackTrace();
			if(pers.length==0) {
				return 0;
			}
				return -1;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.RoleService#judgeRolePermission(int, int)
	 */
	@Override
	public int judgeRolePermission(int roleId, int permissionId) {
		// TODO Auto-generated method stub
		return rolemapper.judgeRolePermission(roleId, permissionId);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.RoleService#getPermission()
	 */
	@Override
	public List<Permission> getPermission() {
		// TODO Auto-generated method stub
		return rolemapper.getPermission();
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.RoleService#getRoleIdByRoleName(java.lang.String)
	 */
	@Override
	public int getRoleIdByRoleName(String roleName) {
		// TODO Auto-generated method stub
		return rolemapper.getRoleIdByRoleName(roleName);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.RoleService#deleteRolePermission(java.lang.Integer)
	 */
	@Override
	public int deleteRolePermission(Integer roleId) {
		// TODO Auto-generated method stub
		return rolemapper.deleteRolePermission(roleId);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.RoleService#judgeRoleExistUser(java.lang.Integer)
	 */
	@Override
	public int judgeRoleExistUser(Integer roleId) {
		// TODO Auto-generated method stub
		return rolemapper.judgeRoleExistUser(roleId);
	}

}
