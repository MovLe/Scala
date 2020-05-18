package spark.streamingshizhan

import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.flume.FlumeUtils
/**
 * @ClassName MyFlumeStreamPush
 * @MethodDesc: TODO MyFlumeStreamPush功能介绍
 * @Author Movle
 * @Date 5/19/20 12:08 上午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
object MyFlumeStreamPush {
  def main(args: Array[String]): Unit = {

    System.setProperty("hadoop.home.dir", "/Users/macbook/Documents/hadoop/hadoop-2.8.4")
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

    val conf = new SparkConf().setAppName("MyFlumeStream").setMaster("local[2]")

    val ssc = new StreamingContext(conf,Seconds(3))

    //创建 flume event 从 flume中接收push来的数据 ---> 也是DStream
    //flume将数据push到了 ip 和 端口中
    val flumeEventDstream = FlumeUtils.createStream(ssc, "192.168.31.211", 1236)

    val lineDStream = flumeEventDstream.map( e => {
      new String(e.event.getBody.array)
    })

    lineDStream.print()

    ssc.start()
    ssc.awaitTermination()
  }

}
