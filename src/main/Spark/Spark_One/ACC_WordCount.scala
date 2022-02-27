package Spark_One

import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

object ACC_WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("WoedCount")
      .setMaster("local[*]")

    val sc = new SparkContext(conf)

    val RDD = sc.makeRDD(List("hello spark", "hello word", "hello scala"))
    val ACC = new Accmulator
    sc.register(ACC, "wordcount")
    /**
     * 创建累加器对象
     * 向spark注册
     * 数据使用累加器
     * 获取累计器结果
     */

    RDD.foreach(
      word => ACC.add(word)
    )

    println(ACC.value)

//    RDD.flatMap(line => line.split(" "))
//      .map((_,1))
//      .reduceByKey((num1, num2) => num1 + num2)
//      .collect()
//      .foreach(println)
  }


  class Accmulator extends AccumulatorV2[String, mutable.Map[String, Long]]{

    private var Map = mutable.Map[String, Long]()
    //zero初始状态
    override def isZero: Boolean = {
      Map.isEmpty
    }

    override def copy(): AccumulatorV2[String, mutable.Map[String, Long]] = {
      new Accmulator()
    }

    override def reset(): Unit = {
      Map.clear()
    }

    //获取累加器需要计算的值
    override def add(word: String): Unit = {
      val newCount = Map.getOrElse(word, 0L) + 1
      Map.update(word, newCount)
    }

    //合并多个累加器
    override def merge(other: AccumulatorV2[String, mutable.Map[String, Long]]): Unit = {
      val map1 = this.Map
      val map2 = other.value

      map2.foreach {
        case (word, count) => {
          val newcount = map1.getOrElse(word, 0L) + 1
          map1.update(word, newcount)
        }
      }
    }

    //累加器结果
    override def value: mutable.Map[String, Long] = {
      Map
    }
  }

}
