/**
 *
 */
package com.javastar920905.outer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于解决nginx 反向代理获取不到ip的问题
 * 
 * @author ouzhx
 */
public class ClientIPUtils {
  private static final Logger LOGGER = LoggerFactory.getLogger(ClientIPUtils.class);
  private static final String UNKNOWN = "unknown";


  /**
   * 获取主机ip ouzhx 2016年9月7日
   *
   * @param request
   * @return
   */
  public static String getHost(HttpServletRequest request) {
    String ip = request.getHeader("host");
    boolean logFlag = LOGGER.isDebugEnabled();
    if (logFlag) {
      LOGGER.debug("host", ip);
    }
    if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getServerName();
      if (logFlag) {
        LOGGER.debug("serverName", ip);
      }
    }
    if (StringUtil.isNotBlank(ip)) {
      ip = ip.split(",")[0];
    }
    return ip;
  }

  /**
   * 在很多应用下都可能有需要将用户的真实IP记录下来，这时就要获得用户的真实IP地址，在JSP里，获取客户端的IP地
   * 址的方法是：request.getRemoteAddr()，这种方法在大部分情况下都是有效的。但是在通过了Apache,Squid等 反向代理软件就不能获取到客户端的真实IP地址了。
   * 但是在转发请求的HTTP头信息中，增加了X－FORWARDED－FOR信息。用以跟踪原有的客户端IP地址和原来客户端请求的服务器地址。
   *
   * @param request
   * @return
   */
  public static String getClientIp(HttpServletRequest request) {
    String ip = request.getHeader("x-forwarded-for");
    boolean logFlag = LOGGER.isDebugEnabled();
    if (logFlag) {
      LOGGER.debug("x-forwarded-for = {}", ip);
    }
    if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
      if (logFlag) {
        LOGGER.debug("Proxy-Client-IP = {}", ip);
      }
    }
    if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
      if (logFlag) {
        LOGGER.debug("WL-Proxy-Client-IP = {}", ip);
      }
    }
    if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
      if (logFlag) {
        LOGGER.debug("RemoteAddr-IP = {}", ip);
      }
    }
    if (StringUtil.isNotBlank(ip)) {
      ip = ip.split(",")[0];
    }
    return ip;
  }
}
