package chapter04

object Test_PriticePyramid {
  def main(args: Array[String]): Unit = {

    for (i <- 1 to 9){
      val starts = 2 * i -1
      val spaces = 9 - i
      println(" " * spaces + "*" * starts)
    }

    println("-----------------------------------")
    for (i <- 1 to 9 ; starts = 2 * i -1 ;  spaces = 9 - i){
      println(" " * spaces + "*" * starts)
    }


    println("-----------------------------------")
    for (stars <- 1 to 17 by 2 ; spaces = (17 - stars) / 2){
      println(" " * spaces + "*" * stars)
    }


  }
}
