package spark.streamingshizhan

import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.flume.FlumeUtils
import org.apache.spark.storage.StorageLevel
/**
 * @ClassName FlumeLogPull
 * @MethodDesc: TODO FlumeLogPull功能介绍
 * @Author Movle
 * @Date 5/19/20 12:34 上午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
object FlumeLogPull {
  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "/Users/macbook/Documents/hadoop/hadoop-2.8.4")
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

    val conf = new SparkConf().setAppName("FlumeLogPull").setMaster("local[2]")
    val ssc = new StreamingContext(conf,Seconds(1))

    val flumeEvent = FlumeUtils.createPollingStream(ssc, "192.168.31.132", 1234,StorageLevel.MEMORY_ONLY_SER)

    val lineDStream = flumeEvent.map( e => {
      new String(e.event.getBody.array)
    })

    lineDStream.print()

    ssc.start()
    ssc.awaitTermination()
  }

}
