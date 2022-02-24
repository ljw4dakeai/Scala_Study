package chapter08

object Test03_MatchTupleExtend {
  def main(args: Array[String]): Unit = {
    val (x, y) = (10 ,"hello")
    println(s"$x  + $y")

    val List(first, second, _*) = List(23, 15, 9, 78)
    println(s"$first $second ")

    val fir :: sec :: rest = List(23, 15 , 9, 18)
    println(s"$fir $sec $rest")

    //for 推导中进行模式匹配
    ////原本遍历方法
    val list = List(("a", 12), ("b", 9), ("v", 27),("a",20))

    for (elem <- list) {
      println(elem._1 + " " + elem._2)
    }

    ////将list的元素直接定义为元组，对变量赋值
    for((word,count) <- list){println(word + " " + count)}

    ////不考虑某个的位置，只遍历key和value
    for ((word,_) <- list){println(word)}

    //指定某个位置的值是多少
    for(("a",count) <- list){println(count)}
  }

}
