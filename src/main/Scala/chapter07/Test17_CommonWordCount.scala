package chapter07

object Test17_CommonWordCount {
  def main(args: Array[String]): Unit = {
    val StringList = List(
      "hello",
      "nihao",
      "hello spark",
      "hello spark",
      "hello spark from scala",
      "hello flink from scala"
    )
    //拿到所有单词的集合
    val wordList = StringList.flatMap(_.split(" "))
    println(wordList)

    //对相同单词进行分组，list -> map
    val groupmap = wordList.groupBy(word => word)
    println(groupmap)

    //拿到所有单词的个数
    val countmap = groupmap.map(kv => (kv._1, kv._2.length))


    //map -> list

    val sortList = countmap.toList
      .sortWith(_._2 > _._2)

    println(sortList)
  }

}
