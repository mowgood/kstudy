package com.example.springedu.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AOPLab1 {

    @Pointcut("execution(public String com.example.springedu.controller.HelloController.hello(*))")
    public void helloPointcut() {
    }

    @Before("helloPointcut()")
    public void beforeHelloPointcut(JoinPoint joinPoint) {
        System.out.println("[AOP]" + joinPoint.getSignature().getName() + " 수행전");
    }

    @After("helloPointcut()")
    public void afterHelloPointcut(JoinPoint joinPoint) {
        System.out.println("[AOP]" + joinPoint.getSignature().getName() + " 수행후");
    }

}
