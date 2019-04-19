package com.ck.springboot.utils;

import lombok.Data;

/**
 * @author lx
 * @date ${Date}
 * @desc
 */
@Data
public class ResponseResult<T> {

    private int code = 0;

    private String msg = "success";

    private T result;

    private int total;

}
