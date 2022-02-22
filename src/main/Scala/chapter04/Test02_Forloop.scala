package chapter04



object Test02_Forloop {
  def main(args: Array[String]): Unit = {

    //范围遍历(to是方法)
    for (i <- 1 to 10){
      println(i + ".nihao")
    }

    for (i <- Range(1, 10)){
      println(i + ".你好")
    }

    for (i <- 1 until 10){
      println(i + ".nihao")
    }


    //集合遍历
    for (i <- Array(12, 18, 26)){
      println(i)
    }
    for (i <- List(11, 20)){
      println(i)
    }


    //循环守卫continue
    for (i <- 1 to 10 ){
      if (i != 5){
        println(i)
      }
    }

    for (i <- 1 to 10  if i != 5 ){
      println(i)
    }

    //循环步长
    for (i <- 1 to 10  by 2 ){
      println(i)
    }
    for (i <- 10 to 1  by -1 ){
      println(i)
    }
    for (i <- 1 to 10  reverse){
      println(i)
    }

//    for (i <- 1.0 to 10.0  by 0.3){
//      println(i)
//    }

    for (i <- 1 to 3){
      for (j <- 1 to 3){
        println("i=" + i + ",j=" + j)
      }
    }

    for (i <- 1 to 3 ; j <- 1 to 10 ){
      println("i=" + i + ",j=" + j)

    }

    //循环变量引入
    for  (i <- 1 to 10){
      val j = 10 - i
      println("i=" + i + ",j=" + j)
    }

    for (i <- 1 to 10 ; j = 10 - i){
      println("i=" + i + ",j=" + j)
    }

    for {
      i <- 1 to 10
         j = 10 - i
    }
    {
      println("i=" + i + ",j=" + j)
    }


  }

}
