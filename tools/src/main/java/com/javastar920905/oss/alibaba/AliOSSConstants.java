package com.javastar920905.oss.alibaba;

import com.aliyun.oss.OSSClient;

/**
 * @author ouzhx on 2018/6/5.
 *
 *         阿里云OSS
 * 
 *         https://help.aliyun.com/document_detail/31883.html?spm=5176.8465980.home.d1.4e701450Rd2qiJ
 *
 *         基本概念介绍 https://help.aliyun.com/document_detail/31827.html?spm=a2c4g.11186623.2.13.x6jY7E
 *         存储空间（Bucket） 对象/文件（Object）
 *
 *         java-sdk
 *         https://help.aliyun.com/document_detail/32009.html?spm=a2c4g.11186623.6.677.pmjxAi
 */
public class AliOSSConstants {
  /** endpoint以杭州为例，其它region请按实际情况填写。 **/
  private static final String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
  /**
   * 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com
   * 创建RAM账号。
   **/
  private static final String accessKeyId = "<yourAccessKeyId>";
  private static final String accessKeySecret = "<yourAccessKeySecret>";
  /** pubulic 可被修改 **/
  public static String bucketName = "";

  public static final OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
}
