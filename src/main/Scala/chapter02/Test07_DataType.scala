package chapter02

import chapter01.Student
import sun.tools.tree.ByteExpression

object Test07_DataType {
  def main(args: Array[String]): Unit = {


    val a1: Byte = 127

    //val a2: Byte = 128 //错误
    val a3: Byte = -128

    val a = 12 //默认类型类Int

    val aL = 1281492897412948129L //加上L标示长整型，长整形也必须加上L

    val b1: Byte = 10
    val b2: Byte = 10 + 20//可以执行
   // val b3: Byte = b1 + 20//不可以执行
    val b4: Byte = (b1 + 20).toByte //调用类型转换方法

    //浮点类型
    val f1 = 1.245F
    val d1 = 1.234


    //字符类型
    val c1: Char = 'c'
    val c2: Char = '9'

    val c3: Char = '\t'
    val c4 = '\n'
    val c5 = '\\' //标示\
    val c6 = '\"' //标示"
    println("abc" + c3 + "def")
    println("abc" + c4 + "def")

    //字符变量底层用ASCII码标示
    val i1: Int = c1
    val i2: Int = c2
    println("i1" + i1)
    println("i2" + i2)
    val c7: Char = (i1 + 1).toChar //c的后一个字符
    println(c7)


    //Boolean类型 True False
    val t: Boolean = true
    val f: Boolean = false

    //空类型
      //Unit
      def m1(): Unit = {
        println("m1被调用")
      }
      val m = m1()
      println("m:" + m)


      //Null
      //val n: Int = null //值类型不能，赋值null
      val student: Student = null //应用类型，才能赋值为Null


      //Nothing,
      def m2(i: Int): Int = {
        if (i == 0)
          throw new NullPointerException
        else
          return i
      }
      val m_ = m2(0)
      println(m_)




  }

}
