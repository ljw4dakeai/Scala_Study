package chapter04

import scala.io.StdIn

object Test01_IfElse {
  def main(args: Array[String]): Unit = {

    println("请输入你的年龄")
    val age: Int = StdIn.readInt()

    if (age >=18){
      println("成年！")
    }

    println("============")

    if (age >= 18 ){
      println("成年")
    } else {
      println("未成年！")
    }


    if (age <= 6 ){
      println("童年")
    } else if (age < 18){
      println("少年")
    } else if (age < 35){
      println("青年")
    } else {
      println("老年")
    }

    //分支语句的表达值

    //可以返回公共父类
    val result: Any =
      if (age <= 6 ){
        println("童年")
        "童年"
      } else if (age < 18){
        println("少年")
        "少年"
      } else if (age < 35){
        println("青年")
        age
      } else {
        println("老年")
        "老年"
      }


    println("result" + result)

    val res = if (age <  18) "weicehngnian" else "chengnian"
    println(res)




    //嵌套分支

    if (age >= 18 ){
      println("成年")
      if (age <= 35){
        println("青年")
      } else {
        println("老年")
      }
    } else {
      println("未成年！")
    }
  }

}
