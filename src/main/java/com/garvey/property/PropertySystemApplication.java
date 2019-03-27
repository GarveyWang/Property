package com.garvey.property;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(annotationClass = Mapper.class, basePackages = "com.garvey.property.dao")
public class PropertySystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PropertySystemApplication.class, args);
    }

}
