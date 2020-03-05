package com.carousell.marketplace.annotation;

import com.carousell.marketplace.error.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;

/**
 * @author faizanmalik
 * creation date 3/6/20
 */
@Slf4j
@Aspect
@Configuration
public class ExceptionInterceptorAspect {

    @Around("@annotation(com.carousell.marketplace.annotation.ExceptionHandler)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (ApplicationException ex) {
            println(ex.getMessage());
            return null;
        }
    }
}

