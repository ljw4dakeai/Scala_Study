package chapter05

object Test03_FuctionParaameter {
  def main(args: Array[String]): Unit = {
    //可变参数
    def f1(str: String*): Unit = {
      println(str)
    }

    f1("aaa")
    f1("aaa", "bbb")

    //固定参数放在头部
    def f2(str: String, strs: String*): Unit ={
      println(str + strs)
    }
    f2("alice")
    f2("aaa", "bbbb", "cccc")

    //参数默认值，放在尾部
    def f3(str: String, name: String = "zoujiahao"): Unit = {
      println(name + str)
    }

    f3("nihao")
    f3("nihao", "whpu")

    //带名参数
    def f4(name: String, age: Int): Unit ={
      println(s"$name 现在  $age 啦")
    }

    f4("zoujiahao", 21)
    f4(age = 21 , name = "zoujiahao")
  }

}
