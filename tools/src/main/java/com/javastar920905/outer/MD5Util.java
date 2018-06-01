package com.javastar920905.outer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * Created by  on 2016/5/13.
 */
public class MD5Util {

  private static final Logger LOGGER = LoggerFactory.getLogger(MD5Util.class);

  private MD5Util() {

  }

  /**
   * 加密字符串
   * 
   * @param rawPass
   * @param salt
   * @return 加密后的字符串
   */
  public static final String encode(String rawPass, Object salt) {
    String saltedPass = mergePasswordAndSalt(rawPass, salt);
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("MD5");
      byte[] digest = messageDigest.digest(saltedPass.getBytes("UTF-8"));
      return new String(byteArrayToHex(digest));
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return rawPass;
    }
  }

  /**
   * 拼接密码与盐值
   */
  private static String mergePasswordAndSalt(String password, Object salt) {
    if (StringUtil.isBlank(salt.toString())) {
      return password;
    }
    return password + salt.toString();
  }

  /**
   * 把字节数组转为十六进制
   *
   * @param byteArray
   * @return
   */
  private static String byteArrayToHex(byte[] byteArray) {
    char[] hexDigits =
        {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    char[] resultCharArray = new char[byteArray.length * 2];
    int index = 0;
    for (byte b : byteArray) {
      resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
      resultCharArray[index++] = hexDigits[b & 0xf];
    }
    return new String(resultCharArray);
  }
}
