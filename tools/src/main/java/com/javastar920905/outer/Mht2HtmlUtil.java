package com.javastar920905.outer;


import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Enumeration;

public class Mht2HtmlUtil {

  /**
   * 将 mht文件转换成 html文件
   *
   * @param s_SrcMht
   * @param s_DescHtml
   */
  public static void mht2html(String s_SrcMht, String s_DescHtml) {
    try {
      InputStream fis = new FileInputStream(s_SrcMht);
      Session mailSession = Session.getDefaultInstance(System.getProperties(), null);
      MimeMessage msg = new MimeMessage(mailSession, fis);
      Object content = msg.getContent();
      if (content instanceof Multipart) {
        MimeMultipart mp = (MimeMultipart) content;
        MimeBodyPart bp1 = (MimeBodyPart) mp.getBodyPart(0);

        // 获取mht文件内容代码的编码
        String strEncodng = getEncoding(bp1);

        // 获取mht文件的内容
        String strText = getHtmlText(bp1, strEncodng);
        if (strText == null){
            return;
        }
        // 最后保存HTML文件
        SaveHtml(strText, s_DescHtml, strEncodng);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 根据文件路劲获取mht文件内容
   * 
   * @param
   * @return
   * @author liuwb
   * @date 2017/3/2
   */
  public static String getMhtContentByFilePath(String filePath) {
    try {
      InputStream fis = new FileInputStream(filePath);
      return getMhtContentByInputStream(fis);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 根据文件流获取mht文件内容
   * 
   * @param
   * @return
   * @author liuwb
   * @date 2017/3/2
   */
  public static String getMhtContentByInputStream(InputStream fis) {
    try {
      Session mailSession = Session.getDefaultInstance(System.getProperties(), null);
      MimeMessage msg = new MimeMessage(mailSession, fis);
      Object content = msg.getContent();
      if (content instanceof Multipart) {
        MimeMultipart mp = (MimeMultipart) content;
        MimeBodyPart bp1 = (MimeBodyPart) mp.getBodyPart(0);

        // 获取mht文件内容代码的编码
        String strEncodng = getEncoding(bp1);

        // 获取mht文件的内容
        return getHtmlText(bp1, strEncodng);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 获取mht文件内容中资源文件的名称
   *
   * @param strName
   * @param ID
   * @return
   */
  public static String getName(String strName, int ID) {
    char separator1 = '/';
    char separator2 = '\\';
    // 将换行替换
    strName = strName.replaceAll("\r\n", "");

    // 获取文件名称
    if (strName.lastIndexOf(separator1) >= 0) {
      return strName.substring(strName.lastIndexOf(separator1) + 1);
    }
    if (strName.lastIndexOf(separator2) >= 0) {
      return strName.substring(strName.lastIndexOf(separator2) + 1);
    }
    return "";
  }

  /**
   * 将提取出来的html内容写入保存的路径中。
   */
  public static boolean SaveHtml(String s_HtmlTxt, String s_HtmlPath, String s_Encode) {
    try {
      Writer out = null;
      out = new OutputStreamWriter(new FileOutputStream(s_HtmlPath, false), s_Encode);
      out.write(s_HtmlTxt);
      out.close();
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * 保存网页中的JS、图片、CSS样式等资源文件
   *
   * @param SrcFile 源文件
   * @param inputStream 输入流
   * @return
   */
  private static boolean SaveResourcesFile(File SrcFile, InputStream inputStream) {
    if (SrcFile == null || inputStream == null) {
      return false;
    }

    BufferedInputStream in = null;
    FileOutputStream fio = null;
    BufferedOutputStream osw = null;
    try {
      in = new BufferedInputStream(inputStream);
      fio = new FileOutputStream(SrcFile);
      osw = new BufferedOutputStream(new DataOutputStream(fio));
      int index = 0;
      byte[] a = new byte[1024];
      while ((index = in.read(a)) != -1) {
        osw.write(a, 0, index);
      }
      osw.flush();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    } finally {
      try {
        if (osw != null){
            osw.close();
        }
        if (fio != null){
            fio.close();
        }
        if (in != null){
            in.close();
        }
        if (inputStream != null){
            inputStream.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
    }
  }

  /**
   * 获取mht文件里资源文件的URL路径
   *
   * @param bp
   * @return
   */
  private static String getResourcesUrl(MimeBodyPart bp) {
    if (bp == null) {
      return null;
    }
    try {
      Enumeration list = bp.getAllHeaders();
      while (list.hasMoreElements()) {
        javax.mail.Header head = (javax.mail.Header) list.nextElement();
        if (head.getName().compareTo("Content-Location") == 0) {
          return head.getValue();
        }
      }
      return null;
    } catch (MessagingException e) {
      return null;
    }
  }

  /**
   * 获取mht文件中的内容代码
   *
   * @param bp
   * @param strEncoding 该mht文件的编码
   * @return
   */
  private static String getHtmlText(MimeBodyPart bp, String strEncoding) {
    InputStream textStream = null;
    BufferedInputStream buff = null;
    BufferedReader br = null;
    Reader r;
    try {
      textStream = bp.getInputStream();
      buff = new BufferedInputStream(textStream);
      r = new InputStreamReader(buff, strEncoding);
      br = new BufferedReader(r);
      StringBuffer strHtml = new StringBuffer("");
      String strLine;
      while ((strLine = br.readLine()) != null) {
        strHtml.append(strLine + "\r\n");
      }
      br.close();
      r.close();
      textStream.close();
      return strHtml.toString();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (br != null){
            br.close();
        }
        if (buff != null){
            buff.close();
        }
        if (textStream != null){
            textStream.close();
        }
      } catch (Exception e) {
      }
    }
    return null;
  }

  /**
   * 获取mht网页文件中内容代码的编码
   *
   * @param bp
   * @return
   */
  private static String getEncoding(MimeBodyPart bp) {
    if (bp == null) {
      return null;
    }
    try {
      Enumeration list = bp.getAllHeaders();
      while (list.hasMoreElements()) {
        javax.mail.Header head = (javax.mail.Header) list.nextElement();
        if (head.getName().equalsIgnoreCase("Content-Type")) {
          String strType = head.getValue();
          int pos = strType.indexOf("charset=");
          if (pos >= 0) {
            String strEncoding = strType.substring(pos + 8, strType.length());
            if (strEncoding.startsWith("\"") || strEncoding.startsWith("\'")) {
              strEncoding = strEncoding.substring(1, strEncoding.length());
            }
            if (strEncoding.endsWith("\"") || strEncoding.endsWith("\'")) {
              strEncoding = strEncoding.substring(0, strEncoding.length() - 1);
            }
            if (strEncoding.toLowerCase().compareTo("gb2312") == 0) {
              strEncoding = "gbk";
            }
            return strEncoding;
          }
        }
      }
    } catch (MessagingException e) {
      e.printStackTrace();
    }
    return null;
  }
}
