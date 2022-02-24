package chapter07

object Test03_MulArray {
  def main(args: Array[String]): Unit = {
    //二维数组创建
    val array: Array[Array[Int]] = Array.ofDim[Int](2, 3)

    array(0)(1) = 19
    array(1)(1) = 100
    //查看二维数组

    for(i <- 0 until array.length; j <- 0 until array(i).length){
      println(array(i)(j) )
    }

    println("===============")

    for (i <- array.indices; j <- array(i).indices){
      print(array(i)(j) + "\t")
      if ( j == array(i).length - 1 ) println()
    }


    array.foreach(line => line.foreach(println))
  }

}
