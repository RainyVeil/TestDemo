package com.ljw.test_demo.dao;

public class UserDaoImpl implements UserDao{
    public void query(String name){
        System.out.println("静态代理测试name1："+name);

    }
    public void query2(String name,String name2){
        System.out.println("静态代理测试name2："+name);

    }
}
