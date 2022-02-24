package chapter07

import scala.math

object Test13_SimpleFuction {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 9 ,10 ,-1)
    val list2 = List(("a", 1), ("b", 2), ("c", 3), ("d", 9), ("e", 10), ("f", -1))
    //求和
    var sum = 0
    for (elem <- list) {sum += elem}
    println(sum)

    println(list.sum)

    //乘

    println(list.product)

    //最大最小
    println(list.max)
    println(list2.maxBy( (tuple: (String, Int)) => tuple._2) )
    println(list2.maxBy(_._2))
    println(list.min)
    println(list2 maxBy(_._2))


    //排序
    //sorted
    //小到大
    val sortedlist = list.sorted
    println(sortedlist)
    //从大到小
    println(list.sorted.reverse)
    println(list sorted Ordering[Int].reverse)

    //sortby
    println(list2 sortBy(_._2))
    println(list2.sortBy(_._2)(Ordering[Int]reverse))

    //sortwithh
    println(list.sortWith((a: Int, b: Int) => a < b))
    println(list.sortWith(_<_))
    println(list.sortWith(_>_))
  }

}
