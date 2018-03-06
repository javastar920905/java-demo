# 项目结构介绍
## java-demo (是一个gradle 的多模块项目)
--
    doc (描述开发规范的项目)
    entity (实体类项目)
    html2pdf (html转pdf或图片的项目)
    pay (使用mybatis plus 开发的支付类项目)
    rule-engine (规则引擎demo 项目)
    tools   (工具类模块)
    web     (spring boot项目)
    web2-sso    (spring boot项目  使用spring session 实现和web项目统一分享session )
    xf-voice    (讯飞和百度语音合成demo )


java 8示例代码是写在test目录下的(为了方便单元测试)
https://github.com/javastar920905/java-demo/tree/master/src/test/java/com/javastar920905/javademo/java8/stream

参考文档 : http://url.cn/5MQWlIV (java8实战)
###流处理 (类似linux的管道"|")
第一个编程概念是流处理。介绍一下，流是一系列数据项，一次只生成一项。程序可以从输
入流中一个一个读取数据项，然后以同样的方式将数据项写入输出流。一个程序的输出流很可能
是另一个程序的输入流。

###用行为参数化把代码传递给方法


###并行与共享的可变数据
你的行为必须能够同时对不同的输入安全
地执行。一般情况下这就意味着，你写代码时不能访问共享的可变数据


###方法和 Lambda 作为一等公民 (方法引用 :: 语法)


###Lambda——匿名函数  (Lambda没有 return 语句，因为已经隐含了 return)
除了允许（命名）函数成为一等值外，Java 8还体现了更广义的将函数作为值的思想，包括
Lambda
① （或匿名函数）。比如，你现在可以写 (int x) -> x + 1 ，表示“调用时给定参数 x ，
就返回 x + 1值的函数”
####什么是lambda
oushuList.sort((n1, n2) -> n1.compareTo(n2));
lambda 构成    Lambda参数 -> Lambda主体
####Java 8中有效的Lambda表达式
(String s) -> s.length()           //表达式有一个 String 类型的参数  并返回一个 int 。
(Apple a) -> a.getWeight() > 150   //表达式有一个 Apple 类型的参数   并返回一个boolean
(int x, int y) -> {
System.out.println("Result:");
System.out.println(x+y);
}                                  //表达式有两个 int 类型的参数而  没有返回值（ void 返回）。注意Lambda 表达式可以包含多行语句
() -> 42                           //表达式       没有参数，       返回一个int
(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight())  
                                   //表达式有两个 Apple 类型的参数，返回一个 int ：比较两个 Apple 的重量

### 函数式接口中可以使用lambda 表达式 
####函数式接口是什么(函数式接口就是只定义1个抽象方法的接口)
####接口可以提供default 默认实现
不过慢着，一个类可以实现多个接口，不是吗？那么，如果在好几个接口里有多个默认实现，
是否意味着Java中有了某种形式的多重继承？是的，在某种程度上是这样。我们在第9章中会谈
到，Java 8用一些限制来避免出现类似于C++中臭名昭著的菱形继承问题。


### 利用策略模式实现 行为参数化
策略模式实现demo可以查看github 
https://github.com/javastar920905/design-pattern/tree/master/vip-lesson/src/main/java/com/javastar920905/strategy/evolution

--
行为参数化，就是一个方法接受多个不同的行为作为参数，并在内部使用它们，完成不
同行为的能力。
  行为参数化可让代码更好地适应不断变化的要求，减轻未来的工作量。
  传递代码，就是将新行为作为参数传递给方法。但在Java 8之前这实现起来很啰嗦。为接
口声明许多只用一次的实体类而造成的啰嗦代码，在Java 8之前可以用匿名类来减少。
  Java API包含很多可以用不同行为进行参数化的方法，包括排序、线程和GUI处理

###用 Runnable 执行代码块
Thread t = new Thread(() -> System.out.println("Hello world"));
t.start()
