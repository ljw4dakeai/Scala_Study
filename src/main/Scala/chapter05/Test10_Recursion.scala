package chapter05

import scala.annotation.tailrec

//递归耗费资源空间复杂度很高， 可能会栈溢出
object Test10_Recursion {
  def main(args: Array[String]): Unit = {
    println(fact(5))
    println(weifact(5))
  }
  def fact(i: Int): Int = {
    if (i == 0) return 1
    fact(i -1 ) * i
  }

  //尾递归调用
  def weifact(n: Int): Int = {
    @tailrec
    def loop(n: Int, currRes: Int): Int = {
      if (n == 0) return currRes
      loop(n - 1, currRes * n)
    }
    loop(n, currRes = 1)
  }

}
