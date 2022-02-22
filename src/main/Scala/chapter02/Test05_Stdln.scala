package chapter02

import scala.io.StdIn

object Test05_Stdln {
  def main(args: Array[String]): Unit = {
    println("请输入你的名字！")
    val name: String = StdIn.readLine()
    println("请输入你的年龄！")

    var age: Int  = StdIn.readInt()

    println(s"欢迎${age}的${name}来的whpu学习！")


  }

}
