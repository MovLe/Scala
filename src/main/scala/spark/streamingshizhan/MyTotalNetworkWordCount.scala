package spark.streamingshizhan
import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.storage.StorageLevel
/**
 * @ClassName MyTotalNetworkWordCount
 * @MethodDesc: TODO MyTotalNetworkWordCount功能介绍
 * @Author Movle
 * @Date 5/18/20 11:34 下午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
object MyTotalNetworkWordCount {

  def main(args: Array[String]): Unit = {

    System.setProperty("hadoop.home.dir", "/Users/macbook/Documents/hadoop/hadoop-2.8.4")
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

    val conf = new SparkConf().setMaster("local[2]").setAppName("MyTotalNetworkWordCount")

    val ssc = new StreamingContext(conf,Seconds(3))

    //设置检查点目录，保存之前的状态信息
    ssc.checkpoint("hdfs://192.168.31.132:9000/TestFile/chkp0518")

    val lines = ssc.socketTextStream("192.168.31.132", 1235, StorageLevel.MEMORY_ONLY)

    val words = lines.flatMap(_.split(" "))

    val wordPair = words.map((_,1))

    /**
     * 两个参数：
     * 第一个参数：当前的值是多少
     * 第二个参数：之前的结果是多少
     */
    val addFunc = (curreValues:Seq[Int],previousValues:Option[Int]) => {
      //进行累加运算
      // 1、把当前值的序列进行累加
      val currentTotal = curreValues.sum

      //2、在之前的值上再累加

      Some( currentTotal + previousValues.getOrElse(0) )

    }

    //进行累加运算
    val total = wordPair.updateStateByKey(addFunc)

    total.print()

    ssc.start()
    ssc.awaitTermination()

  }
}
