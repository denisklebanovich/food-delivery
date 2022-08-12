package com.epam.training.fooddelivery.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    public static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
    public static final String METHOD_NAME = "Method name: ";


    @Before("@annotation(com.epam.training.fooddelivery.annotation.EnableArgumentLogging)")
    public void logArguments(JoinPoint joinPoint){
        String info = METHOD_NAME + joinPoint.getSignature().getName() + ", parameter(s): " + Arrays.toString(joinPoint.getArgs());
        LOGGER.info(info);
    }

    @AfterReturning(value = "@annotation(com.epam.training.fooddelivery.annotation.EnableArgumentLogging)",returning = "returnValue")
    public void logReturnValue(JoinPoint joinPoint,Object returnValue){
        String info = METHOD_NAME + joinPoint.getSignature().getName() + ", return value: " + returnValue;
        LOGGER.info(info);
    }

    @Around("@annotation(com.epam.training.fooddelivery.annotation.EnableExecutionTimeLogging)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endtime = System.currentTimeMillis();
        String info = METHOD_NAME + joinPoint.getSignature().getName() + ", return value: " + (endtime - startTime) + "ms";
        LOGGER.info(info);
        return result;
    }


}
