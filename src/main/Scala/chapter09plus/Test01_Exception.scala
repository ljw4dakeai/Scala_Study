package chapter09plus

object Test01_Exception {
  def main(args: Array[String]): Unit = {
    try {
      val n = 10 / 0
    }catch {
      case e: ArithmeticException => {
        println("输入错误")
      }
      case e: Exception => {println("发生一班异常")}
    }finally {
      println("处理完成")
    }
  }
}
