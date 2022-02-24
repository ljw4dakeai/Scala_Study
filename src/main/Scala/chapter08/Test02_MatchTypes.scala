package chapter08

object Test02_MatchTypes {

  def main(args: Array[String]): Unit = {
    //匹配常量
    def describedConst(x: Any): String = x match {
      case 1=> "int 1"
      case "hello" => "string hello"
      case true => "boolean true"
      case 'c' => "char"
      case _ => "other"
    }

    println(describedConst("hello"))
    println(describedConst('c'))
    println(describedConst(3))

    //匹配类型
    def describedType(x: Any): String = x match {
      case i: Int => "int" + i
      case s: String => "string" + s
      case list: List[String] => "list[string]" + list
      case array: Array[Int] => "array[int]" + array.mkString(" ")
      case _ => "other"
    }

    println(describedType(1))
    println(describedType("hello"))
    println(describedType(List("zoujiahao")))
    println(describedType(List(2, 23))) //泛型类型擦除
    println(describedType(Array(12, 223)))
    println(describedType(Array("1", "2"))) // 基本数据类型，不会擦除


    //匹配数组
    for (array <- List(
      Array(0),
      Array(1, 0),
      Array(0, 1, 0),
      Array(1, 1, 0),
      Array(2, 3, 5, 7),
      Array("hello", 1, 20)
    )){

      val result = array match {
        case Array(0) => "0"
        case Array(1, 0) => "Array(1, 0)"
        case Array(x, y) => "Arrray" + x + "," + y
        case Array(0,_*) => "以0开头的数组"
        case Array(x, 1, y) => "中间为1的三元数组"
        case _ => "other"
      }

      println(result)
    }

    //匹配list方式一
    for (list <- List(
      List(0),
      List(1,0),
      List(0, 0, 0),
      List(1, 1, 0),
      List(88)
    )){
      val rsult = list match {
        case List(0) => "0"
        case List(x, y) => "List(x,y )" + x + "," + y
        case List(0, _*) => "以0开头的List"
        case List(x) => "List(a)" + x
        case _ => "other"
      }
      println(list)
    }

    //匹配list方式二
    val list: List[Int] = List(1, 2, 5, 7, 24)
    val list1 = List(1)
    list1 match {
      case first :: second :: rest => println(s"first$first, second $second, rest $rest")
      case _ => println("other")
    }

    //元组匹配
    for( tuple <- List(
      (0,0),
      (0, 1),
      (0, 0),
      (1, 2, 4),
      (0.5, 100),
      ("hello", 100)

    )){
      val result = tuple match {
        case (a, b) => "" + a + "," + b + ""
        case (0,_) => "(0, _)" //元组无法使用_，因为元组为固定的，无法改变
        case (a, 1, _) => "(a, 1, _) " + a
        case _ => "other"
      }
      println(result)
    }


  }
}
