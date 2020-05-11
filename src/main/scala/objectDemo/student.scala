package objectDemo

/**
 * @ClassName student
 * @MethodDesc: scala 之面向对象的基本概念
 * @Author Movle
 * @Date 5/11/20 6:47 下午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
class Student {

  private var stuId:Int=0
  private var stuName:String="Tom"
  private var age:Int=20

  def getStuName():String =stuName

  def setStuName(newName:String)=this.stuName=newName

}

object Student {

  def main(args: Array[String]): Unit = {
    var s1 = new Student

    println(s1.getStuName())

    s1.setStuName("Movle")

    println(s1.getStuName())

    println("-------直接访问私有属性----------")
    println(s1.stuId+"\t"+s1.stuName+"\t"+s1.age)

  }
}