package chapter06

object Test14_TraitMixin {
  def main(args: Array[String]): Unit = {

    val student14: Student14 = new Student14()

    student14.study()
    student14.increase()
    println("===============")
    student14.yong()
    student14.increase()
    println("===============")
    student14.sayHi()
    student14.increase()
    println("===============")
    student14.dating()
    student14.increase()
    println("==========================================")


    //动态混入
    val studentwithtalent  = new Student14 with Talent{
      override def danceing(): Unit = {
        println("student is goot at danceing")
      }

      override def singing(): Unit = {
        println("student is goot at singing")
      }
    }

    studentwithtalent.danceing()
    studentwithtalent.singing()
    studentwithtalent.dating()

  }
}


//再定义一个特质
trait Knowledge {
  var amount: Int = 0
  def increase(): Unit
}


trait Talent {
  def singing(): Unit
  def danceing(): Unit
}
class Student14 extends Person13 with yong with Knowledge {
  //重写冲突属性
  override val name: String =  "zoujiahao"
  override def dating(): Unit = println(s"${name} dating")

  def study(): Unit = println(s"${name} studing")

  override def sayHi(): Unit = {

    super.sayHi()
    println("hello student")
  }

  override def increase(): Unit = {
    amount += 1
    println(s"student ${name} increase $amount")
  }
}