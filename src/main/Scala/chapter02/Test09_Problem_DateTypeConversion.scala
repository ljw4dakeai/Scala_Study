package chapter02

object Test09_Problem_DateTypeConversion{
  def main(args: Array[String]): Unit = {
    val n: Int = 130
    val b: Byte = n.toByte
    print(b)
  /*
      128 int 4字节，32位
  源码0000 0000 0000 0000 0000 0000 0000 1000 0000
  补码0000 0000 0000 0000 0000 0000 0000 1000 0000

  最后一个字节byte
  补码1000 0000
  标示最大负数-128



      130 int 4字节，32位
  源码0000 0000 0000 0000 0000 0000 0000 1000 0010
  补码0000 0000 0000 0000 0000 0000 0000 1000 0010

  最后一个字节byte
  补码1000 0010 -->符号位不变，取反+1
  对应源码1111 1110 -126
  标示最大负数-128
   */

    val n1: Int = 128
    val b1: Byte = n1.toByte
    print(b1)


  }
}
