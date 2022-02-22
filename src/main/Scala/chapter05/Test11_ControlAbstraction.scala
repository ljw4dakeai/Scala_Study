package chapter05

object Test11_ControlAbstraction {
  def main(args: Array[String]): Unit = {
    //值传参数
    def function0(a: Int): Unit = {
      println("a= " + a)
      println("a= " + a)
    }

    def function1(): Int = {
      println("f1调用")
      12
    }

    function0(function1())
    println("======================")
    //传名参数,传递不再是具体的值，而是代码快
    def function2(a: => Int): Unit = {
      println("a= " + a)
      println("a= " + a)
    }

    function2(10)
    println("=============")
    function2(function1()) //a替换成f1的调用

    println("=============")
    function2({
      println("这是一块代码块")
      11})
  }

}
