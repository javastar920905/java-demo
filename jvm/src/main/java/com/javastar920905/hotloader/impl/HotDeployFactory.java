package com.javastar920905.hotloader.impl;

import com.javastar920905.hotloader.MyClassLoader;
import org.apache.commons.lang3.ClassPathUtils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ouzhx on 2019/1/25.
 * <p>
 * 下面是一些得到classpath和当前类的绝对路径的一些方法。你可能需要使用其中的一些方法来得到你需要的资源的绝对路径。
 * <p>
 * 1.this.getClass().getResource（""）
 * 得到的是当前类class文件的URI目录。不包括自己！
 * 如：file：/D：/workspace/jbpmtest3/bin/com/test/
 * <p>
 * 2.this.getClass().getResource（"/"）
 * 得到的是当前的classpath的绝对URI路径 。
 * 如：file：/D：/workspace/jbpmtest3/bin/
 * <p>
 * 3.this.getClass() .getClassLoader().getResource（""）
 * 得到的也是当前ClassPath的绝对URI路径 。
 * 如：file：/D：/workspace/jbpmtest3/bin/
 * <p>
 * 4.ClassLoader.getSystemResource（""）
 * 得到的也是当前ClassPath的绝对URI路径 。
 * 如：file：/D：/workspace/jbpmtest3/bin/
 * <p>
 * 5.Thread.currentThread().getContextClassLoader ().getResource（""）
 * 得到的也是当前ClassPath的绝对URI路径 。
 * 如：file：/D：/workspace/jbpmtest3/bin/
 * <p>
 * 6.ServletActionContext.getServletContext().getRealPath(“/”)
 * Web应用程序 中，得到Web应用程序的根目录的绝对路径。这样，我们只需要提供相对于Web应用程序根目录的路径，就可以构建出定位资源的绝对路径。
 * 如：file：/D:/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/WebProject
 */
public class HotDeployFactory {
    /**
     * 记录热加载类的加载信息
     **/
    public static final Map<String, LoaderInfo> loadTimeMap = new HashMap();


    public static final String classPath = "E:/gitRepository/java-demo/jvm/out/production/classes";

    //实现热加载 的类的全名(包名+类名)  com/javastar920905/hotloader/MyClassLoader ClassPathUtils.toFullyQualifiedPath(MyClassLoader.class, "MyClassLoader")
    public static final String myHotClassName = "com.javastar920905.hotloader.impl.MyHotImpl";

//    public static void main(String[] args) {
//        //ConfigurableApplicationContext context = SpringApplication.run(TaxArticleApplication.class, args);
//        System.out.println(classPath);
//    }


    public static HotDeployBase getHotClass(String className) {
        File loadFile = new File(classPath + className.replaceAll("\\.", "/") + ".class");
        long lastModify = loadFile.lastModified();

        if (loadTimeMap.get(className) == null) {
            load(className, lastModify);
        } else if (loadTimeMap.get(className).getLoadTime() != lastModify) {
            //加载类时间戳变化,重新加载
            load(className, lastModify);
        }

        return loadTimeMap.get(className).getHotDeployBase();
    }

    private static void load(String className, long lastModify) {
        MyClassLoader myClassLoader = new MyClassLoader(classPath);
        Class<?> loadClass = null;
        try {
            loadClass = myClassLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        HotDeployBase hotDeployBase = newInstance(loadClass);
        LoaderInfo loaderInfo = new LoaderInfo(myClassLoader, lastModify);
        loaderInfo.setHotDeployBase(hotDeployBase);
        loadTimeMap.put(className, loaderInfo);

    }

    private static HotDeployBase newInstance(Class<?> loadClass) {
        try {
            return (HotDeployBase) loadClass.getConstructor(new Class[]{}).newInstance(new Object[]{});

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }
}
