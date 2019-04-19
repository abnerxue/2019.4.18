package com.ck.springboot.exception;

import com.alibaba.fastjson.JSONObject;
import com.ck.springboot.constant.HttpResponseConstant;
import com.ck.springboot.utils.ResponseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = CommonException.class)
    public void defaultErrorHandler(HttpServletResponse response, Exception e) throws IOException {
        //封装错误信息
        ResponseResult responseResult = new ResponseResult();
        if (e instanceof CommonException){
            e = (CommonException) e;
            responseResult.setCode(((CommonException) e).getCode());
        }else {
            responseResult.setCode(HttpResponseConstant.SERVER_EXCEPTION);
        }

        responseResult.setMsg(e.getMessage());
        //跨域
        response.setHeader("Access-Control-Allow-Headers","content-type,x-requested-with");
        response.setHeader("Access-Control-Allow-Origin","*");
        //
        response.setStatus(200);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(JSONObject.toJSONString(responseResult));
        out.flush();
        out.close();
    }

}

