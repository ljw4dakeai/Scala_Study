package chapter06

object Test11_Object {
  def main(args: Array[String]): Unit = {
//    val student = new Student11("zoujiahao", 20)
//    student.printerInfo()

    val student = Student11.newStudent("zoujiahao", 20)
    student.printerInfo()

    val student02 = Student11("bob", 21) //apply方法
    student02.printerInfo()
  }
}

class Student11 private (var name: String, var age:Int ){ //构造方法私有化
  def printerInfo(): Unit ={
    println(s"student ${name} $age " + Student11.school)
  }
}
object Student11{ //里面全是静态的
  val school: String = "WHPU" //静态属性

  //定义一个类的对象实例的创建方法
  def newStudent(name: String, age: Int): Student11 = new Student11(name, age)

  //定义一个类的apply方法
  def apply(name: String, age: Int): Student11 = new Student11(name, age)

}