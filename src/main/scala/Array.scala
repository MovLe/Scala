import scala.collection.mutable.ArrayBuffer

/**
 * @ClassName Array
 * @MethodDesc: Scala之数组
 * @Author Movle
 * @Date 5/11/20 2:05 下午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
object Array {

  def main(args: Array[String]): Unit = {
    //数组创建方式1
    val a = new Array[Int](10)
    //遍历数组
    for(s<-a)println(s)

    val b = new Array[String](5)
    for(s<-b)println(s)
    //var c = Array("Tom","Jack","May")
    //for(s<-c)println(s)
    //创建一个变长数组
    val d = ArrayBuffer[Int]()
    d+=1
    d+=2
    d+=3
    d+=(10,20,30)
    //变长数组转换为普通数组
    d.toArray
    //遍历数组
    d.foreach(println)

  }
}
