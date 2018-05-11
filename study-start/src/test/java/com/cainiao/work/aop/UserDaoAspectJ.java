package com.cainiao.work.aop;

import com.cainiao.work.ApplicationTest;
import com.cainiao.work.dao.UserDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wuchanming
 * @date 18/5/4
 */
public class UserDaoAspectJ extends ApplicationTest{
    @Autowired
    UserDao userDao;

    @Test
    public void aspectJTest(){
        userDao.addUser();
    }
}
