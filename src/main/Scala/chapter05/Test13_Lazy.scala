package chapter05
//lazy 132
//不是lazy 312
object Test13_Lazy {
  def main(args: Array[String]): Unit = {
    val result: Int = sum(12, 15)
    println("1.函数调用")
    println("2.result = " + result  )
  }

  def sum(i: Int, i1: Int): Int = {
    println("3.sum调用")
    i + i1
  }

}
