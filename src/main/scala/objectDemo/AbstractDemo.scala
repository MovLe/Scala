package objectDemo

/**
 * @ClassName AbstractDemo
 * @MethodDesc: scala面向对象之抽象类
 * @Author Movle
 * @Date 5/11/20 8:03 下午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/

abstract class Vehicle{
  def checkType():String
}

class Car extends Vehicle{
  def checkType():String={"I am a Car"}
}

class Bike extends Vehicle{
  def checkType():String={"I am a Bike"}
}



object AbstractDemo {

  def main(args: Array[String]): Unit = {
    var v1:Vehicle = new Car
    println(v1.checkType())

    var v2:Vehicle = new Bike
    println(v2.checkType())
  }
}
