package org.nlb.security;

import org.mybatis.spring.annotation.MapperScan;
import org.nlb.security.util.Doogle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("org.nlb.security.mapper")
@SpringBootApplication
public class yy {
    public static void main(String[] args) {
        SpringApplication.run(yy.class,args);

    }
}
