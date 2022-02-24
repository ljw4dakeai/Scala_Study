package chapter07

import scala.collection.immutable.Queue
import scala.collection.mutable

object Test19_Queue {
  def main(args: Array[String]): Unit = {
    val queue = mutable.Queue[String]()

    queue.enqueue("a", "b", "c")
    println(queue)

    queue.dequeue()
    println(queue)

    queue.dequeue()
    println(queue)

    queue.enqueue("d","e")

    queue.dequeue()
    println(queue)

    val queue2 = Queue("a", "b", "c")
    val queue3 = queue2.enqueue("d")
    println(queue2)
    println(queue3)
  }
}
