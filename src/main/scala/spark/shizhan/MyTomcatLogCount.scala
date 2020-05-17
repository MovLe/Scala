package spark.shizhan

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @ClassName MyTomcatLogCount
 * @MethodDesc: 解析Tomcat日志，找到访问量最高的两个jsp
 * @Author Movle
 * @Date 5/17/20 11:45 上午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
object MyTomcatLogCount {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("My Tomcat Log Count").setMaster("local")

    val sc = new SparkContext(conf)

    val rdd1 = sc.textFile("/users/macbook/TestInfo/localhost_access_log.txt")
      .map(
        line =>{
          val index1 = line.indexOf("\"")
          val index2 = line.lastIndexOf("\"")
          val line1=line.substring(index1+1,index2)

          val index3 = line1.indexOf(" ")
          val index4 = line1.lastIndexOf(" ")
          val line2 = line1.substring(index3+1,index4)

          val jspName = line2.substring(line2.lastIndexOf("/")+1)

          (jspName,1)
        }
      )

    val rdd2=rdd1.reduceByKey(_+_)

    val rdd3=rdd2.sortBy(_._2,false)

    rdd3.take(2).foreach(println)

    sc.stop()
  }

}
