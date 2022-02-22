package chapter01

class Student (name: String, var age: Int){
  def printInfo(): Unit = {
    println(name  + '\t' + age + '\t' + Student.school)//引入伴生对象引入学校
  }
}

//引入伴生对象
object Student{
  val school : String = "whpu"

  def main(args: Array[String]): Unit = {
    val zjh = new Student("zjh", 20)
    val ljw = new Student("ljw", 20)

    zjh.printInfo()
    ljw.printInfo()
  }
}
