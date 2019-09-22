package com.ljw.test_demo;

import com.ljw.test_demo.dao.UserDao;
import com.ljw.test_demo.dao.UserDaoImpl;
import com.ljw.test_demo.dao.proxy.UserLogProxy;
import com.ljw.test_demo.dao.proxy.UserTimerProxy;

import java.util.Optional;

public class Test {
    public static void main(String[] args) {
       /* UserDaoImpl usedao = new UserLogProxy();
        usedao.query("ddd");*/

    UserDao userdao = new UserDaoImpl();
    UserDao proxy = new UserLogProxy(userdao);
    UserDao proxy2 = new UserTimerProxy(proxy);
    proxy2.query("dddc");


    }
}
