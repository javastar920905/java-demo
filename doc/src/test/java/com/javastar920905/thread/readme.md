#说明:
    参考文档,代码的示例直接参考和照搬源文
    http://www.cnblogs.com/xrq730/category/733883.html
    不愿意重复造轮子,也怕自己理解不到位造成误解,尽量尝试理解原作者说的各个点和结论
    
# 示例代码入口 ThreadMainTest  
    每个测试用例,都标注了测试目的,以及测试结论,请注意留意 标记//TODO的是个人目前没理解和完善的地方
    由于类文件太多注释,导致比较臃肿,可以借助Structure(outline)视图,辅助阅读
# 1 线程概念回顾
## 进程与线程 (线程具备无效性,等待cpu资源)
## 线程实现方式
## 线程的实例方法 (join())
## 线程的静态方法 currentThread() sleep()
## 线程synchronized同步  对象锁
## synchronized锁定类方法、volatile关键字及其他
## 死锁
## wait()和notify()/notifyAll()
## ThreadLocal源码剖析
## ReentrantLock的使用和Condition
## 读写锁和两种同步方式的对比
## 生产者/消费者模型  http://www.cnblogs.com/xrq730/p/4855663.html
## Queue、BlockingQueue以及利用BlockingQueue实现生产者/消费者模型  http://www.cnblogs.com/xrq730/p/4855857.html
    BlockingQueue       阻塞队列所谓的"阻塞"，指的是某些情况下线程会挂起（即阻塞），一旦条件满足，被挂起的线程又会自动唤醒。
    ArrayBlockingQueue  基于数组的阻塞队列，必须指定队列大小;如果不指定大小的话就用Integer.MAX_VALUE，那将造成大量的空间浪费
    LinkedBlockingQueue 如果不指定队列容量大小，会默认一个类似无限大小的容量，之所以说是类似是因为这个无限大小是Integer.MAX_VALUE
    SynchronousQueue
    
    Queue是什么
    队列，是一种数据结构。除了优先级队列和LIFO队列外，队列都是以FIFO（先进先出）的方式对各个元素进行排序的。
    无论使用哪种排序方式，队列的头都是调用remove()或poll()移除元素的。在FIFO队列中，所有新元素都插入队列的末尾。
    
    Queue中的方法  http://www.cnblogs.com/xrq730/p/4855857.html    
    Queue中的方法不难理解，6个，每2对是一个也就是总共3对。看一下JDK API就知道了：
    
    
    
    注意一点就好，Queue通常不允许插入Null，尽管某些实现（比如LinkedList）是允许的，但是也不建议。
## 线程池  http://www.cnblogs.com/xrq730/p/4856453.html
    线程池的作用就2个：
    
    1、减少了创建和销毁线程的次数，每个工作线程都可以被重复利用，可执行多个任务
    
    2、可以根据系统的承受能力，调整线程池中工作线程的数据，防止因为消耗过多的内存导致服务器崩溃
    使用线程池，要根据系统的环境情况，手动或自动设置线程数目。少了系统运行效率不高，多了系统拥挤、占用内存多。
    用线程池控制数量，其他线程排队等候。一个任务执行完毕，再从队列中取最前面的任务开始执行。
    若任务中没有等待任务，线程池这一资源处于等待。一个新任务需要运行，如果线程池中有等待的工作线程，就可以开始运行了，否则进入等待队列。


# 线程的使用



# 常见线程安全的实现类
查看相关jdk包 java.util.concurrent.atomic
AtomicInteger 

锁 ReentrantLock(一个可重入的互斥锁) 可重入(获取到对象锁后,可以多次调用其他同步方法)
代码看 ReentrantLockDemo 


多线程下的其他组件之CountDownLatch、Semaphore、Exchanger  http://www.cnblogs.com/xrq730/p/4869671.html
Callable、Future和FutureTask  http://www.cnblogs.com/xrq730/p/4872722.html
