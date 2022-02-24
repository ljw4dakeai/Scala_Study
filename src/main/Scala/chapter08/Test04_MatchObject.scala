package chapter08

object Test04_MatchObject {
  def main(args: Array[String]): Unit = {

    val student: Student = Student("zoujiahao", 20)
    //对象进行模式匹配
    val result = student match {
      case Student("zoujiahao", 20) => "zoujiahao,20"
      case _ => "ljw"
    }

    println(result)

  }

}

class Student(val name: String, val age: Int) {


}

object Student{
  def apply(name: String, age: Int): Student = new Student(name, age)
  def unapply(student: Student): Option[(String, Int)] = {
    if ( student == null ) None
    else Some((student.name, student.age))
  }
}
