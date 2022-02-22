package chapter06

object Test05_Constructor {
  def main(args: Array[String]): Unit = {
    val con = new StudentCon()
    println("==========================================")
    con.studentCon()
    println("==========================================")
    val con1 = new StudentCon("邹家豪")
    println("==========================================")
    val con2 = new StudentCon("李静雯", 21)



  }

}


class StudentCon(){
  var age: Int = _
  var name: String = _
  var sex: String =  _
  println("1。主构造器方法调用")

  def this(name: String){
    this()  //直接调用主构造器
    println("2。辅助构造器方法调用")
    this.name = name
    println(s"name ${name} age ${age}")
  }

  def this(name: String, age: Int){
    this(name)
    println("3。辅助构造器方法调用")
    this.age = age
    println(s"name ${name} age ${age}")
  }

  def studentCon(): Unit ={
    println("一般方法调用")
  }


}
