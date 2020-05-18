package spark.sqlshizhan

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

/**
 * @ClassName Demo1
 * @MethodDesc: 创建DataFrame执行sql
 * @Author Movle
 * @Date 5/18/20 6:16 下午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
object Demo1 {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local").appName("UnderstandSparkSession").getOrCreate()

    val personRDD = spark.sparkContext.textFile("/users/macbook/TestInfo/student.txt").map(_.split("\t"))


    val schema = StructType(
      List(
        StructField("id",IntegerType),
        StructField("name",StringType),
        StructField("age",IntegerType)
      )
    )

    val rowRDD = personRDD.map(p => Row(p(0).toInt,p(1).trim(),p(2).toInt))

    val personDataFrame = spark.createDataFrame(rowRDD,schema)

    personDataFrame.createOrReplaceTempView("t_person")

    val df = spark.sql("select * from t_person order by age desc")

    df.show()

    spark.stop()

  }

}
