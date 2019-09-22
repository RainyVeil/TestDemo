package com.ljw.test_demo.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OtherTest {
    public static void main(String[] args) {
        List names = new ArrayList();

        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");
        names.forEach(name-> System.out.println(name));


        // 创建一个Map
        Map<String, Object> infoMap = new HashMap<>();
        infoMap.put("name", "Zebe");
        infoMap.put("site", "www.zebe.me");
        infoMap.put("email", "zebe@vip.qq.com");
        // 传统的Map迭代方式
        for (Map.Entry<String, Object> entry : infoMap.entrySet()) {
            System.out.println(entry.getKey() + "：" + entry.getValue());
        }
        // JDK8的迭代方式
        infoMap.forEach((key, value) -> {
            System.out.println(key + "：" + value);});
    }

}
