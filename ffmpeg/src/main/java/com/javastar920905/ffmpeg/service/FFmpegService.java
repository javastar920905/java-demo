package com.javastar920905.ffmpeg.service;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import net.bramp.ffmpeg.builder.FFmpegOutputBuilder;
import org.springframework.core.io.ClassPathResource;

import com.javastar920905.ThreadUtil;
import com.javastar920905.ffmpeg.constants.FFmpegConstants;
import com.javastar920905.outer.StringUtil;

import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;

/**
 * @author ouzhx on 2018/5/31.
 */
public class FFmpegService {
  /**
   * 获取class path 路径
   * 
   * @return
   */
  public static String getClassPath() {
    // 获取classpath 路径 file:/D:/gitrepository/java-demo/ffmpeg/out/production/classes/
    String path = FFmpegService.class.getResource("/").getPath();

    if (System.getProperty("os.name").toLowerCase().contains("linux")) {
      return path;
    } else {
      return path.substring(1, path.length());
    }
  }

  /**
   * 获取reources 目录下的文件的 全路径
   * 
   * @param fileName
   * @return
   */
  public static String getFileAbsolutePath(String fileName) {
    String path = null;
    try {
      // 从classpath根路径获取资源 /D:/gitrepository/java-demo/ffmpeg/out/production/resources/test.mp3
      path = new ClassPathResource(fileName).getURL().getPath();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (System.getProperty("os.name").toLowerCase().contains("linux")) {
      return path;
    } else {
      return path.substring(1, path.length());
    }
  }


  /**
   * 合并多个音频(两个音频会重叠)
   * 
   * 使用示例: new FFmpegService().combineAudio("1.mp3", "2.mp3");
   */
  public void combineAudio(String... sources) {
    try {
      FFmpegBuilder builder = new FFmpegBuilder();
      Arrays.stream(sources).forEach(file -> {
        String input1Path = getFileAbsolutePath(file);
        builder.addInput(input1Path);
      });
      builder.overrideOutputFiles(true);

      // 两种方式都行 builder.setComplexFilter("amix=inputs=2:dropout_transition=5");
      builder.addExtraArgs("-filter_complex", "amix=inputs=2:dropout_transition=5");
      builder.addOutput(getClassPath() + "output.mp3").done();

      FFmpegConstants.FFMPEG_EXECUTOR.createJob(builder).run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 拼接多个音频(多个音频顺序拼接播放)
   *
   * 使用示例: new FFmpegService().combineAudio("1.mp3", "2.mp3");
   */
  public void joinAudio(String... sources) {
    try {
      FFmpegBuilder builder = new FFmpegBuilder();
      StringBuilder sb = new StringBuilder();
      sb.append("concat:");
      Arrays.stream(sources).forEach(file -> {
        sb.append(getFileAbsolutePath(file)).append("|");

      });
      builder.setInput(sb.toString());
      builder.overrideOutputFiles(true);
      builder.addOutput(getClassPath() + "join-output.mp3").done();
      FFmpegConstants.FFMPEG_EXECUTOR.createJob(builder).run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

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
  public void adjustVoiceSpeed(String inputFile) {
    FFmpegBuilder builder = new FFmpegBuilder();
    builder.setInput(inputFile);
    builder.overrideOutputFiles(true);

    builder.addOutput(getClassPath() + "output.mp3").setFormat("mp4")
        .addExtraArgs("-filter:a \"atempo=2.0\" -vn").setStrict(FFmpegBuilder.Strict.EXPERIMENTAL)
        .done();
    // 报错 At least one output file must be specified
    FFmpegConstants.FFMPEG_EXECUTOR.createJob(builder).run();
  }

  public static void main(String[] args) {
    // new FFmpegService().joinAudio("1.mp3", "2.mp3");
    new FFmpegService().adjustVoiceSpeed(getFileAbsolutePath("2.mp3"));
  }


  /**
   * 获取音频文件持续时长
   *
   * @param audioPath 音频文件绝对路径
   * @return 音频持续长度, 单位:秒
   */
  public static Double getAudioDuration(String audioPath) {
    Double duration;
    try {
      FFmpegProbeResult probeResult = FFmpegConstants.FFPROBE.probe(audioPath);
      FFmpegFormat format = probeResult.getFormat();
      duration = format.duration;
    } catch (Exception e) {
      duration = 0.0;
    }

    return duration;
  }

  /**
   * 获取格式化后的音频文件持续时长
   *
   * @param audioPath 音频文件绝对路径
   * @return 格式化的持续时长(秒转分)
   */
  public static String getAudioDurationFormater(String audioPath) {
    Double duration = getAudioDuration(audioPath);
    String formatDuration;
    long minutes = (duration.longValue() % 3600) / 60;
    long secends = duration.longValue() % 60;
    if (minutes <= 0) {
      formatDuration = String.format("%d秒", secends);
    } else {
      formatDuration = String.format("%02d分%02d秒", minutes, secends);
    }
    return formatDuration;
  }


  /**
   * 把mp3转换为wav
   *
   * @param path MP3路径
   * @return 如果转换成功返回true, 否则返回false
   * @author chenjun
   */
  public static String convertMP3ToWAV(String path) {
    return convert(path, "wav", "-ac", "1", "-ar", "16000");
  }

  /**
   * 把mp3转换为PCM
   *
   * @param path MP3路径
   * @return 如果转换成功返回true, 否则返回false
   * @author chenjun
   */
  public static String convertMP3ToPCM(String path) {
    return convert(path, "pcm", "-acodec", "pcm_s16le", "-f", "s16le", "-ac", "1", "-ar", "16000");
  }

  /**
   * 转换音频文件
   *
   * @param source 源文件
   * @param suffix 新文件后缀
   * @param param 转换参数
   * @return 返回转换后的文件路径
   */
  private static String convert(String source, String suffix, String... param) {
    String target = "";
    try {
      target = String.format("%s.%s", StringUtil.substringBeforeLast(source, "."), suffix);
      CountDownLatch latch = new CountDownLatch(1);
      FFmpegBuilder builder = new FFmpegBuilder().setInput(source).overrideOutputFiles(true)
          .addOutput(target).addExtraArgs(param).done();
      ThreadUtil.threadPoolExecutor
          .execute(FFmpegConstants.FFMPEG_EXECUTOR.createJob(builder, progress -> {
            if (progress.isEnd()) {
              // 延迟删除文件
              // delayToDeleteFile(source);
              latch.countDown();
            }
          }));
      latch.await(30, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      // LOGGER.error("{}转换为{}超时", source, param);
    }

    return target;
  }

}
