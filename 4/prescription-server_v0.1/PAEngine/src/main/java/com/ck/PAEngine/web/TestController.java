package com.ck.PAEngine.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lx
 * @date 2019/3/28
 * @desc
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }

}
