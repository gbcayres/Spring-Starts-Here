package org.example.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.models.Comment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());
    @Around("execution(* org.example.services.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();

        logger.info("Method " + methodName + " with parameters: " + Arrays.asList(arguments) + " will execute");

        var newComment = new Comment("Ryan", "Faz o L.");
        Object[] newArguments = {newComment};
        Object returnedByMethod = joinPoint.proceed(newArguments);

        logger.info("Method executed and returned: " + returnedByMethod);

        return "FAILED";
    }
}
