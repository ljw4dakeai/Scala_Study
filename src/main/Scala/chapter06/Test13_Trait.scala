package chapter06

object Test13_Trait {
  def main(args: Array[String]): Unit = {

    val studen: Student13 = new Student13()
    studen.study()
    studen.yong()
    studen.sayHi()
    studen.dating()

  }
}

class Person13{
  val name:  String = "zoujiahao"
  var age: Int = 20

  def sayHi(): Unit = {
    println(s"Hello ${name}")
  }
}

trait yong{
  //定义抽象属性和非抽象属性
  val name = "lijingwen"
  var age: Int

  //定义一个抽象方法和非抽象方法

  def yong(): Unit = println("say yong")
  def dating(): Unit

}


class Student13 extends Person13 with yong{
  //重写冲突属性
  override val name: String =  "zoujiahao"
  override def dating(): Unit = println(s"${name} dating")

  def study(): Unit = println(s"${name} studing")

  override def sayHi(): Unit = {

    super.sayHi()
    println("hello student")
  }
}