package com.ck.springboot.mapper;

import java.util.List;

import com.ck.springboot.pojo.Permission;
import org.apache.ibatis.annotations.Param;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
    public List<Permission> getPermission();//获取权限列表

    List<Permission> getPermissionByRoleId(@Param("roleid") Integer roleid);
}