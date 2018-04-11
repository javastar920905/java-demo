package com.javastar920905.spider.config.inteceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.javastar920905.constant.CommonConstants;


/**
 *
 * @author ouzhx
 */

public class AdditionalCookiesInterceptor extends HandlerInterceptorAdapter {

  /**
   * 覆盖spring session cookie 设置
   */
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    HttpSession session = request.getSession(false);
    if (session != null && session.getAttribute("user") == null) {
      // 用户没有登录时,cookie 设置httponly=false
      setCookie(request, response, false);
    }
    /*
     * else { // 已登录就覆盖cookie 状态,httponly =true --测试不通过没意义 setCookie(request, response, true); }
     */
    super.afterCompletion(request, response, handler, ex);
  }

  private void setCookie(HttpServletRequest request, HttpServletResponse response,
      boolean httponly) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (CommonConstants.SSOSESSION.equals(cookie.getName())
            && cookie.isHttpOnly() != httponly) {
          Cookie rcjSessionCookie = new Cookie(CommonConstants.SSOSESSION, cookie.getValue());
          rcjSessionCookie.setPath(cookie.getPath());
          if (cookie.getDomain() != null) {
            rcjSessionCookie.setDomain(cookie.getDomain());
          }
          rcjSessionCookie.setHttpOnly(httponly);
          response.addCookie(rcjSessionCookie);
          return;
        }
      }
    }
  }
}
