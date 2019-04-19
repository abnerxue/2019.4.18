package com.ck.prescriptionAudit.web;

import com.ck.springboot.dto.UserDto;
import com.ck.springboot.pojo.RespBean;
import com.ck.springboot.pojo.User;
import com.ck.springboot.service.UserServeice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

	@Autowired
    UserServeice userServeice;
	
	@RequestMapping(value = "/getlogin", method = RequestMethod.POST)
	public Map<String, Object> getlogin(String name,String password) throws Exception {
	   Map<String, Object> map = new LinkedHashMap<>();
	   UserDto user = userServeice.getlogin(name, password);
	   if (user == null){
		   map.put("code",10000);
		   map.put("msg","用户名或者密码错误");
		   map.put("result", user);
	   }else{
		   map.put("code",0);
		   map.put("msg","success");
		   map.put("result", user);
	   }
	   return map;
    }
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }
	
	@GetMapping(value="/getalluser")
	public Map<String, Object> getAllUser(String name,Integer deptId) throws Exception{
		Map<String, Object> map = new LinkedHashMap<>();
		List<User> user = userServeice.getAllUser(name,deptId);
		if (user.size()==0){
			   map.put("code",0);
			   map.put("msg","该科室下没有用户");
			   map.put("result", user);
		   }else if (user.size()>0){
			   map.put("code",0);
			   map.put("msg","success");
			   map.put("result", user);  
		   }else {
			   map.put("code",10000);
			   map.put("msg","获取用户失败");
		   }
		return map;

	}
	
    @RequestMapping(value = "/insertuser", method = RequestMethod.POST)
    public RespBean insertUser(User user) throws IOException{
    	
    	if(user.getDeptid()!=null && user.getRoleid()!=null && user.getName()!=null && user.getPassword()!=null) {
        if (userServeice.insertUser(user) == 1) {
            return RespBean.ok("添加成功!");
        }else if(userServeice.insertUser(user) == -1) {
        	 return RespBean.error("该用户名已存在，添加失败!");
        }
        return RespBean.error("添加失败!");
    	}
    	 return RespBean.error("添加失败,用户信息存在空值,请检查后再添加!");
    }
    
    @RequestMapping(value = "/updateuser", method = RequestMethod.POST)
    public RespBean updateUser(User user) throws IOException{
        if (userServeice.updateUser(user) == 1) {
            return RespBean.ok("更新成功!");
        }else if(userServeice.updateUser(user) == -1) {
        	return RespBean.error("该用户名已存在，更新失败!");
        }
        return RespBean.error("更新失败!");
    }
    
    @RequestMapping(value = "/deleteuser", method = RequestMethod.POST)
    public RespBean deleteUser(User user) {
        if (userServeice.deleteUser(user) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
