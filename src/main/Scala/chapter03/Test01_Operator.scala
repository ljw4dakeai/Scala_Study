package chapter03

object Test01_Operator {
  def main(args: Array[String]): Unit = {
    //1.算数运算
    val result1: Int = 10 / 3; //3
    println(result1)

    val result2: Double = 10 / 3 //3.0
    println(result2)

    val result3: Double = 10.0 / 3 //3.333333
    println(result3.formatted("%2.2f")) //格式化输出

    val result4 = 10 % 3
    println(result4)



    //2.比较运算符
    val s1: String = "hello"
    val s2: String = new String("hello")

    println(s1 == s2) //里面含有equal方法 true
    println(s1.equals(s2)) //true
    println(s1.eq(s2)) //地址 -->false



    //3.逻辑运算符
    def m(n: Int): Int = {
      println("m调用")
      return n
    }
    val n = 1
    println((4 > 5) && m(n) > 0)

    def isNotEmpty(s: String): Boolean ={
      return s != null && !("".equals(s.trim))
    }



    //4.赋值运算符
//    var b: Byte = 1;
//    b += 1 //会出错

    var i: Int = 10
    i += 1
    println( i)


    //5.位运算符
    var a: Int = 60 //byte --> int 自动提升
    a <<= 2
    println(a)


    //7.运算符本质
    val i1: Int = 25
    val i2: Int = 37

    println(i1.+(i2)) //一切皆对象，调用方法
    println((7.5 toInt)toString)
  }

}
