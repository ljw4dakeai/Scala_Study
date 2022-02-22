package chapter06



//推荐使用
class Student6(var name: String ,var age: Int ){

  var school: String = _

  def this(name: String, age: Int, school: String){
    this(name: String, age: Int)
    this.school = school
  }

  override def toString: String = {
    s"name: ${name} age: ${age} school: ${age}"
  }
}



object Test06_ConstructorParams {
  def main(args: Array[String]): Unit = {
    val student = new Student2()
    student.name = "zoujiahao"
    student.age = 20
    println(student.name + "\t" + student.age)
    println("===============================")

    val student1 = new Student3("zoujiahao", 20)
    println(student1.name + "\t" + student1.age)
    println("===============================")

    val student2 = new Student4("zoujiahao", 20)
    student2.printerInfo()
    println("===============================")

    val student3 = new Student5("zoujiahao", 20)
    println(student3.name + "\t" + student3.age)
//    student3.age = 21 error(val)

    println("===============================")
    val student4 = new Student6("zoujiaho", 20, "WHPU")
    println(student4.toString)
  }
}


//无定义，无参数构造
class Student2 {
  var name: String = _
  var age: Int = _
}

//上面定义等价于

//推荐使用
class Student3(var name: String ,var age: Int ){

  }

class Student4( name: String , age: Int ){
  def printerInfo(): Unit ={
    println(s"name $name age $age")
  }
}


class Student5(val name: String ,val age: Int ){

}

