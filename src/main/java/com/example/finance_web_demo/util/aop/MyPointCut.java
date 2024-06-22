package com.example.finance_web_demo.util.aop;

import org.aspectj.lang.annotation.Pointcut;

public class MyPointCut {
    @Pointcut("execution(* com.example.finance_web_demo.services.*.* (..))")
    public void servicePointCut() {}

    @Pointcut("execution(* com.example.finance_web_demo.controllers.*.* (..))")
    public void controllerPointCut() {}
}
