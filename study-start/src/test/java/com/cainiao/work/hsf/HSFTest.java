package com.cainiao.work.hsf;

import com.cainiao.work.ApplicationTest;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 将HSFConfig中配置的hsf服务注入并测试
 * 
 * @author chengxu
 */
public class HSFTest extends ApplicationTest{

    @Autowired
    @Qualifier("helloService")
    private HelloService service;

    @Test
    public void testInvoke() {
        TestCase.assertEquals("Hello, pandora boot", service.sayHello("pandora boot"));
    }

}