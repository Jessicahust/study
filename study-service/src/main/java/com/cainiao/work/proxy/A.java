package com.cainiao.work.proxy;

/**
 * @author wuchanming
 * @date 18/5/5
 */
public class A implements ExInterface {
    @Override
    public void execute(){
        System.out.println("执行A的execute方法...");
    }
}
