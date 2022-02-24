package chapter07

object Test01_ImmutableArray {
  def main(args: Array[String]): Unit = {
    //创建一个数组
    val array1: Array[Int] = new Array[Int](10)
    val array2 = Array(10, 1, 2, 3, 7)

    //访问元素
    println(array1(1))
    println(array2(1))

    //赋值
    array1(1) = 1 //apply存根方法！
    println(array1(1))
    array1.update(1, 5) //update存根方法！
    println(array1(1))
    println("========================")

    //循环遍历数组
    for (i <- 0 until array2.length) {
      println(array2(i))
    }
    println("========================")

    //array1.indices == 0 until  array1.length
    for (i <- array2.indices) {
      println(array2(i))
    }

    //增强for循环
    println("========================")
    for (elem <- array2) {
      println(elem)
    }

    //迭代器使用
    val iter = array2.iterator

    while (iter.hasNext) {
      println(iter.next())
    }

    //for each 方法
    array2.foreach((element: Int) => println(element))
    array2.foreach(println(_))


    println(array2.mkString("--"))

    println("=============")



    //数组中添加元
    val array3 = array2 :+ 76 :+ 77
    val array5 = array2 :+ (76, 77)
    println(array3.mkString(" "))
    println(array5.mkString(" "))


    val array4 = array2.+:(15, 20)
    val array6 = 15 +: 20 +: array2
    println(array4.mkString(" "))
    println(array6.mkString(" "))
  }
}