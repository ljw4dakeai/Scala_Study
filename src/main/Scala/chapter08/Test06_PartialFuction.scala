package chapter08

object Test06_PartialFuction {
  def main(args: Array[String]): Unit = {
    val list: List[(String, Int)] = List(("a", 12), ("b", 35), ("c", 227))

    //map,实现key不变，value变为2倍
    val list1 = list.map(kv => (kv._1, kv._2 * 2))

    //漠视匹配实现元素赋值
    val newList1 = list.map(
      tuple => {
        tuple match {
          case (word, num) => (word, num * 2)
        }
      }
    )

    //s省略=>
    val newlist2 = list.map { case (word, num) => (word, num * 2) } //便函数

    println(list1)
    println(newList1)
    println(newlist2)


    //求绝对值
    val positiveAbs: PartialFunction[Int, Int] = {
      case x if x > 0 => x
    }

    val nnegativeAbs: PartialFunction[Int, Int] = {
      case x if x < 0 => -x
    }

    val zeroAbs: PartialFunction[Int, Int] = {
      case 0 => 0
    }

    def  Abs(x: Int): Int = (positiveAbs orElse nnegativeAbs orElse zeroAbs) (x)

    println(Abs(67))
    println(Abs(-19))
  }
}
