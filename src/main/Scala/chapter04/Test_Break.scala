package chapter04

import scala.util.control.Breaks

object Test_Break {
  def main(args: Array[String]): Unit = {
    try
      for (i <- 1 to 10){
        if (i == 3)
          throw new RuntimeException
        println(i)

      } catch {
        case e: Exception => //什么都不做，推出循环
    }

    println("xunhuan外")

    //使用Scala中的break类的break方法
    Breaks.breakable(
      for (i <- 1 to 10) {
        if (i == 3)
          Breaks.break()
        println(i)
      }
    )
  }
}
