package com.javastar920905.oss.alibaba;


import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.StorageClass;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static com.javastar920905.oss.alibaba.AliOSSConstants.bucketName;

/**
 * @author ouzhx on 2018/6/5.
 *
 *         阿里云OSS 文件上传工具类
 * 
 */
public class AliOSSUploadUtils {

  /**
   * 创建bucket
   *
   * https://help.aliyun.com/document_detail/32012.html?spm=a2c4g.11186623.6.680.0SbUDO
   *
   * @param bucketName 创建一个存储空间
   */
  public static void createBucket(String bucketName, boolean ispublic) {
    if (!AliOSSConstants.ossClient.doesBucketExist(bucketName)) {
      if (ispublic) {
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
        // 设置Bucket权限为公共读，默认是私有读写。
        createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
        // 设置Bucket存储类型为标准类型，默认是标准类型。
        createBucketRequest.setStorageClass(StorageClass.Standard);
        AliOSSConstants.ossClient.createBucket(createBucketRequest);
      } else {
        // 新建Bucket权限默认是私有读写，标准存储类型。
        AliOSSConstants.ossClient.createBucket(bucketName);
      }
      // 关闭Client。
      AliOSSConstants.ossClient.shutdown();

    }
  }

  /**
   * 上传byte 数组
   * 
   * https://help.aliyun.com/document_detail/32013.html?spm=a2c4g.11186623.6.681.OESc5y
   * 
   * @param bytes (byte[] content = "Hello OSS".getBytes();)
   */
  public static void uploadFile(byte[] bytes) {
    AliOSSConstants.ossClient.putObject(bucketName, "", new ByteArrayInputStream(bytes));
    // 关闭Client。
    AliOSSConstants.ossClient.shutdown();
  }

  /**
   * // 上传网络流。
   *
   * https://help.aliyun.com/document_detail/32013.html?spm=a2c4g.11186623.6.681.OESc5y
   *
   * @param inputStream (InputStream inputStream = new URL("https://www.aliyun.com/").openStream())
   */
  public static void uploadFile(InputStream inputStream) {
    AliOSSConstants.ossClient.putObject(bucketName, "", inputStream);
    // 关闭Client。
    AliOSSConstants.ossClient.shutdown();
  }

  /**
   * // 上传网络流。
   *
   * https://help.aliyun.com/document_detail/32013.html?spm=a2c4g.11186623.6.681.OESc5y
   *
   * @param inputStream (InputStream inputStream = new FileInputStream("<yourlocalFile>");)
   */
  public static void uploadFile(FileInputStream inputStream) {
    AliOSSConstants.ossClient.putObject(bucketName, "", inputStream);
    // 关闭Client。
    AliOSSConstants.ossClient.shutdown();
  }

  /**
   * // 文件上传
   *
   * https://help.aliyun.com/document_detail/32013.html?spm=a2c4g.11186623.6.681.OESc5y
   *
   * @param localFile (new File("<yourLocalFile>"));)
   */
  public static void uploadFile(File localFile) {
    AliOSSConstants.ossClient.putObject(bucketName, "", localFile);
    // 关闭Client。
    AliOSSConstants.ossClient.shutdown();
  }
}
