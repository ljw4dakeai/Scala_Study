package chapter06

object Test10_AnnoymousClass {
  def main(args: Array[String]): Unit = {
    val person: Person10 = new Person10 {
      override val name: String = "zoujiahao"

      override def eat(): Unit = println("zoujiahao eat")
    }

    person.eat()
    println(person.name)
  }

}


abstract class Person10{
  val name: String

  def  eat(): Unit
}