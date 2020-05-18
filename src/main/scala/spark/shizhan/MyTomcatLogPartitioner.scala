package spark.shizhan

import org.apache.spark.{Partitioner, SparkConf, SparkContext}

import scala.collection.mutable

/**
 * @ClassName MyTomcatLogPartitioner
 * @MethodDesc: 解析Tomcat日志，并创建自定义分区
 * @Author Movle
 * @Date 5/17/20 1:24 下午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
object MyTomcatLogPartitioner {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("My Tomcat Log Partitioner")

    val sc = new SparkContext(conf)


    val rdd1 = sc.textFile("/users/macbook/TestInfo/localhost_access_log.txt")
      .map(
        line=>{
          val index1 = line.indexOf("\"")
          val index2 = line.lastIndexOf("\"")
          val line1=line.substring(index1+1,index2)

          val index3 = line1.indexOf(" ")
          val index4 = line1.lastIndexOf(" ")
          val line2 = line1.substring(index3+1,index4)

          val jspName = line2.substring(line2.lastIndexOf("/")+1)

          (jspName,line)
        }
      )

    val rdd2 = rdd1.map(_._1).distinct().collect()
    val myPartitioner = new MyWebPatitioner(rdd2)

    val rdd3 = rdd1.partitionBy(myPartitioner)

    rdd3.saveAsTextFile("/users/macbook/TestInfo/0518/test_partition")

    sc.stop()
  }
}

class MyWebPatitioner(jspList: Array[String]) extends Partitioner{
  val partitionMap = new mutable.HashMap[String,Int]()

  var partId = 0

  for(jsp <-jspList){
    partitionMap.put(jsp,partId)
    partId += 1
  }

  def numPartitions: Int = partitionMap.size

  def getPartition(key: Any): Int = partitionMap.getOrElse(key.toString,0)
}
