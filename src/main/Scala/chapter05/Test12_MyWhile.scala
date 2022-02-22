package chapter05

import scala.annotation.tailrec

object Test12_MyWhile {
  def main(args: Array[String]): Unit = {
    var n = 5
    while (n >= 1){
      println(n)
      n -= 1

    }

    n = 5
    println("==============")
    myWhile2(n >= 1){
      println(n)
      n -= 1
    }

    n = 5
    println("==============")
    myWhile3(n >= 1)({
      println(n)
      n -= 1
    })

    //用闭包实现一个函数，将代码块作为参数传入，递归调用

    def myWhile(condition: => Boolean): (=> Unit) => Unit= {
      def doLoop(op: => Unit): Unit = {
        if (condition){
          op
          myWhile(condition)(op)
        }
      }
      doLoop _
    }


    //用闭包实现一个函数，将代码块作为参数传入，递归调用,匿名函数

    def myWhile2(condition: => Boolean): (=> Unit) => Unit= {
      op =>  {
        if (condition){
          op
          myWhile2(condition)(op)
        }
      }
    }


    //用闭包实现一个函数，将代码块作为参数传入，递归调用,匿名函数，柯里化

    @tailrec
    def myWhile3(condition: => Boolean)(op: => Unit): Unit= {
      if (condition){
        op
        myWhile3(condition)(op)
      }
    }

  }

}
