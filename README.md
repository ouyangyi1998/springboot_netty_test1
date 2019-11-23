#  springboot+netty对于拆包粘包的控制
- netty实现心跳tcp看之前的文章,这里重点讲一下netty粘包和拆包的解决方案　
- 一次传送过多数据会出现拆包的现象，多次发送较少内容，会发生粘包现象。
- 解决粘包
  - 在Server服务端使用定长数据帧的解码器 FixedLengthFrameDecoder之后。
  - 可以明显看到数据已经按照我们所设定的大小分割了。
- 解决拆包
  - 在Server服务端使用字节解码器 LineBasedFrameDecoder 之后。
  - 通过设置最大字节码来解决拆包的问题（不能设置太大，不然会翻车）
- 还可以通过HttpObjectAggregator 来解决粘包拆包的问题,ph.addLast("aggregator",new HttpObjectAggregator(10*1024*1024));
    - 如果对于单条HTTP消息你不想处理多个消息对象，你可以传入 HttpObjectAggregator 到pipline中。
    - HttpObjectAggregator 会将多个消息对象转变为单个 FullHttpRequest 或者 FullHttpResponse。
    - 但是依然有拆包现象，效果一般。。。
 - 就酱。。。

