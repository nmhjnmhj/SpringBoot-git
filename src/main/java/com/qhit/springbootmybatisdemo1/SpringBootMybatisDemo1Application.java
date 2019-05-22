package com.qhit.springbootmybatisdemo1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.qhit.springbootmybatisdemo1")
@MapperScan("com.qhit.springbootmybatisdemo1.dao")
public class SpringBootMybatisDemo1Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisDemo1Application.class, args);
    }

}
