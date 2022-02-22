package chapter06

object Test04_ClassForAccess {

}


class Person {
  private var idCorf: String = "11111111"
  protected var name: String = "zoujiahao"
  var sex: String = "female"
  private [chapter06] var age = 20

  def printerInfo(): Unit = {
    println("「信息： " + "\n" +
      idCorf + "\n" +
      name + " \n" +
      sex + "\n" +
      age + "\n"  )
  }
}