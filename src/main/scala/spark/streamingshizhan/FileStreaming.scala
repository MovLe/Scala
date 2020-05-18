package spark.streamingshizhan

import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.storage.StorageLevel
/**
 * @ClassName FileStreaming
 * @MethodDesc: TODO FileStreaming功能介绍
 * @Author Movle
 * @Date 5/18/20 11:58 下午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
object FileStreaming {

  def main(args: Array[String]): Unit = {

    System.setProperty("hadoop.home.dir", "/Users/macbook/Documents/hadoop/hadoop-2.8.4")
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

    //local[2]代表开启两个线程
    val conf = new SparkConf().setAppName("MyNetwordWordCount").setMaster("local[2]")

    //接收两个参数，第一个conf，第二个是采样时间间隔
    val ssc = new StreamingContext(conf, Seconds(3))

    //监控目录 如果文件系统发生变化 就读取进来
    val lines = ssc.textFileStream("/Users/macbook/TestInfo/test_file_stream")

    lines.print()

    ssc.start()
    ssc.awaitTermination()
  }

}
