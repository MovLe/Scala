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
    var words = scala.io.Source.fromFile("/users/macbook/TestInfo/student.txt").mkString

    println(words)
  }

}
