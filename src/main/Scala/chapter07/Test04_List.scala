package chapter07

object Test04_List {
  def main(args: Array[String]): Unit = {
    val list1 = List(1, 2, 40)
    println(list1)

    println(list1(1))
    //不能修改值
    list1.foreach(println)

    //添加元素，和arrayBUffer类似
    val list2 = list1 :+ 10
    println(list2)
    val list3 = 202 +: list1
    println(list3)
    println("===================")

    //新的添加方法 :: 头部添加
    val list4 = list3.::(52)
    println(list4)

    val list5 = Nil.::(12)
    println(list5)

    //常见的操作方式，添加数据
    val list6 = 16 :: 20 :: 59 :: 70 :: Nil
    println(list6)

    println("===================")

    //列表的合并
    val list8  = list6 :: list5
    println(list8)
    //合并，扁平化操作
    val list9 = list6 ::: list5
    println(list9)

    val list10 = list6 ++ list5
    println(list10)






  }
}
