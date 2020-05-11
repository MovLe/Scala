package objectDemo

/**
 * @ClassName inheritDemo
 * @MethodDesc: scala面向对象之继承
 * @Author Movle
 * @Date 5/11/20 7:15 下午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
class Person(val name:String,val age:Int){
  def sayHello():String = "Hello "+name+" and age is "+age
}

class Employee (override val name:String,override val age:Int,val salary:Int) extends Person(name,age){

  override def sayHello(): String = "bye "+name+" and age is "+age+" and salary is "+salary
}

object inheritDemo {
  def main(args: Array[String]): Unit = {
    var p1 = new Person("Tom",20)
    println(p1.sayHello())


    var p2:Person = new Employee("Mike",25,1000)

    println(p2.sayHello())

    //使用匿名子类
    var p3:Person = new Person("Jerry",26){
      override def sayHello(): String = "Hello "+name+" and age is "+age
    }
    println(p3.sayHello())
  }
}
