package com.ck.springboot.exception;

import lombok.Data;

/**
 * @author lx
 * @date 2019/3/21
 * @desc
 */
@Data
public class CommonException extends Exception {

    private int code;

    public CommonException(int code,String message) {
        super(message);
        this.code = code;
    }
}
