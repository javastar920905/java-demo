package com.javastar920905.oss.alibaba;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;

/**
 * @author ouzhx on 2018/6/5.
 * <p>
 * 阿里云OSS
 * <p>
 * https://help.aliyun.com/document_detail/31883.html?spm=5176.8465980.home.d1.4e701450Rd2qiJ
 * <p>
 * 基本概念介绍 https://help.aliyun.com/document_detail/31827.html?spm=a2c4g.11186623.2.13.x6jY7E
 * 存储空间（Bucket） 对象/文件（Object）
 * <p>
 * java-sdk
 * https://help.aliyun.com/document_detail/32009.html?spm=a2c4g.11186623.6.677.pmjxAi
 */
public class AliOSSConstants {
    /**
     * endpoint以杭州为例，其它region请按实际情况填写  在控制台bucket 下可以查看EndPoint。
     **/
    private static final String endpoint = "oss-cn-beijing.aliyuncs.com";
    /**
     * 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com
     * 创建RAM账号。
     **/
    private static final String accessKeyId = "LTAIiQCoHPH2rCUP";
    private static final String accessKeySecret = "5Ith0Izwj7QfbRUwwDj7lEIhTsUaWa";
    /**
     * pubulic 可被修改
     **/
    public static String bucketName = "integratedwall";

    public static final OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);


    public enum IntegratedWallKeyPath {
        sea("3DSea", "3D海洋"), EuropeanSimple("EuropeanSimple", "欧式简约"), bigStone("bigStone",
            "大理石纹"), capitalDraw("capitalDraw", "国画绘画"), cartoon("cartoon", "儿童卡通"), mountains(
            "mountains", "山水竹柳"), chineseStyle("chineseStyle", "新中式系列"), scene("scene",
            "风景系列"), workDecrator("workDecrator", "工装系列");

        private String path;
        private String cheseName;

        private static final JSONArray array = new JSONArray();
        private static final String IntegratedWallKey_prefix = "books/";

        IntegratedWallKeyPath(String path, String cheseName) {
            this.path = path;
            this.cheseName = cheseName;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getCheseName() {
            return cheseName;
        }

        public void setCheseName(String cheseName) {
            this.cheseName = cheseName;
        }

        static {
            IntegratedWallKeyPath[] keyPaths = IntegratedWallKeyPath.values();
            for (IntegratedWallKeyPath path : keyPaths) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("key", path.name());
                jsonObject.put("path", IntegratedWallKey_prefix + path.getPath());
                jsonObject.put("chineseName", path.getCheseName());
                array.add(jsonObject);
            }
        }

        public static JSONArray getCategoryList() {
            return array;
        }
    }
}
