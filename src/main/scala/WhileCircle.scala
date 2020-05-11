/**
 * @ClassName WhileCircle
 * @MethodDesc: while循环与dowhile循环还有foreach迭代
 * @Author Movle
 * @Date 5/11/20 11:05 上午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
object WhileCircle {

  /**
   * while循环与dowhile循环还有foreach迭代
   * @param args
   */
  def main(args: Array[String]): Unit = {
    var list = List("LiMing","May","Mike")

    var i=0
    println("******while循环******")
    while(i<list.length){
      println(list(i))
      i+=1
    }

    println("******dowhile循环******")
    var j=0
    do{
      println(list(j))
      j+=1
    }while(j<list.length)

    println("******foreach******")
    list.foreach(println)
  }

}

