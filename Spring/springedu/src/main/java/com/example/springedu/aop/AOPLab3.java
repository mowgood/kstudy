package com.example.springedu.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
@Slf4j
public class AOPLab3 {
    @Around("within(com.example.springedu.controller.EmpController)")
    public Object around(ProceedingJoinPoint pjp) {
        StopWatch sw = new StopWatch();
        Object result=null;
        try {
            sw.start();
            result = pjp.proceed();
            sw.stop();
            log.info(pjp.getTarget().getClass().getName() + " - 수행시간(밀리초) - " + sw.getTotalTimeMillis());
        } catch(Throwable e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
