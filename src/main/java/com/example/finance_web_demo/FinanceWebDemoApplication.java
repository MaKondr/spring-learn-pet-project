package com.example.finance_web_demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FinanceWebDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceWebDemoApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

//    @Bean
//    public HiddenHttpMethodFilter corsConfigurer() {
//        HiddenHttpMethodFilter corsFilter = new HiddenHttpMethodFilter().;
//    }



}
