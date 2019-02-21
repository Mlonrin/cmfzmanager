package com.baizhi.cmfz.aspect;

import com.baizhi.cmfz.entity.AdminLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(com.baizhi.cmfz.annotation.ServiceLog)")
    public void testPoint(){}

    @Around("testPoint()")
    public Object testAdd(ProceedingJoinPoint proceedingJoinPoint) {
        AdminLog adminLog = new AdminLog();

        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        return null;

    }


}
