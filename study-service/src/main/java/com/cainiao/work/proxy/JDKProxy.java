package com.cainiao.work.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wuchanming
 * @date 18/5/5
 */
public class JDKProxy implements InvocationHandler {
    /**
     * 要被代理的目标对象
     */
    private A target;

    public JDKProxy(A target){
        this.target=target;
    }

    /**
     * 创建代理类
     * @return
     */
    public ExInterface createProxy(){
        return (ExInterface) Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    /**
     * 调用被代理类(目标对象)的任意方法都会触发invoke方法
     * @param proxy 代理类
     * @param method 被代理类的方法
     * @param args 被代理类的方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //过滤不需要该业务的方法
        if("execute".equals(method.getName())) {
            //调用前验证权限
            AuthCheck.authCheck();
            //调用目标对象的方法
            Object result = method.invoke(target, args);
            //记录日志数据
            Report.recordLog();
            return result;
        }else if("delete".equals(method.getName())){
            //.....
        }
        //如果不需要增强直接执行原方法
        return method.invoke(target,args);
    }

    //测试验证
    public static void main(String args[]){
        A a=new A();
        //创建JDK代理
        JDKProxy jdkProxy=new JDKProxy(a);
        //创建代理对象
        ExInterface proxy=jdkProxy.createProxy();
        //执行代理对象方法
        proxy.execute();
    }
}
