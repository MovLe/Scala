package spark.coreshizhan

import java.sql.{Connection, DriverManager, PreparedStatement}

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @ClassName MyTomcatLogCountToMysql
 * @MethodDesc: 将解析Tomcat日志的结果写入mysql数据库中
 * @Author Movle
 * @Date 5/18/20 2:41 下午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
object MyTomcatLogCountToMysql {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("My Tomcat Log Count To Mysql")

    val sc=new SparkContext(conf)

    val rdd1 = sc.textFile("/users/macbook/TestInfo/localhost_access_log.txt")
      .map(
        line => {
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

    rdd1.foreachPartition(saveToMysql)

    sc.stop()

  }
  def saveToMysql(iter:Iterator[(String,Int)])={
    var conn:Connection = null
    var pst:PreparedStatement = null

    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?serverTimezone=UTC&characterEncoding=utf-8", "root", "123456")
    pst = conn.prepareStatement("insert into mydata values(?,?)")

    iter.foreach(data=>{
      pst.setString(1,data._1)
      pst.setInt(2,data._2)
      pst.executeUpdate()

    })

  }
}
