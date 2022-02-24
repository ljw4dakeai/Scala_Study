package chapter09plus

object Test03_Generics {
  def main(args: Array[String]): Unit = {
    val child: Parent = new Child
//    val childList: Mycollection[Parent] = new Child //class Mycollection[+E] {}
//    val childList: Mycollection[Child] = new Parent //class Mycollection[-E] {}

    def Test[A <: Child](a: A): Unit ={ //上限为Child
      println(a.getClass.getName)
    }
//    Test[Child](new Parent)//
    Test[SubChild](new SubChild) //ok
    Test[Child](new Child) //ok
  }
}

class Parent{}
class Child  extends Parent{}
class SubChild extends Child {}

class Mycollection[E] {}