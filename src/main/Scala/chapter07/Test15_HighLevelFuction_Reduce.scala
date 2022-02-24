package chapter07

object Test15_HighLevelFuction_Reduce {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4)

    //1.reduce
    println(list.reduce(_ + _))
    println(list.reduceLeft(_ + _))
    println(list.reduceRight(_ + _))


    val list2 = List(3, 4, 5, 8, 10)
    println(list2.reduce(_-_))
    println(list2.reduceLeft(_-_))
    println(list2.reduceRight(_ - _))// 3 -(4 - (5- (8 - 10)))

    //2.fold
    println(list.fold(10)(_+_))

    println(list.foldLeft(10)(_ - _))

    println(list.foldRight(11)(_ - _)) //3 -(4 -(5 - (8 -(10 - 11))))
  }

}
