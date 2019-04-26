package com.huangsuip.api.config;

import com.huangsuip.framework.util.LogUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author HuangSuip
 */
@Component
@Aspect
public class AOPConfig {

    @Pointcut("execution(* com.huangsuip.service.user.*.*(..))")
    public void pointCut(){
        LogUtils.info("pointCut");
    }

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint){
/*        LogUtils.info("doBefore");
        LogUtils.info(joinPoint.getArgs());*/
    }

    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint){
/*        LogUtils.info("doAfter");
        LogUtils.info(joinPoint.getArgs());*/
    }

    @AfterReturning(pointcut="pointCut()",returning="returnVal")
    public void afterReturn(JoinPoint joinPoint,Object returnVal){
/*        LogUtils.info("afterReturn");
        LogUtils.info(joinPoint.getArgs());
        LogUtils.info(returnVal);*/
    }

    @AfterThrowing(pointcut="pointCut()",throwing="error")
    public void afterThrowing(JoinPoint joinPoint,Throwable error){
/*        try {
            LogUtils.info("afterThrowing");
            LogUtils.info(joinPoint.getArgs());
            System.out.println("AfterThrowing...");
        }catch (Exception e){
            LogUtils.info(error);
        }*/

    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        LogUtils.info("around start");
        LogUtils.info(pjp.getArgs());
        Object proceed = pjp.proceed();
        LogUtils.info(proceed);
        LogUtils.info("around end");
    }

}
