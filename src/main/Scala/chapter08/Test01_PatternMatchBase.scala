package chapter08

object Test01_PatternMatchBase {
  def main(args: Array[String]): Unit = {
    val x: Int = 2
    val y: String = x match {
      case 1 => "one"
      case 2 => "two"
      case 3 => "three"
      case 4 => "four"
      case _ => "other"
    }

    println(y)

    //match时间二元运算
    val a = 25
    val b = 13

    def matchDualOp(op: Char) = op match {
      case '+' => a + b
      case '-' => a - b
      case '*' => a * b
      case '/' => a / b
      case '%' => a % b
      case _ => "error"

    }

    println(matchDualOp('+'))
    println(matchDualOp('\\'))


    //模式守卫
    def abs(num: Int): Int = {
      num match {
        case i if i>=0 => i
        case i if i< 0 => -i
      }

    }

    println(abs(10))
    println(abs(-10))
  }

}
