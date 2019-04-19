package com.ck.springboot.mapper;

import com.ck.springboot.pojo.User;
import com.ck.springboot.pojo.UserLogin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

	public User login(String name, String password);

	public List<User> getAllUser(@Param("name") String name,
                                 @Param("deptId") Integer deptId);

	public int insertUser(User user);

	public int updateUser(User user);

	public int deleteUser(User user);

	public List<UserLogin> getlogin(@Param("name") String name, @Param("password") String password);
	


}

