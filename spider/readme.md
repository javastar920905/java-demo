# 说明
java api 实现爬虫
## 实现步骤
### 爬取思路

1 确定目标网站

2 分析确认待爬取的url 通过特征批量生成待爬取url

3 多线程爬取,添加到爬取队列

4 记录扒取结果

根据业务规则执行如下步骤

5 排重,入库

6 清洗转换,入库

爬虫优化,性能爬取速度优化

其他参考文档:

知乎 https://www.zhihu.com/question/30626103

手写简单爬虫框架示例: https://www.cnblogs.com/sanmubird/p/7857474.html

爬取中国最小粒度的区域维度信息   https://www.cnblogs.com/Jims2016/p/5877300.html


### 1 抓取页面
框架选择: 1 原生java net包,2 http client ,3 webmagic 等爬虫框架

抓取页面时,可能遇到的问题
(1编码,2代理ip,3需登录访问的页面(验证码,短信识别))

### 2 解析html dom
1 原生的String.indexOf()+substring()方法 

2 正则表达式,(正则语法博大精深,借助正则表达式测试工具 http://tool.oschina.net/regex/) 

    // 定义一个样式模板，此中使用正则表达式，括号中是要抓的内容  
    // 相当于埋好了陷阱匹配的地方就会掉下去  
    Pattern pattern = Pattern.compile("href=\"(.+?)\"");  
    // 定义一个matcher用来做匹配  
    Matcher matcher = pattern.matcher("＜a href=\"index.html\"＞我的主页＜/a＞");  
    // 如果找到了  
    if (matcher.find()) {   
        // 打印出结果   
        System.out.println(matcher.group(1));
    }

3 jsoup  https://jsoup.org/cookbook/


# 用到的java api 核心包
java.net 包,在线api文档 http://tool.oschina.net/apidocs/apidoc?api=jdk_7u4

我的总结文档 http://note.youdao.com/noteshare?id=263fa7ba55b3ea543f9f9cfd4d21ea76







# 科普java 包(package)
## 包的作用  http://www.runoob.com/java/java-package.html
为了更好的组织java api中的类,java提供了包机制,用于区别类名的命名空间

Java 使用包（package）这种机制是为了防止命名冲突，访问控制，提供搜索和定位类（class）、接口、枚举

    1 把相似功能的接口和类组织在同一个包中,方便类的查找和使用
    2、如同文件夹一样，包也采用了树形目录的存储方式。同一个包中的类名字是不同的，不同的包中的类的名字是可以相同的，当同时调用两个不同包中相同类名的类时，应该加上包名加以区别。因此，包可以避免名字冲突。
    3、包也限定了访问权限，拥有包访问权限的类才能访问某个包中的类。
## 包的使用
    包语句的语法格式为： package pkg1[．pkg2[．pkg3…]];
import 关键字

为了能够使用某一个包的成员，我们需要在 Java 程序中明确导入该包。使用 "import" 语句可完成此功能。

    在 java 源文件中 import 语句应位于 package 语句之后，所有类的定义之前，可以没有，也可以有多条，
    其语法格式为：import package1[.package2…].(classname|*);


