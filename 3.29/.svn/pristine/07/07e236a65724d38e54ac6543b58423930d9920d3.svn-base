package com.ck.springboot.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.ck.springboot.constant.RedisConstant;
import com.ck.springboot.dto.UserDto;
import com.ck.springboot.pojo.Permission;
import com.ck.springboot.service.PermissionService;
import com.ck.springboot.service.RedisService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ck.springboot.mapper.UserMapper;
import com.ck.springboot.pojo.User;
import com.ck.springboot.pojo.UserLogin;
import com.ck.springboot.service.UserServeice;
import com.ck.springboot.utils.TaleUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserServiceImpl implements UserServeice {

	@Resource
	private UserMapper usermapper;
	@Resource
	private RedisService redisService;
	@Resource
	private PermissionService permissionService;


	@Override
	public User login(String name,String password) {
		// TODO Auto-generated method stub
		return usermapper.login(name, password);
	}

	@Override
	public List<User> getAllUser(String name,Integer deptId) {
		// TODO Auto-generated method stub
		return usermapper.getAllUser(name,deptId);
	}

	@Override
	public int insertUser(User user) throws IOException{
		// TODO Auto-generated method stub
		try {
			String encodePwd = TaleUtils.MD5encode(user.getName() + user.getPassword());
			user.setPassword(encodePwd);
			return usermapper.insertUser(user);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("exception toString and track space : {}", "\r\n" + e);
			log.error("---------------------------------------------");
			e.printStackTrace();
			return -1;
		}
		
	}

	@Override
	public int updateUser(User user)  throws IOException{
		// TODO Auto-generated method stub
		try {
			String encodePwd = TaleUtils.MD5encode(user.getName() + user.getPassword());
			user.setPassword(encodePwd);
			return usermapper.updateUser(user);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("exception toString and track space : {}", "\r\n" + e);
			log.error("---------------------------------------------");
			e.printStackTrace();
			return -1;
		}

	}

	@Override
	public int deleteUser(User user) {
		// TODO Auto-generated method stub
		return usermapper.deleteUser(user);
	}

	@Override
	public UserDto getlogin(String name, String password) {
		// TODO Auto-generated method stub
		String pwd = TaleUtils.MD5encode(name + password);
		List<UserLogin> userList = usermapper.getlogin(name, pwd);
		if (CollectionUtils.isEmpty(userList)){
			return null;
		}
		//获得菜单权限
		List<Permission> permissionList = permissionService.getPermissionByRoleId(userList.get(0).getRoleid());
		//生成token放入缓存中，过期时间为1小时
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userList.get(0),userDto);
		userDto.setPermissionList(permissionList);
		String token ;
		// 根据ID获取token，key为ID，值为token
		if (redisService.exists(RedisConstant.USER_TOKEN_PREFIX + userDto.getId())){
			token = redisService.get(RedisConstant.USER_TOKEN_PREFIX + userDto.getId()).toString();
		}else {
			token = UUID.randomUUID().toString().replace("-", "");
		}
		redisService.set(RedisConstant.USER_TOKEN_PREFIX + userDto.getId(),token,60);
		//设置用户信息存放 key为token，value为user
		redisService.set(RedisConstant.USER_INFO_PREFIX + token,userDto,60);
		userDto.setToken(token);
		userDto.setExpire("60");
		return userDto;
	}

}
