package com.javastar920905.oss.alibaba;



import java.util.ArrayList;
import java.util.List;

import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;

import static com.javastar920905.oss.alibaba.AliOSSConstants.bucketName;


/**
 * @author ouzhx on 2018/6/5.
 * <p>
 * 阿里云OSS 文件管理工具类
 */
public class AliOSSFileManageUtils {



    /**
     * 列举指定个数的文件
     * <p>
     * https://help.aliyun.com/document_detail/32015.html?spm=a2c4g.11186623.6.683.FM8JCG
     *
     * @param maxKeys (限定返回文件的最大个数,默认返回100个)
     */
    public static List<String> listFiles(String keyPrefix, int maxKeys) {
        // 列举Object。
        ObjectListing objectListing = AliOSSConstants.ossClient.listObjects(
            new ListObjectsRequest(bucketName).withPrefix(keyPrefix).withMaxKeys(maxKeys));
        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
        List<String> files = new ArrayList<>();
        sums.stream().forEach(s -> {
            if (isValidFileName(s.getKey())) {
                files.add(s.getKey());
            }
        });
        return files;
    }

    /**
     * 分页列举指定个数的文件
     * <p>
     * https://help.aliyun.com/document_detail/32015.html?spm=a2c4g.11186623.6.683.FM8JCG
     *
     * @param
     */
    public static List<String> listFiles(String keyPrefix, String nextMarker) {
        // 每页默认大小
        final int maxKeys = 100;
        ObjectListing objectListing;
        List<String> files = new ArrayList<>();
        do {
            objectListing = AliOSSConstants.ossClient.listObjects(
                new ListObjectsRequest(bucketName).withPrefix(keyPrefix).withMarker(nextMarker)
                    .withMaxKeys(maxKeys));
            List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
            sums.stream().forEach(s -> {
                if (isValidFileName(s.getKey())) {
                    files.add(s.getKey());
                }
            });
            nextMarker = objectListing.getNextMarker();
        } while (objectListing.isTruncated());
        return files;
    }

    /**
     * 判断是文件名还是路径
     *
     * @return
     */
    private static boolean isValidFileName(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex <= 0) {
            return false;
        } else {
            return fileName.substring(dotIndex).length() < 1 ? false : true;
        }

    }
}
