package spark.coreshizhan

import java.sql.DriverManager

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @ClassName MyJDBCRddDemo
 * @MethodDesc: 使用JDBC RDD操作数据库
 * @Author Movle
 * @Date 5/18/20 3:01 下午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
object MyJDBCRddDemo {

  val connection = ()=>{
    Class.forName("com.mysql.jdbc.Driver").newInstance()
    DriverManager.getConnection("jdbc:mysql://localhost:3306/company?serverTimezone=UTC&characterEncoding=utf-8", "root", "123456")
  }


  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("My JDBC RDD Demo").setMaster("local")

    val sc = new SparkContext(conf)

    val mysqlRDD = new JdbcRDD(sc,connection,"select * from emp where sal > ? and sal <= ?",900,2000,2,r=>{
      val ename = r.getString(2)
      val sal = r.getInt(4)
      (ename,sal)
    })

    val result = mysqlRDD.collect()

    println(result.toBuffer)

    sc.stop()
  }
}
