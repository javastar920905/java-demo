# freemarker 介绍
Apache FreeMarker™是一个模板引擎：一个Java库，用于根据模板和更改数据生成文本输出（HTML网页，电子邮件，配置文件，源代码等）

# 参考文档
    [我的有道云笔记总结](http://note.youdao.com/noteshare?id=26eb45d68e546062f0cfc663ba41d63b)
    [官方文档](https://freemarker.apache.org/docs/dgui_quickstart_basics.html)
    [spring boot 2.1.3 参考文档](https://docs.spring.io/spring-boot/docs/2.1.3.RELEASE/reference/htmlsingle/)
    [spring mvc 之模板引擎配置](https://docs.spring.io/spring-boot/docs/2.1.3.RELEASE/reference/htmlsingle/#boot-features-spring-mvc-template-engines)

## 添加生成简历示例
     https://blog.csdn.net/jackfrued/article/details/39449021
     1 将word 文档保存为xml格式
        打开word文档 -->文件-->另存为-->保存类型为xml
     2 将xml作为模板,生成新的xml文件,双击会直接调用word文档打开
     3 打开xml文件,文件另存为docx文件
     
 ## 生成word文档的解决方案
    包括使用Jacob、Apache POI、Java2Word、iText
    [itext教程文档](https://itextpdf.com/en/products/itext-7/itext-7-core)
    [博客教程](https://blog.csdn.net/u012397189/article/details/80196974)
    [博客教程](http://www.cuteke.cn/blogs)
    [itext 中文社区](https://github.com/iTextCN)
    [itext汇总](https://www.cnblogs.com/xiaoSY-learning/p/5805577.html)
    
    为啥iText这么强大，这么优秀，为什么用的人这么少呢？或者没有大力推广呢？其实主要是碍于它的许可协议APGL，这个什么许可协议呢？我来总结一下：
    
    如果你项目用到了iText系列产品，而且你把你的项目用于商业用途，那么你就需要向iText公司申请授权费了。
    除了上述情况，那么都是开源的，只要是个人用途或者源码公开项目，那么你都可以免费试用iText产品
   