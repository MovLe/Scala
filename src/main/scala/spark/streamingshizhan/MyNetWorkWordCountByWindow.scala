package spark.streamingshizhan

import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.storage.StorageLevel
/**
 * @ClassName MyNetWorkWordCountByWindow
 * @MethodDesc: TODO MyNetWorkWordCountByWindow功能介绍
 * @Author Movle
 * @Date 5/18/20 11:49 下午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
object MyNetWorkWordCountByWindow {

  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "/Users/macbook/Documents/hadoop/hadoop-2.8.4")
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

    val conf = new SparkConf().setAppName("MyNetWorkWordCountByWindow").setMaster("local[2]")

    val ssc = new StreamingContext(conf,Seconds(1))

    val lines = ssc.socketTextStream("192.168.31.132", 1235, StorageLevel.MEMORY_ONLY)

    val words = lines.flatMap(_.split(" ")).map((_,1))

    /**
     * reduce By Key And Window
     * 三个参数
     * 1、要进行什么操作
     * 2、窗口的大小
     * 3、窗口滑动的距离
     */

    val result = words.reduceByKeyAndWindow((x:Int,y:Int)=>(x+y),Seconds(30),Seconds(10))

    result.print()

    ssc.start()
    ssc.awaitTermination()
  }
}
