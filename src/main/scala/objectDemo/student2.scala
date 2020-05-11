package objectDemo

import scala.collection.mutable.ArrayBuffer

/**
 * @ClassName student2
 * @MethodDesc: scala之内部类(嵌套类)
 * @Author Movle
 * @Date 5/11/20 7:00 下午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
class Student2 {
  class Course(val courseName:String,val credit:Int){

  }
  private var stuName:String = "Tom"
  private var stuAge:Int=20

  private var courseList = new ArrayBuffer[Course]()

  def addNewCourse(cname:String,credit:Int): Unit ={
    var c = new Course(cname,credit)

    courseList +=c
  }

}

object Student2{
  def main(args: Array[String]): Unit = {
    var s2 = new Student2

    s2.addNewCourse("Chinese",2)
    s2.addNewCourse("English",3)
    s2.addNewCourse("Math",3)

    println(s2.stuName+"\t"+s2.stuAge)
    println("------选修课程---------")

    for(s <- s2.courseList)println(s.courseName+"\t"+s.credit)
  }
}