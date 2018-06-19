package com.javastar920905.ffmpeg.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author ouzhx on 2018/6/19.
 *
 *         java 调用命令行工具
 * 
 *         https://blog.csdn.net/xinghuo0007/article/details/53129379
 */
public class CmdUtil {

  /**
   * 官方文档 http://trac.ffmpeg.org/wiki/How%20to%20speed%20up%20/%20slow%20down%20a%20video
   * https://blog.csdn.net/matrix_laboratory/article/details/53158307 调整音频倍数 ffmpeg -i 2.mps
   * -filter:a "atempo=2.0" -vn output.mp3
   *
   * (setInput() 会添加 -y 覆盖 -i input参数,-vn参数可以省略,参数不能以空格开头)
   *
   * 构建顺序 是定义在FFmpegBuilder#build()
   *
   */
  public static void main(String[] args) {
    // ffmpeg 的安装目录
    String ffmpegPath = "E:/ffmpeg-3.4.1-win64-static/bin/ffmpeg.exe";
    String sourcePath = FFmpegService.getFileAbsolutePath("2.mp3");
    String outputPath = FFmpegService.getClassPath() + "output2_0.mp3'";
    String commandStr = String.format("%s -y -v error -i %s -filter:a \"atempo=2.0\" -vn  %s",
        ffmpegPath, sourcePath, outputPath);

    CmdUtil.exeCmd(commandStr);
  }

  public static void exeCmd(String commandStr) {
    System.out.println("正在执行cmd命令行: " + commandStr);
    BufferedReader br = null;
    try {
      Process p = Runtime.getRuntime().exec(commandStr);
      br = new BufferedReader(new InputStreamReader(p.getInputStream()));
      String line = null;
      StringBuilder sb = new StringBuilder();
      while ((line = br.readLine()) != null) {
        sb.append(line + "\n");
      }
      System.out.println(sb.toString());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
}
