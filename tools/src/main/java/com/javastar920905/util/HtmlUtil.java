package com.javastar920905.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ouzhx on 2018/3/9.
 */
public class HtmlUtil {
  /** 去掉Html中<!-- -->注释 **/
  private static Pattern COMMET_PATTERN = Pattern.compile("<!--[\\w\\W\\r\\n]*?-->");

  /**
   * 去掉Html中<!-- -->注释
   *
   * @param html html字符串
   * @return 返回处理好的String
   * @author chenjun
   */
  public static String trimHtmlComments(String html) {
    return COMMET_PATTERN.matcher(html).replaceAll("");
  }

  public static String trimHTMLTag(String htmlStr) {
    String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

    Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
    Matcher m_script = p_script.matcher(htmlStr);
    htmlStr = m_script.replaceAll(""); // 过滤script标签

    Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
    Matcher m_style = p_style.matcher(htmlStr);
    htmlStr = m_style.replaceAll(""); // 过滤style标签

    Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
    Matcher m_html = p_html.matcher(htmlStr);
    htmlStr = m_html.replaceAll(""); // 过滤html标签

    htmlStr = htmlStr.replaceAll("&nbsp;", "");
    htmlStr = htmlStr.replaceAll("//s*|/t|/r|/n", "");

    return htmlStr.trim(); // 返回文本字符串
  }

  /*
   * public static void main(String [] args){ String
   * html="<p style=\"margin-top: 2px; margin-bottom: 2px; line-height: 1.75em;\">\n" +
   * "    <span style=\"font-family: 微软雅黑;\"></span><br/>\n" + "</p>\n" + "<p>\n" +
   * "    编者按：在你不知道的地方，有些人活在了《1984》。\n" + "</p>\n" + "<p>\n" +
   * "    <img alt=\"数字时代的“思想钢印”：有一群人坚信自己被芯片控制，生活如傀儡\" data-img-size-val=\"1515,691\" src=\"https://pic.36krcnd.com/201803/06020602/bk92gt9itg54n5uq.png!1200\" style=\"box-sizing: border-box; vertical-align: middle; border: 0px; opacity: 1; transition: opacity 0.3s ease-in; max-width: 100%; border-radius: 5px; width: 720px; height: auto;\"/>\n"
   * + "</p>\n" + "<p>\n" +
   * "    成千上万的人都认为政府正在使用植入芯片和电子光束来控制他们的思想，他们不顾一切地想证明自己没有妄想症。《连线》杂志发表了一篇文章，报道了相关故事。文章由36氪编译。\n" +
   * "</p>\n" + "<h2>\n" + "    一、\n" + "</h2>\n" + "<p>\n" +
   * "    每天早上醒来，莉莎（Liza）都会想起自己曾经被折磨过的经历。当她低头看自己的手时，能看到微微隆起的肿块，她认为自己身体内已经被植入微芯片了。她确信这些芯片会追踪她的一举一动，她的家人也已经被芯片设定好了，不相信她说的每一句话。她知道自己的思想已经被推到了人类忍耐的极限（在人死之前，你可以给其带来最大的痛苦）。她的认为自己是一个非常极端的目标个体（Targeted Individuals）——需要将大脑重新连接，以至于她连哭都不可能了。\n"
   * + "</p>\n" + "<p style=\"margin-top: 2px; margin-bottom: 2px; line-height: 1.75em;\">\n" +
   * "    <span style=\"font-family: 微软雅黑;\"></span><br/>\n" + "</p>";
   * 
   * System.out.println(trimHTMLTag(html)); }
   */
}
