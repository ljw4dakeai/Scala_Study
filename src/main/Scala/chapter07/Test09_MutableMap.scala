package chapter07

import scala.collection.mutable


object Test09_MutableMap {
  def main(args: Array[String]): Unit = {
    val map1: mutable.Map[String, Int] = mutable.Map("hello" -> 20, "scala" ->40)
    println(map1)

    println(map1.getClass)

    println("============")

    //添加元素
    map1.put("1" , 10)
    map1.put("2", 10)
    println(map1)

    map1 += (("e", 100))
    println(map1)

    println("============")
    //删除元素
    map1.remove("1")
    println(map1)
    map1 -= (("2"))
    println(map1)

    //更新值，修改元素
    map1.update("1" , 10)
    map1.update("2", 10)
    println(map1)

    map1 += (("e", 100))
    println(map1)

    map1.update("1", 20)
    println(map1)

    println("============")

    val map2: mutable.Map[String, Int] = mutable.Map("h" -> 100, "nihao" -> 100)

    val map3 = map1 ++ map2

    println(map1)
    println(map2)
    println(map3)

    //后面的数据如果前面没有，就直接添加，如果有，修改为后面的数据
    map1 ++= map2
    println(map1)
    println(map2)

  }

}
