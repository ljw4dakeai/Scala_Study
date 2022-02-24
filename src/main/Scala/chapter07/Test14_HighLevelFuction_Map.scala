package chapter07

object Test14_HighLevelFuction_Map {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4, 5, 6, 7, 8, 9)

    //过滤
    //选取偶数
    val evenlist = list.filter((elem: Int) => elem %2 ==0)
    println(evenlist)
    //选取奇数
    println(list.filter(_ % 2 == 1))


    //map操作
    //集合中每个数*2
    println(list.map(_ *2))

    println(list.map(x => x * x))

    //扁平化
    val nestList = List(List(1, 2, 3),List(1, 4, 7), List(9, 10, 11))

    println(nestList(0) ::: nestList(1) ::: nestList(2))
    println(nestList.flatten)


    //扁平映射
    val strings: List[String] = List("hello word", "hello scala", "hello spark")

    val stringsList: List[Array[String]] = strings.map(_.split(" ")) //分词
    val flattenList = stringsList.flatten
    println(flattenList )

    val flattenMapList = strings.flatMap(_.split(" "))
    println(flattenMapList)

    //group
    val group = list.groupBy(_%2)
    println(group)


    val groupString = flattenMapList.groupBy(_.charAt(0))
    println(groupString)

  }
}
