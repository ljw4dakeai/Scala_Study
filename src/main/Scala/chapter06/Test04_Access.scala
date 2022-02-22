package chapter06

object Test04_Access {
  def main(args: Array[String]): Unit = {
    val person = new Person()
//    println(person.idcorf)
//    println(person.name)
    println(person.age)
    println(person.sex)
    person.printerInfo()

    val work = new work()
    work.printerInfo()
  }
}

class  work extends  Person {
  override def printerInfo(): Unit = {
    println("worker")
//    println(idcorf)
    name = "ljw"
    age = 21
    sex = "meal"
    println(s"${name} ${age} ${sex}")
  }
}
