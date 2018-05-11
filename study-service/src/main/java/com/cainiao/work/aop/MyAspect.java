package com.cainiao.work.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author wuchanming
 * @date 18/5/4
 */
@Component
@Aspect
public class MyAspect {
    /**
     * 前置通知
     */
    @Before("execution(* com.cainiao.work.dao.UserDao.addUser(..))")
    public void before(){
        System.out.println("前置通知....");
    }

    @Before("execution(public * com.cainiao.work..*.addUser(..)) && args(userId,..)")
    public void before(int userId) {
        //调用addUser的方法时如果与addUser的参数匹配则会传递进来会传递进来
        System.out.println("userId:" + userId);
    }

    /**
     * 后置通知
     * returnVal,切点方法执行后的返回值
     */
    @AfterReturning(value="execution(* com.cainiao.work.dao.UserDao.addUser(..))",returning = "returnVal")
    public void AfterReturning(Object returnVal){
        System.out.println("后置通知...."+returnVal);
    }


    /**
     * 环绕通知
     * @param joinPoint 可用于执行切点的类
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.cainiao.work.dao.UserDao.addUser(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕通知前...."+joinPoint);
        Object obj= (Object) joinPoint.proceed();
        System.out.println("环绕通知后....");
        return obj;
    }

    /**
     * 抛出通知
     * @param e
     */
    @AfterThrowing(value="execution(* com.cainiao.work.dao.UserDao.addUser(..))",throwing = "e")
    public void afterThrowable(Throwable e){
        System.out.println("出现异常:msg="+e.getMessage());
    }

    /**
     * 无论什么情况下都会执行的方法
     */
    @After(value="execution(* com.cainiao.work.dao.UserDao.addUser(..))")
    public void after(){
        System.out.println("最终通知....");
    }

    /**
     * 使用Pointcut定义切点
     */
    @Pointcut("execution(* com.cainiao.work.dao.UserDao.addUser(..))")
    private void myPointcut(){}

    /**
     * 应用切入点函数
     */
    @After(value="myPointcut()")
    public void afterDemo(){
        System.out.println("最终通知....");
    }
}
