package com.ck.springboot.dto;

import com.ck.springboot.pojo.Permission;
import com.ck.springboot.pojo.UserLogin;
import lombok.Data;

import java.util.List;

/**
 * @author lx
 * @date 2019/3/20
 * @desc
 */
@Data
public class UserDto extends UserLogin {
    /**
     *
     */
    private String token;
    /**
     * 过期时间
     */
    private String expire;
    /**
     * 菜单列表
     */
    private List<Permission> permissionList;
}
