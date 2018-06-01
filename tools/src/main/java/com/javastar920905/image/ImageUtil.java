package com.javastar920905.image;

import com.javastar920905.outer.StringUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

/**
 * @author ouzhx on 2018/6/1.
 */
public class ImageUtil {

  /**
   * 下载图片
   *
   * @param imageURL 图片url
   * @param outputPath 保存图片地址
   * @param imageType 保存的图片类型
   */
  public static boolean downloadImage(String imageURL, String outputPath, String imageType) {
    boolean result = false;
    try {
      BufferedImage image = ImageIO.read(new URL(imageURL));
      result = ImageIO.write(image, imageType, new File(outputPath));
    } catch (MalformedURLException e) {
      // LOGGER.error("下载的图片地址不存在!", e);
    } catch (IOException e) {
      // LOGGER.error(e.getMessage(), e);
    }
    return result;
  }

  /**
   * 将图片转化为base64字符串
   *
   * @param image 图片file对象
   * @return
   */
  public static String convertImage2Base64(File image) {
    String result = StringUtil.EMPTY;
    if (image == null) {
      return result;
    }
    try (InputStream input = new FileInputStream(image)) {
      byte[] data = new byte[input.available()];
      input.read(data);
      Base64.Encoder encoder = Base64.getEncoder();
      result = encoder.encodeToString(data);
    } catch (IOException e) {
      // Do Nothing
    }
    return result;
  }
}
