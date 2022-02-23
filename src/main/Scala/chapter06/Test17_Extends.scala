package chapter06

object Test17_Extends {
  def main(args: Array[String]): Unit = {

    val student: Student17 = new Student17("zoujiahao", 20)

    student.sayHi()
    student.study()
    println("=========================")
    val person: Person17 = new Student17("zoujiaho",20)
    person.sayHi()
//    person.study()error
    println("=========================")


    println(student.isInstanceOf[Student17])
    println(student.isInstanceOf[Person17])
    println(person.isInstanceOf[Student17])
    println(person.isInstanceOf[Person17])


    if (person.isInstanceOf[Student17]) {
      val newstudent = person.asInstanceOf[Student17]
      newstudent.study()  //就有了study方法
    }


    //测试枚举累
    println(workday.MonDay)
  }
}


class Person17(val name: String, val age: Int){
  def sayHi(): Unit = {
    println(s"nhao person $name $age")
  }
}


class Student17(name: String, age: Int ) extends Person17(name: String, age: Int ){
  override def sayHi(): Unit = println(s"nhao student $name $age")

  def study(): Unit = println("study ")
}


//枚举类型
object workday extends Enumeration{
  val MonDay: workday.Value = Value(1, "MonDay")
  val TusDay: workday.Value = Value(2, "TusDay")

}

object TestAPP extends App{
  println("app start")
}
