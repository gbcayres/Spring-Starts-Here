package org.example.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Order(2)
@Component
public class SecurityAspect {
    private Logger logger = Logger.getLogger(SecurityAspect.class.getName());

    @Around("@annotation(org.example.annotations.ToSecure)")
    public Object secure(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Security Aspect: Calling the intercepted method");

        Object returnedByMethod = joinPoint.proceed();

        logger.info("Security Aspect: Method executed and returned: " + returnedByMethod);

        return returnedByMethod;
    }
}
