package com.javastar920905.ffmpeg.constants;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;

import java.io.IOException;

/**
 * @author ouzhx on 2018/5/31.
 * 
 *         参考文档: https://github.com/bramp/ffmpeg-cli-wrapper
 */
public class FFmpegConstants {
  /** ffmpeg 安装bin目录 **/
  private static final String FFMPEG_HOME = "E:\\ffmpeg-3.4.1-win64-static\\bin\\";
  private static final String FFMPEG_SUFFIX = ".exe";

  private static final String PATH_FFMPEG = FFMPEG_HOME + "ffmpeg" + FFMPEG_SUFFIX;
  private static final String PATH_FFPROBE = FFMPEG_HOME + "ffprobe" + FFMPEG_SUFFIX;

  public static FFmpeg FFMPEG;
  public static FFprobe FFPROBE;
  public static FFmpegExecutor FFMPEG_EXECUTOR;



  static {
    try {
      FFMPEG = new FFmpeg(PATH_FFMPEG);
      FFPROBE = new FFprobe(PATH_FFPROBE);
      FFMPEG_EXECUTOR = new FFmpegExecutor(FFMPEG);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
