//package chapter06
//
//object Test02_PackageObject {
//  def main(args: Array[String]): Unit = {
//    commonMethod()
//    println(commonvalue)
//  }
//}



package chapter06{
  object Test02_PackageObject {
    def main(args: Array[String]): Unit = {
      commonMethod()
      println(commonvalue)
    }
  }

}

//包对象和包必须在同一级目录下
package ccc{
  package bbb{
    object  Test02_PackageObject {
      def main(args: Array[String]): Unit = {
        println(school)
      }
    }
  }

  package object bbb{
    val school: String = "WHPU"
  }
}



