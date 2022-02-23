package chapter06

//从右到左叠加
object Test15_TraitOverlying {
  def main(args: Array[String]): Unit = {
    val student15: Student15 = new Student15()
    student15.increase()
    println("========================")

    //砖石叠加顺序，
    val myFootBall: MyFootBall = new MyFootBall()
    println(myFootBall.disc())
  }
}

//定义球类方法
trait ball {
  def disc(): String = "ball"
}

//定义球的颜色特征
trait colorball extends ball{
  val color: String = "read"
  override def disc(): String = {
    super.disc() + color

  }
}

//定义球的种类
trait categoryball  extends ball{
  val category: String = "foot"
  override def disc(): String = {
    super.disc() + category
  }

}

//myclass -> categoryball -> colorball -> ball (叠加)
class MyFootBall extends colorball with categoryball {
  override def disc(): String = super.disc()
}













trait Knowledge15 {
  var amount: Int = 0
  def increase(): Unit = {
    println("knowledge increase")
  }
}


trait Talent15 {
  def singing(): Unit
  def danceing(): Unit
  def increase(): Unit = {
    println("Talent increase")
  }
}

class Student15 extends Person13 with Talent15 with Knowledge15{
  override def danceing(): Unit = {
    println("student is goot at danceing")
  }

  override def singing(): Unit = {
    println("student is goot at singing")
  }


  override def increase(): Unit = {
    super.increase() //调用最后一个特征对应的方法，最后一个特征方法
  }
}