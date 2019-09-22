package com.ljw.test_demo.utils;


import com.ljw.test_demo.dao.UserDao;
import com.ljw.test_demo.dao.UserDaoImpl;
import com.ljw.test_demo.dao.proxy.UserLogProxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Optional;

public class ProxyUtil {
    /*
    * 1.得到一个代理对象的java文件
    * 2.编译成为class文件
    * 3.classloader加载磁盘上的java文件
    * 4.通过反射拿到代理对象
    * 5.返回代理对象
    *
    * */
    public static Object newProxyInstance(Object target){
        Object proxy = null;
        String content = "";
        Class targetInfo = target.getClass().getInterfaces()[0];
        String targetInfoname = targetInfo.getSimpleName();
        String packerContent = "package com.ljw.test_demo.dao;" ;
        String importContent = "import "+targetInfo.getName()+";" ;
        String Content1 = "public class $Proxy implements "+ targetInfoname+"{";
        String Content2 = "private "+targetInfoname+" target;";
        String Content3 = "public $Proxy("+targetInfoname+" target){"+
                          "this.target = target;"+
                          "}";


        Method[] methods = targetInfo.getDeclaredMethods();


        String methodsContent ="";
        for(Method method : methods){
            String retuenTypeName = method.getReturnType().getSimpleName();
            String methodName = method.getName();
            String paramNames ="";
            Class[] parameterTypes = method.getParameterTypes();
            String argsContent = "";
            int i = 0;
            for (Class parameterType:parameterTypes
                 ) {
                String simpleName = parameterType.getSimpleName();
                argsContent += simpleName+" p"+i+",";
                paramNames += "p"+i+",";
                i++;
            }
            if (argsContent.length()>0) {
                 argsContent = argsContent.substring(0,argsContent.lastIndexOf(",") - 1);
                 paramNames = paramNames.substring(0,paramNames.lastIndexOf(",") - 1);
            }
            methodsContent+= "public "+retuenTypeName+" "+methodName+"("+argsContent+"){"+
                        "System.out.println(\"log proxy...\");"+
                       "target."+methodName+"("+paramNames+");"+
                        "}";
        }
        content = packerContent+importContent+Content1+Content2+Content3 + methodsContent+"}";

        File file = new File("E:\\ideaproject\\test_demo\\src\\main\\java\\com\\ljw\\test_demo\\dao\\$Proxy.java");
        try {
            if (!file.exists()) {
                    file.createNewFile();
            }else {
                    file.delete();
                    file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null,null,null);
        Iterable units = fileManager.getJavaFileObjects(file);
        JavaCompiler.CompilationTask t = compiler.getTask(null,fileManager,null,null,null,units);
        t.call();

            fileManager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            URL[] urls = new URL[]{new URL("file:e:\\\\ideaproject\\\\test_demo\\\\src\\\\main\\\\java")};
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            Class Clazz = urlClassLoader.loadClass("com.ljw.test_demo.dao.$Proxy");
            Constructor constructor = Clazz.getConstructor(targetInfo);
            proxy = constructor.newInstance(target);
        } catch (Exception e) {
            e.printStackTrace();
        }


        /*
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos  = null;
        PrintWriter pw = null;
        Boolean bool = false;
        String filein = content+"\r\n";//新写入的行，换行
        String temp  = "";
        try {
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();
            //文件原有内容
            for(int i=0;(temp =br.readLine())!=null;i++){
                buffer.append(temp);
                // 行与行之间的分隔符 相当于“\n”
                buffer = buffer.append(System.getProperty("line.separator"));
            }
            buffer.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;

        } catch (Exception  e) {
            e.printStackTrace();
        }finally {
            try {
            //不要忘记关闭
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


*/



/*
        String classes1 = packerContent+importContent+Content1+Content2+Content3 + methodsContent+"}";
        System.out.println(classes1);
*/


        return proxy;
    }

    public static void main(String[] args) {

        UserDao userDao = new UserDaoImpl();

        UserDao proxy = (UserDao)newProxyInstance(userDao);
        proxy.query("莫里斯");


    }
}
