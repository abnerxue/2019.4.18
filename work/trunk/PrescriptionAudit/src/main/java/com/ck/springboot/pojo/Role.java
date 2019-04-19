package com.ck.springboot.pojo;

import java.util.List;

import lombok.Data;

@Data
public class Role {
    private Integer roleId;

    private String roleName;
    
//    private List<RolePermission> rolepermission;
}