package com.imooc.security.imoocsecuritydemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan("com.imooc")
@MapperScan("com.imooc")
@SpringBootApplication
public class ImoocSecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImoocSecurityDemoApplication.class, args);
    }

}

