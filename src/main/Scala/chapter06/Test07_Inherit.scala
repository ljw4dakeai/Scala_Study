package chapter06

object Test07_Inherit {
  def main(args: Array[String]): Unit = {
    val student1 = new Student7("zoujiahao", 20)
    println("=======================")
    val student2 = new Student7("zoujiahao", 20, 1905)

    student1.printerInfo()
    student2.printerInfo()
    println("=======================")
    val teacher = new Teacher()
    teacher.printerInfo()
    println("=======================")
    //动态绑定方法 == 多态
    def personInfo(person: Person7): Unit = {
      person.printerInfo()
    }

    personInfo(student1)
    personInfo(teacher)





  }
}

class Person7() {
  var name: String = _
  var age: Int = _
  println("1.父类主构造器被调用")

  def this(name: String, age: Int){
    this()
    println("2.父类辅助构造器被调用")
    this.name = name
    this.age = age
  }
  def printerInfo(): Unit = {
    println(s"person: name: ${name} age: ${age}")
  }
}

//子类 extends后为父类构造器，自己决定使用那个
class Student7(name: String, age: Int) extends Person7(){

  var stuNum: Int = _
  println("3.子类主构造器被调用")
  def this(name: String, age: Int, stuNum: Int){
    this(name, age)
    println("4.子类辅助构造器被调用")
    this.stuNum = stuNum
  }

  override def printerInfo(): Unit = {

    println(s"student: name: ${name} age: ${age} school: ${stuNum}")
  }

}


class Teacher extends Person7{
  override def printerInfo(): Unit ={
    println("teacher")
  }
}
