package chapter05

object Test08_Pritice {
  def main(args: Array[String]): Unit = {
    val function1 = (i: Int, s: String, c: Char) => {if (i==0 && s== "" && c == '0') false else true}

    println(function1(0, "", '0'))
    println(function1(1, "", '0'))

    println("===========================")


    def function(i: Int):  String => (Char => Boolean)= {
      def f1(s: String): Char => Boolean = {
        def f2(c: Char): Boolean =  {
          if (i==0 && s== "" && c == '0') false else true
        }
        f2
      }
      f1
    }

    println(function(0)("")('0'))
    println(function(1)("hello")('0'))

    println("==============================")
    //匿名函数的简写
    def function2(i: Int):  String => (Char => Boolean)= {
      (s: String) => {
        (c: Char) => {
          if (i==0 && s== "" && c == '0') false else true
        }
      }
    }

    def function3(i: Int): String => (Char => Boolean) ={
      s => {c => {if (i==0 && s== "" && c == '0') false else true }}
    }


    def function4(i: Int): String => (Char => Boolean) = s => c => if (i==0 && s== "" && c == '0') false else true


    println(function2(0)("")('0'))
    println(function2(1)("hello")('0'))
    println("===========================")
    println(function3(0)("")('0'))
    println(function3(1)("hello")('0'))
    println("===========================")
    println(function4(0)("")('0'))
    println(function4(1)("hello")('0'))


    //柯里化
    def function5(i: Int)(s: String)(c: Char): Boolean = {
      if (i==0 && s== "" && c == '0') false else true
    }

    println("===========================")
    println(function5(0)("")('0'))
    println(function5(1)("hello")('0'))
  }

}
