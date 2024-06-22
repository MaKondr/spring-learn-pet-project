package com.example.finance_web_demo.util.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;

@Component
@Aspect
public class Logger {

    @After("com.example.finance_web_demo.util.aop.MyPointCut.servicePointCut()")
    public void logServiceAfter(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("------------------------------");
        System.out.println(Instant.now() + " : " +
                "LogServiceAfterMethod: " +
                methodSignature.toLongString() + " | " +
                Arrays.toString(joinPoint.getArgs())
        );
        System.out.println("------------------------------");
    }

    @After("com.example.finance_web_demo.util.aop.MyPointCut.controllerPointCut()")
    public void logControllerAfter(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("------------------------------");
        System.out.println(Instant.now() + " : " +
                "LogControllerAfterMethod: " +
                methodSignature.toLongString() + " | " +
                Arrays.toString(joinPoint.getArgs())
        );
        System.out.println("------------------------------");
    }

}
