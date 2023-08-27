package com.psycho.backend.aop.aspect;


import com.psycho.backend.aop.api.Loggable;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@Slf4j
@Aspect
public class LoggingAspect {

    public Object handleAnnotationLoggableAdvice(ProceedingJoinPoint joinPoint, Loggable loggable) {
        Object returnValue;

        try (PrintWriter printWriter = new PrintWriter(new FileWriter("app.log", true))) {
            String argsString = Arrays.toString(joinPoint.getArgs());
            Signature signature = joinPoint.getSignature();
            returnValue = joinPoint.proceed();
            printWriter.printf("Method %s args %s return value %s %n", signature, argsString, returnValue);
        } catch (Throwable e) {
            log.info("Error while working with log file.");
            throw new RuntimeException(e);
        }

        return returnValue;
    }
}
