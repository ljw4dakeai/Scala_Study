package chapter01
/*
  object：关键字，声明一个单例对象，全局只有一个（伴生对象）为了实现静态功能
 */
object Helloword {
  //main 方法：从外部可以直接调用的方法
  // def 方法名称(参数名称： 参数类型) ：返回值类型 = {方法体}
  def main(args: Array[String]): Unit = {
    println("hello word")
    System.out.println("nihao")
  }
}
