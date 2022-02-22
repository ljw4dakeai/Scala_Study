package chapter06

object Test08_DynamicBind {
  def main(args: Array[String]): Unit = {

    val person: Person08 = new Student08()

    person.hello() //属性动态绑定，方法动态绑定
    println(person.name)
  }
}


class Person08 {
  val name: String = "person"
  def hello (): Unit = {
    println("hellow person")
  }
}


class Student08 extends Person08 {
  override val name: String = "student"

  override def hello(): Unit = {
    println("hello student")
  }
}