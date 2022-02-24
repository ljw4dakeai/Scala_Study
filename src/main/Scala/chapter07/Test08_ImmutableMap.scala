package chapter07

object Test08_ImmutableMap {
  def main(args: Array[String]): Unit = {
    val map1: Map[String, Int] = Map("hello" -> 20, "scala" ->40)
    println(map1)

    println(map1.getClass)

    println("=================")

    //遍历
    map1.foreach(println)

    map1.foreach((kv: (String, Int) ) => println(kv) )

    println("=================")

    //提取所有key或者value
    for(key <- map1.keys){
      println(s"$key -> ${map1.get(key)}" )
      println(key)
    }

    println("=================")
    //通过key访问value
    println(map1.get("hello").get)
    println(map1.get("zoujih"))

    println(map1.getOrElse("c", 0 )) //如果没有返回默认值0

  }

}
