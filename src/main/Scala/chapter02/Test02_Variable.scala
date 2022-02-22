package chapter02

import chapter01.Student

object Test02_Variable {
  def main(args: Array[String]): Unit = {
    var a: Int = 10
    //(1)类型可以省掉
    val b = 10

    //(2)类型确定后不能修改，强数据类型语言
    //var b = "hellow"

    //(3)变量声明，必须给初始值
    //var b: Int

    //(4)var为变量（variable）val为常量（value）

    var alice = new Student("alice", 20)
    alice = new Student("alices", 21)


    val bob = new Student("bob", 20)

    //bob = new Student("bob", 21)
    bob.age = 21
    bob.printInfo()

  }

}
