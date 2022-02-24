package chapter07

import scala.collection.mutable

object Test07_MutableSet {
  def main(args: Array[String]): Unit = {
    val set1: mutable.Set[Int]  = mutable.Set(1, 20, 30 ,1)
    println(set1)

    println("==============")


    //添加元素,推荐使用英文方法
    val set2 = set1 + 11 //和不可变一样
    println(set2)
    set1 +=  11
    println(set1)

    val flage2 = set1.add(10)
    println(flage2)
    println(set1)

    val falge2 = set1.add(10)
    println(falge2)
    println(set1)

    println("==============")
    //删除元素
    println(set1)
    val set3 = set1 - 11 //和不可变一样
    println(set3)
    set1 -=  11
    println(set1)

    val flage3 = set1.remove(10)
    println(flage3)
    println(set1)

    val falge4 = set1.remove(10)
    println(falge4)
    println(set1)
    println(set3)

    //合并
    println("=============")
    val set4 = mutable.Set(100, 90, 80, 10)

    set3 ++ set4

    println(set3)
    println(set4)

    val set5 = set3 ++ set4
    println(set5)

    set3 ++= set4
    println(set3)
    println(set4)
  }

}
