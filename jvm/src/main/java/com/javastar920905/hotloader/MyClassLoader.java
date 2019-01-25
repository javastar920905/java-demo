package com.javastar920905.hotloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author ouzhx on 2019/1/25.
 */
public class MyClassLoader extends ClassLoader {
    /**
     * 要加载的classpath 路径
     */
    private String classPath;

    public MyClassLoader(String classPath) {
        super(ClassLoader.getSystemClassLoader());
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = this.loadClassData(name);

        return this.defineClass(name, data, 0, data.length);
    }

    /**
     * 加载class 文件中的内容
     *
     * @param name
     * @return
     */
    private byte[] loadClassData(String name) {
        //将包名中的.转换为//
        name.replace(".", "//");
        try (FileInputStream is = new FileInputStream(new File(classPath + name + ".class"))) {

            //构建输出流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            //读取数据
            int b = 0;
            while ((b = is.read()) != -1) {
                baos.write(b);
            }

            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
