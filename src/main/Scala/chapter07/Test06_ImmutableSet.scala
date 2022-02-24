package chapter07
//无序
object Test06_ImmutableSet {
  def main(args: Array[String]): Unit = {
    val set1 = Set(1, 20, 30 ,1)
    println(set1)

    println("=========")


    //添加元素
    val set2 = set1 + 40
    println(set1)
    println(set2)

    println("=========")
    //合并
    val set3 = Set(11, 21, 31 ,41)

    val set4 = set2 ++ set3 //++ 而不是+ +只能加一个元素
    println(set2)
    println(set3)
    println(set4)


    println("=========")
    //删除
    val set5 = set3 - 11
    println(set3)
    println(set5)
  }

}
