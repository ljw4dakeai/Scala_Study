package chapter07

object Test11_CommonOP {
  def main(args: Array[String]): Unit = {
    val list = List(10, 20, 11)
    val set = Set(10, 20 ,10)
    //获取长度或者大小
    println(list.length)
    println(set.size)


    //遍历

    for(elem <- list){
      println(elem)
    }

    list.foreach(println)

    //迭代器
    for (elem <- list.iterator) {println(elem)}


    //生成字符串
    println(list)
    println(set)
    println(list.mkString(" "))


    //是否包涵
    println(list.contains(23))
    println(set.contains(10))
  }


}
