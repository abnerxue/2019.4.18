package com.ck.springboot.service;

import java.io.IOException;
import java.util.List;

import com.ck.springboot.dto.UserDto;
import com.ck.springboot.pojo.User;
import com.ck.springboot.pojo.UserLogin;


public interface UserServeice {
	
	public User login(String name,String password);
	
	public List<User> getAllUser(String name,Integer deptId);
	
	public int insertUser(User user) throws IOException;
	
	public int updateUser(User user) throws IOException;
	
	public int deleteUser(User user);
	
	public UserDto getlogin(String name, String password);

}
