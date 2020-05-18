package spark.streamingshizhan

import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import scala.collection.mutable.Queue
import org.apache.spark.rdd.RDD
/**
 * @ClassName RDDQueueStream
 * @MethodDesc: TODO RDDQueueStream功能介绍
 * @Author Movle
 * @Date 5/19/20 12:05 上午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
object RDDQueueStream {

  def main(args: Array[String]): Unit = {

    System.setProperty("hadoop.home.dir", "/Users/macbook/Documents/hadoop/hadoop-2.8.4")
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

    val conf = new SparkConf().setAppName("RDDQueueStream").setMaster("local[2]")

    val ssc = new StreamingContext(conf,Seconds(1))

    //需要一个RDD队列
    val rddQueue = new Queue[RDD[Int]]()


    for( i <- 1 to 3){
      rddQueue += ssc.sparkContext.makeRDD(1 to 10)

      Thread.sleep(5000)
    }

    //从队列中接收数据 创建DStream
    val inputDStream = ssc.queueStream(rddQueue)

    val result = inputDStream.map(x=>(x,x*2))

    result.print()

    ssc.start()
    ssc.awaitTermination()

  }

}
