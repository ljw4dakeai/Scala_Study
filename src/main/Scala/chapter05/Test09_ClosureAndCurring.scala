package chapter05

object Test09_ClosureAndCurring {
  def main(args: Array[String]): Unit = {
    def add(a: Int, b: Int): Int ={
      a + b
    }

    //考虑固定一个加数
    def add4(b: Int): Int ={
      b + 4
    }

    //固定加数作为另一个参数传入，但是是作为"第一层"参数传入
    def add4One(): Int => Int ={
      val a = 4
      def addB(b: Int): Int = {
        a + b
      }
      addB
    }

    def add4Two(a: Int): Int => Int ={
      def addB(b: Int): Int = {
        a + b
      }
      addB
    }

    //lambda简写

    def Add(a: Int): Int => Int = b => a + b

    def AddTwo(a: Int): Int => Int = a + _

    println(add4Two(10)(20))
    println(Add(10)(20))
    println(AddTwo(10)(20))


    //柯里化。推荐使用
    def Curring(a: Int)(b: Int): Int = a + b

    println(Curring(10)(20))
  }
}
