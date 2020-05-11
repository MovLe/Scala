package base

/**
 * @ClassName For4
 * @MethodDesc: for循环的四种写法
 * @Author Movle
 * @Date 5/11/20 10:42 上午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
object For4 {

  def main(args: Array[String]): Unit = {

    var list = List("LiMing","May","Mike")

    println("for循环的第一种写法")
    for(s<-list) println(s)

    println("for循环的第二种写法")
    for{
      s <-list
      if(s.length >3)
    }println(s)

    println("for循环的第三种写法")
    for(s <- list if s.length < 3) println(s)

    println("for循环的第四种写法")
    var newList = for{
      s <- list
      s1=s.toUpperCase()
    }yield(s1)
    for(s1<-newList) println(s1)

  }
}
