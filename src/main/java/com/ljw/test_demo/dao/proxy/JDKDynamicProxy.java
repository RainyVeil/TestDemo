package com.ljw.test_demo.dao.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKDynamicProxy implements InvocationHandler{
    private Object target;

    public JDKDynamicProxy(Object target) {
        this.target = target;
    }

    public <T> T  getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理逻辑前。。。");
        try {
            return method.invoke(target,args);
        }finally {
            System.out.println("代理逻辑后。。。");
        }
    }
}
