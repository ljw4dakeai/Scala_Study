package chapter05


//实现集合处理的map操作
object Test07_PriticeCollectionOperation {
  def main(args: Array[String]): Unit = {
    //对数组进行处理，将操作抽象出来，处理完毕后的结果返回一个新的数组

    val array: Array[Int] = Array(12, 14, 22, 90)


    def arrayOpreation(array: Array[Int], op: Int => Int): Array[Int] = {
      for (elem <- array) yield op(elem)
    }

    //定义操作
    def add1(elem: Int): Int = {
      elem + 1
    }

    val newArray: Array[Int] = arrayOpreation(array, add1)

    println(newArray.mkString(","))

    //2.传入匿名函数，实现翻倍
    val newArrayw = arrayOpreation(array, _ * 2)
    println(newArrayw.mkString(","))

  }
}
