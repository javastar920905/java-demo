package com.javastar920905.spider.config.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.UrlPathHelper;

/**
 * @author  ouzhx on 2017/6/26.
 * 自定义spring mvc 404 跳转页面
 */
public class CustomDispatcherServlet extends DispatcherServlet {

  private static final long serialVersionUID = 1L;
  private static final UrlPathHelper urlPathHelper = new UrlPathHelper();
  private String fileNotFondUrl = "/404";

  public CustomDispatcherServlet(WebApplicationContext webApplicationContext) {
    super(webApplicationContext);
  }

  @Override
  public void noHandlerFound(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    if (pageNotFoundLogger.isWarnEnabled()) {
      String requestUri = urlPathHelper.getRequestUri(request);
      pageNotFoundLogger.warn("No mapping found for HTTP request with URI [" + requestUri
          + "] in DispatcherServlet with name '" + getServletName() + "'");
    }
    response.sendRedirect(request.getContextPath() + fileNotFondUrl);
  }

  public String getFileNotFondUrl() {
    return fileNotFondUrl;
  }

  public void setFileNotFondUrl(String fileNotFondUrl) {
    this.fileNotFondUrl = fileNotFondUrl;
  }
}
