package chapter09plus

import scala.language.implicitConversions

object Test02_Implicit {
  def main(args: Array[String]): Unit = {

    val new12 = new myRichInt(12)
    println(new12.myMax(15))


    //隐士方法
    implicit def convert(num: Int): myRichInt = new myRichInt(num)
    println(12.myMax(15))
    println(12.myMax2(100))

    //隐士参数 //同一作用范围内，不能有两个相同隐士参数
    implicit val str: String = "alice"
    def sayHello(implicit name: String): Unit = {
      println("hello" + name)
    }
    def sayHi(implicit name: String): Unit = {
      println("hi" + name)
    }

    //简单写法
    def sayNihao(): Unit= println("nihaao" + implicitly[String])

    sayHello
    sayHi
    sayNihao



  }

  //隐士类不能直接在外部定义
  implicit class myRichInt2(val self: Int) {
    def myMax2(n: Int): Int = if (n < self) self else n
    def myMin2(n: Int): Int = if (n < self) n else self

  }



}

class myRichInt(val self: Int) {
  def myMax(n: Int): Int = if (n < self) self else n
  def myMin(n: Int): Int = if (n < self) n else self

}
