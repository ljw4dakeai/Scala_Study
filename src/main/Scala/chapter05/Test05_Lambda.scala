package chapter05

object Test05_Lambda {
  def main(args: Array[String]): Unit = {
    val fun = (name: String) => {println(name)}


    //以函数作为参数传入
    def f(func: String => Unit): Unit={
      func("zoujiahao")
    }
    f(fun)



    //实际操作
    //只操作两个数，但具体操作通过参数传入
    def dualFuction(fun: (Int, Int) => Int): Int = {
      fun(1, 2)
    }

    val add = (a: Int, b: Int) => a + b
    val minus = (a: Int, b: Int) => a - b


    println(dualFuction(add))
    println(dualFuction(minus))

    println(dualFuction((a, b) => a + b))
    println(dualFuction(_ + _))
    println(dualFuction(-_ + _))
  }

}
