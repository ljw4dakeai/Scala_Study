package chapter04

object Test_UlTable {
  def main(args: Array[String]): Unit = {
    for (i <- 1 until 10){
      for (j <- 1 to  i){
        print(s"$i*$j = ${i * j}\t")
      }
      println()
    }

    for (i <- 1 until 10 ; j <- 1 to i){
      print(s"$i*$j = ${i * j}\t")
      if (j == i) println()

    }
  }

}
