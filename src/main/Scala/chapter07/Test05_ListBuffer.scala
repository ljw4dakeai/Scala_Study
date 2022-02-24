package chapter07

import scala.collection.mutable.ListBuffer

object Test05_ListBuffer {
  def main(args: Array[String]): Unit = {
    //scala推荐使用伴生对象创建
    val listB1: ListBuffer[Int] = new ListBuffer[Int]()

    val listB2 = ListBuffer(10, 20, 30, 11)
    println(listB1)
    println(listB2)

    println("===========")

    //添加元素
    listB1.append(12, 33)

    listB2.append(100)

    listB1.insert(1, 100, 200)

    println(listB1)
    println(listB2)
    println("===========")

    listB1 += 25 += 100

    101 +=: 20 +=: listB1
    println(listB1) //== 等价 101 +=: 20 +=: listB1 += 25 += 100

    println("===========")
    println(listB1)
    println(listB2)
    //连接两个list
    val listB3 = listB1 ++ listB2
    println(listB3)
    println(listB1)
    println(listB2)

    listB1 ++= listB2
    println(listB1)
    println(listB2)

    println("===========")
    //修改元素
    listB2(3) = 30
    println(listB2)
    listB2.update(2, 11)
    println(listB2)

    //删除元素
    listB2.remove(0)
    println(listB2)
    listB2 -= 30
    println(listB2)

  }

}
