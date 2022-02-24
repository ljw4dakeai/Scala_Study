package chapter07

object Test18_ComplexWordCount {
  def main(args: Array[String]): Unit = {
    val TupleList = List(
      ("hello", 1),
      ("nihao", 2),
      ("hello spark", 3),
      ("hello spark", 3),
      ("hello spark fr0m scala", 1),
      ("hello flink from scala", 2)
    )

    val newTupleList  = TupleList.map(
      kv => {
        (kv._1.trim + " ")* kv._2
      }
    )
    println(newTupleList)

    //falemap
    val wordCountList = newTupleList
      .flatMap(_.split(" ")) //以" "分开，拿到所有单词
      .groupBy(word => word) //映射到map hello -> (hello hello), spark -> (spark, saprk, spark) ....
      .map(kv => (kv._1, kv._2.length)) //拿到单词的个数length  hello -> 2 spark -> 3
      .toList //转化为list (("hello" ，2), ("spark", 3) ... )
      .sortBy(_._2)(Ordering[Int].reverse) //排序

    println(wordCountList)


    //基于预统计结果再进行统计
    val prewordCountList = TupleList.flatMap(
      tuple => {
        val String = tuple._1.split(" ") //对前面进行分割 "hello spark"  --> "hello" "spark"
        String.map(word => (word,tuple._2))  //然后直接规划新的 hello 2 spark 2
      }
    )
    println(prewordCountList)

    val preCountMap = prewordCountList.groupBy(_._1)
    println(preCountMap)

    //叠加每个单词的统计数值
    val CountMap = preCountMap.mapValues(
      TupleList => TupleList.map(_._2).sum
    )
    println(CountMap)

    val CountList = CountMap.toList
      .sortWith(_._2 > _._2)

    println(CountList)

  }
}
