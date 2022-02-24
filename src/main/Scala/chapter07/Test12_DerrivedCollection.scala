package chapter07

object Test12_DerrivedCollection {
  def main(args: Array[String]): Unit = {
    val list1 = List(1, 20, 30, 40, 11)
    val list2 = List(1, 2, 33, 4, 5)

    //集合的头和尾
    println(list1.head)
    println(list1.tail) //除了头救是尾

    //最后一个元素和初始数据
    println(list1.last)
    println(list1.init)

    //反转
    println(list1.reverse)

    //去左右元素
    println(list1.take(3))
    println(list1.takeRight(3))

    //去掉钱n个元素
    println(list1.drop(2))
    println(list1.dropRight(2))

    //并集 如果是set就会去重
    val uion = list1.union(list2)
    println(uion)

    println(list1 ::: list2)
    println(list1 ++ list2)

    val set1 = Set(1, 20, 30, 40, 11)
    val set2 = Set(1, 2, 33, 4, 5)

    val uion2 = set1 union set2
    println(uion2)
    println(set1 ++ set2)

    //交集
    val intersection = list1 intersect list2
    println(intersection)

    //差集
    val differ1 = list1 diff list2
    val differ2 = list2 diff list2
    println(differ1)
    println(differ2)


    //拉链
    println(list1 zip list2)

    println(list2 zip list1)


    //滑窗
    println(list2)
    for (elem <- list2.sliding(3)) {
      println(elem)
    }

    //滚动窗口
    for (elem <- list2.sliding(2, 2)) {println(elem)}
  }
}