package com.codigoartesanal.entuliga.infrastructure;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by betuzo on 12/05/15.
 */
@Aspect
@Component
public class ExceptionAop {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAop.class);

    @Pointcut("execution(* com.codigoartesanal.entuliga.repositories.*Repository.*(..))")
    public void repositoryLayer() {
    }

    @Pointcut("execution(* com.codigoartesanal.entuliga.services.*Service.*(..))")
    public void servicesLayer() {
    }

    @Pointcut("execution(* com.codigoartesanal.entuliga.controller.*Controller.*(..))")
    public void controllerLayer() {
    }

    @AfterThrowing(pointcut = "controllerLayer()", throwing= "error")
    public void throwingControllerMethod(JoinPoint joinPoint, Throwable error) throws Throwable{
        logger.info("Controller " + joinPoint.getTarget().getClass()
                + " Method " + joinPoint.getSignature().getName()
                + " error in " + error.getLocalizedMessage());
    }

    @AfterThrowing(pointcut = "servicesLayer()", throwing= "error")
    public void throwingServiceMethod(JoinPoint joinPoint, Throwable error) throws Throwable{
        logger.info("Services " + joinPoint.getTarget().getClass()
                + " Method " + joinPoint.getSignature().getName()
                + " error in " + error.getLocalizedMessage());
    }

    @AfterThrowing(pointcut = "repositoryLayer()", throwing= "error")
    public void throwingDAOMethod(JoinPoint joinPoint, Throwable error) throws Throwable{
        logger.info("Repository " + joinPoint.getTarget().getClass()
                + " Method " + joinPoint.getSignature().getName()
                + " error in " + error.getLocalizedMessage());
    }
}
