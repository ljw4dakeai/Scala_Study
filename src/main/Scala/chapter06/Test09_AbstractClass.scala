package chapter06

object Test09_AbstractClass {
  def main(args: Array[String]): Unit = {
    val student: Student09 = new Student09()

    student.sleep()
  }
}


abstract class Person09{
  val name: String = "person" //非抽象属性
  var age: Int //抽象属性

  //非抽象方法
  def eat(): Unit = {
    println("person eat")
  }

  //抽象方法
  def sleep():  Unit

}


class Student09 extends Person09 {
  var age: Int = 18

  def sleep(): Unit = {
    println("student sleep")
    super.eat()
  }

  override val name: String = "student"

}
