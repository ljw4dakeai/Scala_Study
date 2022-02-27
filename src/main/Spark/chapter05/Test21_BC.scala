package chapter05

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable


//广播变量
object Test21_BC {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("Operator")
      .setMaster("local[*]")

    val sc = new SparkContext(conf)
    val map = mutable.Map(("q", 1), ("b",2), ("c",3))
    val bc = sc.broadcast(map) // ->广播变量 ->相当于通用数据可以放在一个缓存中

    val RDD1 = sc.makeRDD(List(("a",1),("b",2),("c",3) ))
//    val RDD2 = sc.makeRDD(List(("q", 1), ("b",2), ("c",3)))

//    //会导致数据量的集合增长，会影响shuffle的性能，不推荐使用
//    val joinRdd = RDD1.join(RDD2)
//    joinRdd.collect()
//      .foreach(println)
    //(c,1) ->    (b,2)->
    //(c,(3,3))   (b,(2,2))

    //闭包数据都是以Task为单位发送的，每个任务中都会包含闭包数据 -> 导致Executor中含有大量重复数据 -> 完全可以将闭包数据放入Executor中（不能修改）
    //Spark中的广播变量只读，不能修改
    RDD1.map {
      case (w, c) => {
        val l = map.getOrElse(w, 0)
        (w, (c, l))
      }
    }
      .collect()
      .foreach(println)

    RDD1.map{
      case (w, c) => {
        val l = bc.value.getOrElse(w, 0)
        (w, (c, l))
      }
    }
      .collect()
      .foreach(println)


    sc.stop()
  }





}
