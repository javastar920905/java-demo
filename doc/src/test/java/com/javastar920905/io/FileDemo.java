package com.javastar920905.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author ouzhx on 2018/4/11.
 *
 *
 */
public class FileDemo {

  /**
   * 文件操作
   */
  @Test
  public void fileTest() {
    // 与系统有关的默认名称分隔符。此字段被初始化为包含系统属性 file.separator 值的第一个字符。
    // 在 UNIX 系统上，此字段的值为 '/'；在 Microsoft Windows 系统上，它为 '\\'。
    System.out.println(File.separator);
    // 在 UNIX 系统上，此字段为 ':'；在 Microsoft Windows 系统上，它为 ';'。
    System.out.println(File.pathSeparator);

    File file = new File("src/test.txt");
    try {
      if (!file.exists()) {
        file.createNewFile();
      }
      // 文件所在目录为:src
      System.out.println("文件所在目录为:" + file.getParent());
      // test.txt
      System.out.println(file.getName());
      // D:\gitrepository\java-demo\doc\src\test.txt
      System.out.println(file.getAbsolutePath());
      // 相对项目路径 src\test.txt
      System.out.println(file.getPath());
      System.out.println("该分区大小" + file.getTotalSpace() / (1024 * 1024 * 1024) + "G");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 目录操作
   */
  @Test
  public void fileTest2() {
    // 创建多级目录
    File tmpDir = new File("tmp/child");
    tmpDir.mkdirs();

    File dir = new File("src");
    try {
      // 列出直接 子目录和文件
      if (dir.isDirectory()) {
        Arrays.asList(dir.list()).forEach(fileItem -> System.out.println(fileItem));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 修改指定文件夹下 所有文件名称
   */
  @Test
  public void modifyDirectionFilesName() {
    String path = "E:\\bgwall\\BT";

    File dir = new File(path);
    if (dir.isDirectory()) {
      File[] files = dir.listFiles();

      // 批量修改文件名
      for (File file : files) {
        // 取后缀名前面部分
        String oldFileName = file.getName().split("\\.")[0];
        // 文件名删除"中文"并以后缀名".jpg"结尾
        String newFileName = oldFileName.replace("分层", "") + ".jpg";

        File newFile = new File(path + "\\" + newFileName);
        file.renameTo(newFile);
      }
    }
  }
}
