package com.ck.prescriptionAudit;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import java.text.ParseException;

@SpringBootApplication
@MapperScan("com.ck.springboot.mapper")
@Slf4j
@ServletComponentScan
@ComponentScan(value = {"com.ck.prescriptionAudit","com.ck.springboot"})
public class Application extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    
    public static void main(String[] args) throws ParseException {
        SpringApplication.run(Application.class, args);
        log.info("----------------------------程序启动----------------------------");
    }
 
}