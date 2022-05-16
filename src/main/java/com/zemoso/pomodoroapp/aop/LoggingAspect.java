package com.zemoso.pomodoroapp.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    //pointcut expressions for user controller
    @Pointcut("execution(* com.zemoso.pomodoroapp.controller.*.*(..))")
    private void forControllerPkg(){
        log.info(">>>> PRIVATE POINTCUT METHOD: forControllerPkg() -> to trigger all methods in controller pkg");
    }

    @Pointcut("execution(* com.zemoso.pomodoroapp.dao.*.*(..))")
    private void forDaoPkg(){
        log.info(">>>> PRIVATE POINTCUT METHOD: forDaoPkg() -> to trigger all methods in dao pkg");
    }

    @Pointcut("execution(* com.zemoso.pomodoroapp.service.*.*(..))")
    private void forServicePkg(){
        log.info(">>>>> PRIVATE forServicePkg() -> to trigger all methods in service pkg");
    }

    @Pointcut("execution(* com.zemoso.pomodoroapp.security.*.*(..))")
    private void forSecurityPkg(){
        log.info(">>>>> PRIVATE forSecurityPkg() -> to trigger all methods in security pkg");
    }

    @Pointcut("forControllerPkg() || forDaoPkg() || forServicePkg() || forSecurityPkg() ")
    private void forAppFlow(){
        log.info(">>>>> PRIVATE forAppFlow() -> to trigger all the methods in all mentioned pkgs");
    }

    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint){
        String method = theJoinPoint.getSignature().toShortString();
        log.info("====> in @Before: calling method " + method);
    }
}
