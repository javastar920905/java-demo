package com.javastar920905.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ouzhx on 2018/3/7.
 */
public class BeanUtil {
  /**
   * 对象转数组
   * 
   * @param obj
   * @return
   */
  public static byte[] toByteArray(Object obj) {
    byte[] bytes = null;
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    try {
      ObjectOutputStream oos = new ObjectOutputStream(bos);
      oos.writeObject(obj);
      oos.flush();
      bytes = bos.toByteArray();
      oos.close();
      bos.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return bytes;
  }

  /**
   * 数组转对象
   * 
   * @param bytes
   * @return
   */
  public static Object toObject(byte[] bytes) {
    Object obj = null;
    try {
      ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
      ObjectInputStream ois = new ObjectInputStream(bis);
      obj = ois.readObject();
      ois.close();
      bis.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    } catch (ClassNotFoundException ex) {
      ex.printStackTrace();
    }
    return obj;
  }

  public static List<String> toListString(List<byte[]> list) {
    if (list != null) {
      List<String> list1 = new ArrayList<>(list.size());
      list.stream().forEach(byteObj -> {
        list1.add(new String(byteObj));
      });
      return list1;
    }
    return null;
  }
}
