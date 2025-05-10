package org.example.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Order(1)
@Component
public class LoggingAspect {

    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());
    @Around("@annotation(org.example.annotations.ToLog)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Logging Aspect: Calling the intercepted method.");

        Object returnedByMethod = joinPoint.proceed();

        logger.info("Logging Aspect: Method executed and returned: " + returnedByMethod + "\n");

        return returnedByMethod;
    }
}
