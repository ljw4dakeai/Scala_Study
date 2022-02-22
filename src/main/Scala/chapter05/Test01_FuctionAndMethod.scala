package chapter05

object Test01_FuctionAndMethod {


  def main(args: Array[String]): Unit = {
    //定义函数
    def sayHi(name: String): Unit = {
      println("hi" + name)
    }

    //调用函数
    sayHi("zoujiahao")
    //调用对象方法
    Test01_FuctionAndMethod.sayHi("zoujiahao")


    val result = Test01_FuctionAndMethod.SayHi("alice")
    println(result)
  }

  //对象方法
  def sayHi(name: String): Unit = {
    println("Hi" + name)
  }


  def SayHi(name: String): String = {
    println("Hi" + name)
    return "hellow"
  }

}
