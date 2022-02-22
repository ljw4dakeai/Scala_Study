package chapter02

object Test04_String {
  def main(args: Array[String]): Unit = {

    val name: String = "zjh"
    val age: Int = 20
    println(name +  age + "岁在whpu学习")

    println(name * 3)


    printf("%d%s岁在whpu学习\n", age, name)//和c一样


    //字符串模版

    println(s"${age}${name}岁在whpu学习")


    val num: Double = 2.3456
    val num_ : Float = 2.3456f

    //格式化模版字符串
    println(f"the num is ${num}%2.2f")

    //不考虑任何格式化，直接输出全部值
    println(raw"the num is ${num}%2.2f")


    //三引号标示字符串，保持多行原格式输出
    val sql =
    s"""
       |select *
       |from
       |  student
       |where
       |  name = ${name}
       |and
       |  age > ${age}
       |""".stripMargin
    println(sql)


  }

}
