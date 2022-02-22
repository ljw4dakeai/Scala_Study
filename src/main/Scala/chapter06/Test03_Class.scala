package chapter06

import chapter01.Student

import scala.beans.BeanProperty


object Test03_Class{
  def main(args: Array[String]): Unit = {
    val student = new Studen()
    println(student.age) //0
    println(student.name) //null
    student.sex = "female"
    println(student.sex)

  }
}



class Test03_Class {

}

//定义一个类
class Studen{
  //定义属性,下划线标示空，或者0，默认值通配符
  var name: String = _
  @BeanProperty //实现get和set方法
  var age: Int = _
  var sex: String = _
}