package com.ljw.test_demo;

import com.ljw.test_demo.dao.UserDao;
import com.ljw.test_demo.dao.UserDaoImpl;
import com.ljw.test_demo.dao.proxy.JDKDynamicProxy;
import com.ljw.test_demo.dao.proxy.UserLogProxy;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;

public class ProxyTest {
    public static void main(String[] args) {
       /* final UserDaoImpl userdaoimpl = new UserDaoImpl();
        UserDao userservice = (UserDao) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(),new Class[]{UserDao.class},(proxy, method, args1)->{
            System.out.println("代理前置log...");
            try {
                return method.invoke(userdaoimpl,args1);
            }finally {
                System.out.println("代理后置log...");
            }
        });+
        userservice.query("夏尔");*/
        final UserDaoImpl userdaoimpl = new UserDaoImpl();
        UserDao userservice = new JDKDynamicProxy(userdaoimpl).getProxy();


        userservice.query("卡莲");





                }
/*    public static void createProxyClass() {
        byte[] bytes = ProxyGenerator.generateProxyClass("UserDao$Proxy", new Class[]{UserDao.class});
        File file = new File("E:\\ideaproject\\test_demo\\target\\classes\\com\\ljw\\test_demo\\dao\\UserDao$Proxy.java");
        try {
            Files.write(file.toPath(),bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}




