package com.javastar920905.outer;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static net.logstash.logback.marker.Markers.append;
import static net.logstash.logback.marker.Markers.appendArray;

/**
 * Created by chenjun on 2016/8/11.
 */
public class HttpUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);
  /**
   * 日志记录请求参数时 的key
   */
  private static final String PARAMS = "params";

  /**
   * 日志记录异常类型 的key
   */
  private static final String ERRORTYPE = "errorType";


  private HttpUtil() {}


  public static String doGet(String url) {
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    HttpGet request = new HttpGet(url);
    try {
      // 添加请求头
      installHead(request);
      HttpResponse response = httpClient.execute(request);
      return getHtml(response);
    } catch (IOException e) {
      LOGGER.error(appendArray(PARAMS, url).and(append(ERRORTYPE, e.toString())), "执行get请求失败", e);
      return null;
    }
  }

  public static String doPost(String url, Map<String, String> formData) {
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    HttpPost request = new HttpPost(url);
    try {
      installFormData(formData, request);
      installHead(request);
      HttpResponse response = httpClient.execute(request);
      return getHtml(response);
    } catch (IOException e) {
      LOGGER.error(appendArray(PARAMS, url).and(append(ERRORTYPE, e.toString())), "执行post请求失败", e);
      return null;
    }
  }

  public static String doPut(String url, Map<String, String> formData) {
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    HttpPut request = new HttpPut(url);
    try {
      installFormData(formData, request);
      HttpResponse response = httpClient.execute(request);

      return getHtml(response);
    } catch (IOException e) {
      LOGGER.error(appendArray(PARAMS, url).and(append(ERRORTYPE, e.toString())), "执行put请求失败", e);
      return null;
    }
  }

  /**
   * 解析html返回值
   *
   * @param response
   * @return
   */
  public static String getHtml(HttpResponse response) {
    StringBuffer result = new StringBuffer();
    try (InputStream inputStream = response.getEntity().getContent()) {
      BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
      String line;
      while ((line = rd.readLine()) != null) {
        result.append(line);
      }
    } catch (Exception e) {
      LOGGER.error(appendArray(PARAMS, response).and(append(ERRORTYPE, e.toString())),
          "解析html返回值失败", e);
    }
    return result.toString();
  }

  /**
   * 添加通用请求头信息
   *
   * @param request
   */
  public static void installHead(HttpRequestBase request) {
    request.addHeader("User-Agent",
        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)Chrome/49.0.2623.110 Safari/537.36");
    request.addHeader("Accept", "*/*");
    request.addHeader("Accept-Encoding", "gzip, deflate, sdch");
    request.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
    request.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
    request.setProtocolVersion(HttpVersion.HTTP_1_0);
  }

  /**
   * post请求添加参数
   *
   * @param parameter
   * @param request
   */
  public static void installFormData(Map<String, String> parameter,
      HttpEntityEnclosingRequestBase request) {
    if (parameter != null) {
      List<NameValuePair> formData = new ArrayList<>();
      for (String key : parameter.keySet()) {
        String value = parameter.get(key);
        formData.add(new BasicNameValuePair(key, value));
      }
      request.setEntity(new UrlEncodedFormEntity(formData, Consts.UTF_8));
    }
  }




}
