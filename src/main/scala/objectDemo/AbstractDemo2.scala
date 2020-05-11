package objectDemo

/**
 * @ClassName AbstractDemo2
 * @MethodDesc: scala面向对象之抽象字段
 * @Author Movle
 * @Date 5/11/20 8:07 下午
 * @Version 1.0
 * @Email movle_xjk@foxmail.com
 **/
//抽象的父类
abstract class Person{
  //第一个抽象的字段，并且只有get方法
  val id:Int
  //第二个抽象的字段，有get和set方法
  var name:String
}

abstract class Employee1 extends Person{
  var name:String="No Name"
}

abstract class Employee2(val id:Int) extends Person{
  var name:String="No Name"
}

object AbstractDemo2 {

}
