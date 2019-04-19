package com.ck.springboot.interceptor;

import com.ck.springboot.constant.RedisConstant;
import com.ck.springboot.dto.UserDto;
import com.ck.springboot.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author lx
 * @date 2019/3/20
 * @desc
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Value("#{'${interceptor.not.match.urls}'.split(',')}")
    private List<String> noMatches;

    @Resource
    private RedisService redisService;


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //不需要连接的url直接通过
        if (noMatches.contains(httpServletRequest.getRequestURI())){
            return true;
        }
        //验证token
        String token = RedisConstant.USER_INFO_PREFIX + httpServletRequest.getHeader("token");
        if (!redisService.exists(token)){
            httpServletRequest.getRequestDispatcher("/index/noToken").forward(httpServletRequest, httpServletResponse);
            return true;
        }
        UserDto userDto = (UserDto)redisService.get(token);
        redisService.set(token,userDto,60);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
