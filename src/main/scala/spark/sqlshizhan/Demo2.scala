package spark.sqlshizhan

import org.apache.spark.sql.SparkSession

/**
 * @ClassName Demo2
 * @MethodDesc: 使用case class 创建DataFrame
 * @Author Movle
 * @Date 5/18/20 6:34 下午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
object Demo2 {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local").appName("CaseClassDemo").getOrCreate()

    val lineRDD = spark.sparkContext.textFile("/users/macbook/TestInfo/student.txt").map(_.split("\t"))

    val studentRDD = lineRDD.map(x =>Student(x(0).toInt,x(1),x(2).toInt))

    import spark.sqlContext.implicits._

    val studentDF = studentRDD.toDF()

    studentDF.createOrReplaceTempView("student")

    spark.sql("select * from student").show

    spark.stop()

  }
}
case class Student(stuId:Int,stuName:String,stuAge:Int)
