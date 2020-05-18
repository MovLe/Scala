package spark.streamingshizhan

import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.storage.StorageLevel
import org.apache.spark.sql.SparkSession

/**
 * @ClassName MyNetwordWordCountWithSQL
 * @MethodDesc: 集成Spark SQL，使用SQL语句进行WordCount
 * @Author Movle
 * @Date 5/18/20 11:52 下午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
object MyNetwordWordCountWithSQL {

  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "/Users/macbook/Documents/hadoop/hadoop-2.8.4")
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

    val conf = new SparkConf().setMaster("local[2]").setAppName("MyNetwordWordCountWithSQL")

    val ssc = new StreamingContext(conf,Seconds(5))

    val lines = ssc.socketTextStream("192.168.31.132",1235,StorageLevel.MEMORY_ONLY)

    val words = lines.flatMap(_.split(" "))

    //集成Spark SQL 使用SQL语句进行WordCount
    words.foreachRDD( rdd => {

      val spark = SparkSession.builder().config(rdd.sparkContext.getConf).getOrCreate()

      import spark.implicits._
      val df1 = rdd.toDF("word")

      df1.createOrReplaceTempView("words")

      spark.sql("select word , count(1) from words group by word").show()
    })

    ssc.start()
    ssc.awaitTermination()
  }

}
