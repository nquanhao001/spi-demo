package com.niugege;

import com.niugege.spi.spring.scan.SpiFunctionPointScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
@SpiFunctionPointScan
public class AppMain{
    public static void main(String[] args) {
        System.setProperty("app.name", "spi-demo");
        SpringApplication.run(AppMain.class, args);
    }
}
