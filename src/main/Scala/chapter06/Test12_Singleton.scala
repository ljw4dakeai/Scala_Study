package chapter06

object Test12_Singleton {

// 单例设计模式
  def main(args: Array[String]): Unit = {
    val student1: Student12 = Student12.getInstance()
    student1.printerInfo()


    val student2: Student12 = Student12.getInstance()
    student2.printerInfo()


    println(student1)
    println(student2)
  }
}


class Student12 private (var name: String, var age:Int ){ //构造方法私有化
  def printerInfo(): Unit ={
    println(s"student ${name} $age " + Student11.school)
  }
}

////饿汉式
//object Student12{
//  private val student: Student12 = new Student12("zoujiahao", 20)
//  def getInstance(): Student12 = student
//}

//懒汉式
object Student12{
  private var student: Student12 = _
  def getInstance(): Student12 = {
    if (student == null) student = new Student12("zoujiahao", 20)
    student
  }
}