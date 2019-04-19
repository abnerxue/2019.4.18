package com.ck.PAEngine;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import java.text.ParseException;

/**
 * @author lx
 * @date 2019/3/28
 * @desc
 */
@SpringBootApplication
@MapperScan("com.ck.springboot.mapper")
@Slf4j
@ServletComponentScan
@ComponentScan(value = {"com.ck.PAEngine","com.ck.springboot"})
public class PAEngineApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PAEngineApplication.class);
    }

    public static void main(String[] args) throws ParseException {
        SpringApplication.run(PAEngineApplication.class, args);
        log.info("----------------------------程序启动----------------------------");
    }

}
