package spark.sqlshizhan

import java.util.Properties

import org.apache.spark.sql.SparkSession

/**
 * @ClassName Demo4
 * @MethodDesc: 使用Spark SQL 连接Hive,将查询结果存储到mysql中
 * @Author Movle
 * @Date 5/18/20 7:21 下午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
object Demo4 {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("Hive2Mysql").enableHiveSupport().getOrCreate()

    val result = spark.sql("select deptno,mgr from default.emp")

    val props = new Properties()
    props.setProperty("user","root")
    props.setProperty("password","000000")

    result.write.mode("append").jdbc("jdbc:mysql://hadoop2:3306/company?serverTimezone=UTC&characterEncoding=utf-8","emp_stat3",props)

    spark.stop()
  }
}
