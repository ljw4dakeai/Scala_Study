package chapter07

object Test10Tuple {
  def main(args: Array[String]): Unit = {
    val tuple: (Char, String, Int, Boolean) = ('h', "nihao", 100, true)
    println(tuple)

    val s = "+++++++++++++++++++++++"
    println(s)

    //访问数据
    println(tuple._1)
    println(tuple._2)

    println(tuple.productElement(0))
    println(tuple.productElement(1))
    println(s)

    //遍历元组数据
    for(elem <- tuple.productIterator) println(elem)

    //嵌套元组
    val multuple = (12 ,0.3,  (23, " nihao"))
    println(multuple)

  }
}
