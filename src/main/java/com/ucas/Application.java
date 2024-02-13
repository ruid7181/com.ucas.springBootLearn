package com.ucas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // 运行此main方法就会启动内置的tomcat.
        SpringApplication.run(Application.class, args);
    }
}
