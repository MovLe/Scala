package spark.WordCountScala

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @ClassName WordCount
 * @MethodDesc: 本地模式scala编写WordCount
 * @Author Movle
 * @Date 5/17/20 10:21 上午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
object WordCount {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("My Scala Word Count").setMaster("local")

    val sc = new SparkContext(conf)

    val result = sc.textFile("/users/macbook/TestInfo/scala_student.txt")
      .flatMap(_.split(" "))
      .map((_,1))
      .reduceByKey(_+_)

    result.foreach(println)

    sc.stop()
  }
}
