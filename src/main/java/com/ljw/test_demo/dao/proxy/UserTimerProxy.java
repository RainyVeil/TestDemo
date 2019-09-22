package com.ljw.test_demo.dao.proxy;

import com.ljw.test_demo.dao.UserDao;

public class UserTimerProxy implements UserDao{
    private UserDao userdao;

    public UserTimerProxy(UserDao userdao) {
        this.userdao = userdao;
    }

    public void query(String name) {
        System.out.println("timer Log。。。");
        userdao.query(name);
    }

    @Override
    public void query2(String name, String name2) {

    }
}
