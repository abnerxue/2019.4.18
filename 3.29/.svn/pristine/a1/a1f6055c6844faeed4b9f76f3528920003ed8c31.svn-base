package com.ck.springboot.mapper;

import com.ck.springboot.pojo.Permission;
import com.ck.springboot.pojo.Role;
import com.ck.springboot.pojo.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    public List<Role> getRole(@Param("roleId") Integer roleId);//获取所有角色列表
    
	public List<Permission> getPermission();//获取所有权限列表
	
    public List<RolePermission> getRolePermission(Integer roleId);//通过角色Id获取权限列表
    
    public int updateRoleName(@Param("roleId") int roleId, @Param("roleName") String roleName);//更新角色权限信息

//    public int updateRolePermission(Integer roleId,long[] states);//更新角色权限

    public int deleteRolePermission(Integer roleId);

//    public int addRolePermission(@Param("roleId") Integer roleId,
//    		@Param("permission") long[] permission,
//    		@Param("states") long[] states);
	public int judgeRoleExistUser(Integer roleId);//判断该角色下面时候有用户
    public int deleteRole(Integer roleId);//删除角色信息

	public int insertRole(@Param("roleName") String roleName);//新增角色
	public int getRoleIdByRoleName(@Param("roleName") String roleName);//通过角色名称获取角色ID

	public int insertRolePermission(@Param("roleId") int roleId, @Param("pers") long[] pers);//新增角色权限

	public int judgeRolePermission(@Param("roleId") int roleId, @Param("permissionId") int permissionId);//判断该用户是否有指定权限
}