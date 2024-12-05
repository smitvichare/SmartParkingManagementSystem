//package com.smartparkingms.aspect;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Aspect
//@Component
//public class Logging {
//
//    @Before("execution(* com.smartparkingms.controller.*.*(..))")
//    public void logBeforeControllerMethods(JoinPoint joinPoint){
//        log.info("Entering method: {} with arguments:{}",joinPoint.getSignature(),joinPoint.getArgs());
//    }
//
//    @AfterReturning(pointcut ="execution(* com.smartparkingms.controller.*.*(..))", returning = "result")
//    public void logAfterControllerMethods(JoinPoint joinPoint, Object result){
//        log.info("Exiting method:{} with result:{}",joinPoint.getSignature(),result);
//    }
//
////    @AfterThrowing(pointcut = "execution(* com.smartparkingms.controller.*.*(..))", throwing = "exception")
////    public void logExceptions(JoinPoint joinPoint, Throwable exception){
////        log.error("Exception in method:{} with message:{}",joinPoint.getSignature(),exception.getMessage());
////    }
//
//    @Before("execution(* com.smartparkingms.service.*.*(..))")
//    public void logBeforeServiceMethods(JoinPoint joinPoint){
//        log.debug("Service method invoked:{} with arguments: {}",joinPoint.getSignature(),joinPoint.getArgs());
//    }
//
//    @AfterReturning(pointcut ="execution(* com.smartparkingms.service.*.*(..))",returning = "result")
//    public void  logAfterServiceMethods(JoinPoint joinPoint, Object result){
//        log.debug("Service method finished: {} with result: {}",
//                joinPoint.getSignature(),
//                result);
//    }
//}
