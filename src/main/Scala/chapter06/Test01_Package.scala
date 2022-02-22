
//包可以创建

package zou{

  //局部导入，(some=>sm)别名  (ArrayList=>_)屏蔽导入，不导入Arraylist
  import zou.jia.hao.jiahao
  object zoujia{

    var name: String = "zoujiahao"
    def main(args: Array[String]): Unit = {
      println(jiahao.jiahao)
    }
  }
  package jia{
    package hao{
      object jiahao{
        val jiahao: String = "jiahao"
        def main(args: Array[String]): Unit = {
          println(zoujia.name)
          println("===========================")
          zoujia.name = "zoujiahao666"
          println(zoujia.name)
        }
      }
    }
  }
}


package package2{
  object packages {

    def main(args: Array[String]): Unit = {
      println(zou.zoujia.name)
    }
  }
}
