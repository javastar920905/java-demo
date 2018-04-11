package com.javastar920905.io;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

/**
 * @author ouzhx on 2018/4/11.
 *
 *         字节输入流demo https://blog.csdn.net/yczz/article/details/38761237
 */
public class InputStreamDemo {
  private static final byte[] buffer = new byte[1024];

  /**
   *
   * InputStream/OutPutStream - - -字节流基类 根据构造函数的参数,选择合适的流即可
   *
   * FileInputStream/FileOutputStream - - - - -处理文件类型
   *
   * ByteArrayInputStream/ByteArrayOutputStream - - - -字节数组类型
   *
   * DataInputStream/DataOutputStream - - - -装饰类
   *
   * BufferedInputStream/BufferedOutputStream - - - -缓冲流
   *
   * InputStream是一个输入流，也就是用来读取文件的流，抽象方法read读取下一个字节，当读取到文件的末尾时候返回
   * -1。如果流中没有数据read就会阻塞直至数据到来或者异常出现或者流关闭。
   */
  @Test
  public void inputStreamDemo() {
    String str = "this is test char";

    StringBuilder sb = new StringBuilder();
    try (InputStream inputStream = new ByteArrayInputStream(str.getBytes())) {
      while (inputStream.read(buffer) != -1) {
        sb.append(new String(buffer));
      }
      System.out.println(sb.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * 一次性读取指定长度的
   * 
   * 这个程序还有很多缺陷，就是这个程序进行程序拷贝的时候，是你的源文件有多大 它就会去形成多大的临界缓冲区，假如我们这个文件过大的话，就会导致我们的内 存占用过大，造成电脑死机等等问题。
   * （但是我们也得明白：缓冲区的大小越大，必然拷贝搬运文件的速度就越快 但是缓冲区的大小最大就是需要拷贝文件的大小，再大的话拷贝的速度也不会再 提升了）
   */
  @Test
  public void inputStreamDemo2() {
    String str = "this is test char";

    try (InputStream inputStream = new ByteArrayInputStream(str.getBytes())) {
      byte[] cache = new byte[str.length()];
      // 将读取的长度 保存到cache中
      inputStream.read(cache);

      System.out.println(new String(cache));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void outputStreamTest() {
    String str = "this is  output test char";

    try (OutputStream outputStream = new FileOutputStream("src/outputStreamTest.txt")) {
      byte[] cache = str.getBytes();
      // 将cache中内容输出到文件
      outputStream.write(cache);

      System.out.println(new String(cache));
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
