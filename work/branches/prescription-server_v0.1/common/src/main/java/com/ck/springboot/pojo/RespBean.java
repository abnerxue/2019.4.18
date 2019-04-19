package com.ck.springboot.pojo;

import lombok.Data;

@Data
public class RespBean {
    private Integer code;
    private String msg;
    private Object obj;

    private RespBean() {
    }

    public static RespBean build() {
        return new RespBean();
    }

    public static RespBean ok(String msg, Object obj) {
        return new RespBean(0, msg, obj);
    }

    public static RespBean ok(String msg) {
        return new RespBean(0, msg, null);
    }

    public static RespBean error(String msg, Object obj) {
        return new RespBean(10000, msg, obj);
    }

    public static RespBean error(String msg) {
        return new RespBean(10000, msg, null);
    }

    private RespBean(Integer code, String msg, Object obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

}
