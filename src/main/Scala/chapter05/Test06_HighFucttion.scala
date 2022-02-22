package chapter05

import scala.collection.immutable.Range

object Test06_HighFucttion {
  def main(args: Array[String]): Unit = {
    def f(n: Int): Int = {
      println("f调用")
      n + 1
    }

    def fun(): Int = {
      println("fun调用")
      1
    }

    val result: Int = f(123)
    println(result)


    //1.函数作为值进行传递
    val f1: Int => Int = f
    val f2 = f _

    val f3 = fun  //函数调用

    val f4 = fun _ //函数作为值传递
    val f5: ()=>Int = fun //函数作为值传递


    println(f1)
    println(f1(12))

    println(f2)
    println(f2(35))


    println(f3)
    println(f4)
    println(f5)
    println("=============================================")

    //2.函数作为参数传递
    //定义一个二元计算
    def dualEval (op: (Int ,Int) => Int, a: Int, b: Int): Int = {
      op(a, b)
    }

    def add(a: Int , b: Int): Int = {
      a + b
    }

    println(dualEval(add, 12, 35))
    println(dualEval((a: Int, b: Int) => a + b , 12 ,35 ))
    println(dualEval(_ + _, 35 ,12))

    println("=============================================")



    //3.函数作为函数的返回值返回
    def f6(): Int => Unit = {
      def f7(a: Int): Unit = {
        println("f7被调用" + a)
      }
      f7 //将函数直接返回
    }

    val f7 = f6()
    println(f7(25))

    println(f6()(25)) //调用f7


  }
}
