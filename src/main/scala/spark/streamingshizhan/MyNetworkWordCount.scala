package spark.streamingshizhan

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @ClassName MyNetworkWordCount
 * @MethodDesc: 通过spark streaming统计端口号1234中的信息
 * @Author Movle
 * @Date 5/18/20 11:09 下午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
object MyNetworkWordCount {

  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir","/Users/macbook/Documents/hadoop/hadoop-2.8.4")
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

    val conf = new SparkConf().setAppName("MyNetworkWordCount").setMaster("local[2]")

    val ssc = new StreamingContext(conf,Seconds(3))

    val lines = ssc.socketTextStream("192.168.31.132",1234,StorageLevel.MEMORY_ONLY)

    val words = lines.flatMap(_.split(" "))

    val wordCount = words.map((_,1)).reduceByKey(_+_)

    wordCount.print()

    ssc.start()

    ssc.awaitTermination()

  }
}
