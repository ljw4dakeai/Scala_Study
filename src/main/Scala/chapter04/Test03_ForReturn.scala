package chapter04

object Test03_ForReturn {
  def main(args: Array[String]): Unit = {
    val unit = for (i <- 1 to 10) {
     i
    }
    println(unit)

    val a = for (i <- 1 to 10) yield i * i //对集合处理，非常好用！！！！
    println("a =  " + a)
  }

}
