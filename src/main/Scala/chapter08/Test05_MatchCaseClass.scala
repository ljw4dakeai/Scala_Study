package chapter08

object Test05_MatchCaseClass {
  def main(args: Array[String]): Unit = {

    val student =Student1("zoujiaho", 21)
    val result = student match {
      case Student1("zoujiahao", 20) => "zoujiahao,20"
      case _ => "ljw"
    }

    println(result)
  }
}

case class Student1( name: String, age: Int)
