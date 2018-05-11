package com.cainiao.work.hsf.consumer;

import org.springframework.context.annotation.Configuration;

import com.alibaba.boot.hsf.annotation.HSFConsumer;
import com.cainiao.work.hsf.HelloService;

/**
 * hsf服务的统一个Config类，在其它需要使用的地方，直接@Autowired注入即可。详情见
 * http://gitlab.alibaba-inc.com/middleware-container/pandora-boot/wikis/spring-boot-hsf
 *
 * @author chengxu
 */
@Configuration
public class HsfConfig {

    @HSFConsumer
    private HelloService helloService;
}
