package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages="com")
public class RunStart {

    /**
     * 加载动态库
     */
    static {
//        System.loadLibrary("IKAlgo");
    }
    public static void main(String[] args) {
        SpringApplication.run(RunStart.class, args);
    }
}