package chapter02
//自动类型转换
// byte -> short -> int -> Long -> float -> double
//char -> int
object Test08_DateTypeConversion {
  def main(args: Array[String]): Unit = {
    //自动提神
    val B1: Byte = 10

    val b1: Long = 100L
    val result: Long = b1 + B1

    val result1: Int = (b1 + B1).toInt

    //低精-->高精度（自动）高精度-->低精度（error）
    val a2: Byte = 10
    val b2: Int = a2

    //（byte short） char 不会自动转换

    val a3: Byte = 10
    val b3: Char = 'b'
    //val c2: Byte = b3 //error
    val c3: Int = b3
    print(c3)

    //byte short char 可以计算但是 char 需要变成int

    val a4: Byte = 14
    val b4: Short = 20
    val c4: Char = 'c'

    val result2: Int = a4 + b4 + c4
    println(result2)



    //强制类型转换

    //只会取整，不会进
    val n1: Int = 2.5.toInt
    val n2: Int = -2.5.toInt
    println(n1)
    println(n2)

    //对最近数有效，小括号提神优先级
    val n3: Int = 2.5.toInt + 3.3.toInt
    val n4: Int = (2.5 + 3.3).toInt

    //数值类型和string类型
    //(1)数值转换string
    val n: Int = 100
    val s1: String = n + ""
    print(s1)
    //(2)string转换为数值
    val m: Int = "12".toInt

    val x: Int = "12.3".toFloat.toInt
    print(x)






  }

}
