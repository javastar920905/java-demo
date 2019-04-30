# 验证码识别demo

## 一 项目启动说明
* 启动 CodeIdentificationApplication
* 使用postman 导入 spider\impl\selenium\ticket-spider.postman_collection.json 进行单元测试
* 可测试接口 SeleniumSpiderController
* 如果需要html转pdf功能 需要本地安装 wkhtmltopdf (同时修改值: ChinaTaxDriver.wkhtmltopdfPath)
* [更换IEDriver ](https://share.weiyun.com/5SDdsAZ) 如果链接不可用> 访问javabus.cn>腾讯微云-常用开发工具备份>iedriver
* IEDriver使用32位的,当前IEDriverServer_Win32_3.8.0

###  selenium IE踩坑总结
* 1 Browser zoom level was set to 150%. It should be set to 100%
   * 解决: IE 设置,缩放比例设置为100%即可
* 2 浏览器->安全选项->4个设置需要保持一致,要么全部关闭"启用保护模式",要么全部开启
* 3 提示https安全性问题:
   * 解决: 安装证书
   * 解决2: 关闭https提示  https://www.cnblogs.com/leeboke/p/8108680.html
* 4 IE webdriver sendkeys,执行缓慢问题
    * 解决: IEDriver 64位有bug,使用32位的
    
    
## 二、发票查验需求
（一）增值税发票查验
1、查验范围
        全国地区
2、数据来源
        查验数据取自全国增值税发票查询系统：https://inv-veri.chinatax.gov.cn/
3、可查票种及所需字段

