package spark.sqlshizhan

import java.util.Properties

import org.apache.spark.sql
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

/**
 * @ClassName Demo3
 * @MethodDesc: 将结果写入Mysql中
 * @Author Movle
 * @Date 5/18/20 6:50 下午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
object Demo3 {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local").appName("Save To Mysql").getOrCreate()

    val personRDD = spark.sparkContext.textFile("/users/macbook/TestInfo/student.txt").map(_.split("\t"))

    val schema = StructType(
      List(
        StructField("id",IntegerType),
        StructField("name",StringType),
        StructField("age",IntegerType)
      )
    )

    val rowRDD = personRDD.map(p=>Row(p(0).toInt,p(1).trim(),p(2).toInt))

    val personDataFrame = spark.createDataFrame(rowRDD,schema)

    personDataFrame.createOrReplaceTempView("t_person")

    val result = spark.sql("select * from t_person order by age desc")

    val props = new Properties()

    props.setProperty("user","root")

    props.setProperty("password","123456")

    result.write.mode("append").jdbc("jdbc:mysql://localhost:3306/company?serverTimezone=UTC&characterEncoding=utf-8","student",props)


    spark.stop()
  }
}
