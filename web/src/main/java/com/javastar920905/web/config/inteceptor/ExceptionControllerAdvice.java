package com.javastar920905.web.config.inteceptor;

import static net.logstash.logback.marker.Markers.append;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.javastar920905.outer.spring.SpringMailSender;
import com.javastar920905.util.StringUtil;

/**
 * 统一处理异常切面
 */
@ControllerAdvice
public class ExceptionControllerAdvice {
  private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
  @Value("${email.exception.receiver}")
  private String exceptionReceiver;

  /**
   * 处理Controller抛出的异常
   */
  @ExceptionHandler
  public ModelAndView processException(HttpServletRequest request, HttpServletResponse response,
      Exception exception) {
    // 生成异常Id
    String exceptionId = this.generateExceptionId();
    // 日志记录
    LOGGER.error(append("_exception_id", exceptionId), exception.getMessage(), exception);
    // 发送异常日志邮件
    new Thread(() -> SpringMailSender.simpleSend(exceptionReceiver, exception.toString(),
        StringUtil.stackTraceToString(exception))).start();
    // 设置页面参数1
    Map<String, Object> attributes = new HashMap<>();
    Map<String, String[]> parms = request.getParameterMap();
    attributes.put("params", parms);
    attributes.put("_exception_id", exceptionId);
    attributes.put("_error_msg", "系统产生了一个错误");

    // 返回请求
    if (containsIgnoreCase(request.getHeader("accept"), "application/json")
        || containsIgnoreCase(request.getHeader("X-Requested-With"), "XMLHttpRequest")) { // Ajax请求
      attributes.put("_success", false);
      response.setContentType("application/json;charset=UTF-8");
      try (PrintWriter writer = response.getWriter()) {
        writer.write(new JSONObject(attributes).toString());
        writer.flush();
      } catch (IOException e) {
        LOGGER.error(e.getMessage(), e);
      }

      return null;
    } else { // 页面访问
      // 设置Http状态码
      response.setStatus(500);
      // 禁用缓存
      this.preventCaching(response);
      // 跳转错误页面
      return new ModelAndView("forward:/serviceError/", attributes);
    }
  }

  /**
   * 生成异常Id
   */
  private synchronized String generateExceptionId() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }

  /**
   * 禁用页面缓存
   */
  private void preventCaching(HttpServletResponse response) {
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 1L);
    response.setHeader("Cache-Control", "no-cache");
    response.addHeader("Cache-Control", "no-store");
  }

}
