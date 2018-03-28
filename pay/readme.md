# 说明
支付类项目

# 使用redis 做秒杀实现
## 提示客户端使用的是RedissonClient 
api 使用演示 (可以实现数据类型的转换)
@Autowired
private RedissonClient redissonClient;

long size= redissonClient.getAtomicLong(String.format("redPacket:%s:size",redPacketId)).get()
int sizeInt=size.intValue();


## 使用spring RedisConnection api
判断队列中是否包含某元素
List<byte[]> listBytes = connection.lRange("Listkey".getBytes(), 0,-1);
  if (CollectionUtils.isNotEmpty(listBytes)
        && listBytes.contains(openId.getBytes())) {
        }
