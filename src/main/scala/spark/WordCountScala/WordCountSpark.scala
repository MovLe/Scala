package spark.WordCountScala

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @ClassName WordCountSpark
 * @MethodDesc: TODO WordCountSpark功能介绍
 * @Author Movle
 * @Date 5/17/20 10:59 上午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
object WordCountSpark {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Spark WordCount JiQun")

    val sc = new SparkContext(conf)

    val result = sc.textFile(args(0))
      .flatMap(_.split(" "))
      .map((_,1))
      .reduceByKey(_+_)
        .saveAsTextFile(args(1))

    sc.stop()

  }


}
