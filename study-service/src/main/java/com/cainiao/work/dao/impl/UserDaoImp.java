package com.cainiao.work.dao.impl;

import com.cainiao.work.dao.UserDao;
import org.springframework.stereotype.Component;

/**
 * @author wuchanming
 * @date 18/5/4
 */
@Component
public class UserDaoImp implements UserDao{
    @Override
    public int addUser() {
        System.out.println("add user ......");
        return 6666;
    }

    @Override
    public void updateUser() {
        System.out.println("update user ......");
    }

    @Override
    public void deleteUser() {
        System.out.println("delete user ......");
    }

    @Override
    public void findUser() {
        System.out.println("find user ......");
    }
}
