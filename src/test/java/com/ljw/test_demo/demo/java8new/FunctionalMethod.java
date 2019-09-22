package com.ljw.test_demo.demo.java8new;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class FunctionalMethod {
    public static void show(FunctionalInterface_new1 myInter){
        myInter.method1();
    }
    public static String getString(Supplier<String> sup){
        return sup.get();
    }
    public static void talk(String s,Consumer<String> con){
        con.accept(s);
    }
    public static void printinfo(String[] arr,Consumer<String> con1,Consumer<String> con2){
       for(String s:arr){
           con1.andThen(con2).accept(s);
       }
    }


    public static void main(String[] args) {
        //方法参数是接口 可以传接口的实现类对象
        show(new FunctionalInterface_new1_Impl());
        //方法参数是接口 可与传接口的内部匿名类
        show(new FunctionalInterface_new1() {
            @Override
            public void method1() {
                System.out.println("使用内部匿名类重写抽象方法");
            }
        });
        //方法参数是函数式接口 可以传Lambda表达式
        show(()-> System.out.println("使用Lambda表达式重写接口的抽象方法"));


        System.out.println(getString(() -> new String("Supplier接口测试").substring(3)));



        talk("hello,Consumer测试",(ss)-> System.out.println(ss));

        String[] names = {"张三,男","李四,女","王五,男"};
        printinfo(names,(n)->{
            System.out.print("姓名：" + n.substring(0,n.indexOf(',')) + ", " );

        },(n)->{
            System.out.println("性别：" + n.substring(n.indexOf(',') + 1) + "; " );

        });

    }
}
