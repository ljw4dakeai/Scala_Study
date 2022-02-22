package chapter05

object Test02_FuctionDefine {
  def main(args: Array[String]): Unit = {
    //无参，无返回
    def f1(): Unit ={
      println("无参，无返回")
    }

    //无参，有返回
    def f2(): Int = {
      println("无参，有返回")
      12
    }

    //有参，无返回
    def f3(name: String): Unit = {
      println("有参，无返回 " + name)
    }

    def f4(name: String): String ={
      println("有参，有返回")
      "hi" + name
    }

    def f5(name1: String, name2: String): Unit = {
      println(s"$name1 he $name2")
    }

    def f6(name1: String, name2: String): String ={
      println("有参，有返回")
      "hi" + name1 + name2
    }

    println(f1())
    println(f2())
    println(f3("zoujiahao"))
    println(f4("zoujiahao"))
    println(f5("zoujiahao", "zoujiahao"))
    println(f6("zoujiahao", "zoujiahao"))





  }
}
