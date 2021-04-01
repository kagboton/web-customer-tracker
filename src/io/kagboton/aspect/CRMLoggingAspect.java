package io.kagboton.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    // setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    // setup pointcut declaration
    @Pointcut("execution(* io.kagboton.springdemo.controller.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* io.kagboton.springdemo.dao.*.*(..))")
    private void forDaoPackage(){}

    @Pointcut("execution(* io.kagboton.springdemo.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
    private void forAppFlow(){}

    // add @Before advice
    @Before("forAppFlow()")
    private void before(JoinPoint joinPoint){

        // display the method we are calling
        String theMethod = joinPoint.getSignature().toShortString();
        myLogger.info("===> in @Before: calling method: " + theMethod);


        // display the arguments to the method
        Object[] args = joinPoint.getArgs();

        for(Object arg : args){
            myLogger.info("===> argument: " + arg.toString());
        }

    }


    // add @AfterReturning advice
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result"
    )
    public void afterReturning(JoinPoint joinPoint, Object result){
        // display method we are returning from
        String theMethod = joinPoint.getSignature().toShortString();
        myLogger.info("===> in @AfterReturning: from method: " + theMethod);

        // display the data returned
        myLogger.info("===> result: " + result);
    }
}
