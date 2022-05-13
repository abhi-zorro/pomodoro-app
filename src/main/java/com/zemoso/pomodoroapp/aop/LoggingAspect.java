package com.zemoso.pomodoroapp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    //setup logger
    private Logger logger = Logger.getLogger(getClass().getName());

    //pointcut expressions for user controller
    @Pointcut("execution(* com.zemoso.pomodoroapp.controller.*.*(..))")
    private void forControllerPkg(){
    }

    @Pointcut("execution(* com.zemoso.pomodoroapp.dao.*.*(..))")
    private void forDaoPkg(){

    }

    @Pointcut("execution(* com.zemoso.pomodoroapp.service.*.*(..))")
    private void forServicePkg(){

    }

    @Pointcut("execution(* com.zemoso.pomodoroapp.security.*.*(..))")
    private void forSecurityPkg(){

    }

    @Pointcut("forControllerPkg() || forDaoPkg() || forServicePkg() || forSecurityPkg() ")
    private void forAppFlow(){

    }

    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint){
        String method = theJoinPoint.getSignature().toShortString();
        logger.info("====> in @Before: calling method " + method);
    }
}
