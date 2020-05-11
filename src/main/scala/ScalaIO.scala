import java.io.{File, FileInputStream, PrintWriter}

import scala.io.Source.fromFile
import scala.io.Source.fromURL
/**
 * @ClassName LazyObject
 * @MethodDesc: ScalaIO
 * @Author Movle
 * @Date 5/11/20 2:00 下午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
object ScalaIO {

  def main(args: Array[String]): Unit = {

    val source1 = fromFile("/users/macbook/TestInfo/student.txt")

    //读取整个文件
    println("--------mkString----------")
    println(source1.mkString)

    val source2=fromFile("/users/macbook/TestInfo/student.txt")
    //按行读取
    println("----------lines----------------")
    val lines = source2.getLines()
    lines.foreach(println)

    //读取二进制文件
    println("--------Read Bytes---------")
    var file = new File("/users/macbook/TestInfo/hudson.war")
    //构建一个inputstream
    var in = new FileInputStream(file)
    //构造buffer
    var buffer = new Array[Byte](file.length().toInt)
    //读取
    in.read(buffer)
    println(buffer.length)
    //关闭
    in.close()

    //从URL中获取信息
    println("--------fromURL---------")
    var source3 = fromURL("https://www.baidu.com","UTF-8")
    println(source3.mkString)

    //写入文件
    println("--------Write File---------")
    var out = new PrintWriter("/users/macbook/TestInfo/insert_0601.txt")
    for (i <- 0 until 10) out.println(i)
    out.close()
  }

}
