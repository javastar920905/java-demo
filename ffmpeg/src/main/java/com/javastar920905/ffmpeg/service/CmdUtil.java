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
  public static void main(String[] args) {
    String ffmpegPath = "E:/ffmpeg-3.4.1-win64-static/bin/ffmpeg.exe";
    String sourcePath = "D:/gitrepository/java-demo/ffmpeg/out/production/resources/2.mp3";
    String outputPath = "D:/gitrepository/java-demo/ffmpeg/out/production/classes/output2_0.mp3";
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
