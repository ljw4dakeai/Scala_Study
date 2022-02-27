package Spark_One

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

object RDD_ManyWordCount {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("WoedCount")
      .setMaster("local[*]")


    val sc = new SparkContext(conf)

//1.groupby

    sc.makeRDD(List("hello spark", "hello word", "hello scala"))
      .flatMap(line => line.split(" "))
      .groupBy(word => word)
      .mapValues(iter => iter.size)
      .collect()
      .foreach(println)


//2.groupbykey (shuffle)

    sc.makeRDD(List("hello spark", "hello word", "hello scala"))
      .flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .groupByKey()
      .mapValues(iter => iter.size)
      .collect()
      .foreach(println)


//3.reducebykey

    sc.makeRDD(List("hello spark", "hello word", "hello scala"))
      .flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey((word1,word2) => word1 + word2)
      .collect()
      .foreach(println)

//4.aggregateBykey

    sc.makeRDD(List("hello spark", "hello word", "hello scala"))
      .flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .aggregateByKey(0)((word1, word2) => word1 + word2,(word1, word2) => word1 + word2 )
      .collect()
      .foreach(println)


//5.foldBykey

    sc.makeRDD(List("hello spark", "hello word", "hello scala"))
      .flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .foldByKey(0)((word1, word2) => word1 + word2 )
      .collect()
      .foreach(println)

//6.combineByKey

    sc.makeRDD(List("hello spark", "hello word", "hello scala"))
      .flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .combineByKey(
        v => v,
        (x: Int, y) => x + y,
        (x: Int, y: Int) => x + y
      )
      .collect()
      .foreach(println)

//7.countByKey

    println{
      sc.makeRDD(List("hello spark", "hello word", "hello scala"))
        .flatMap(line => line.split(" "))
        .map(word => (word, 1))
        .countByKey()
        .mkString(",")}

//8.countByValue

    println{
      sc.makeRDD(List("hello spark", "hello word", "hello scala"))
        .flatMap(line => line.split(" "))
        .countByValue()
        .mkString(",")}

//8.reduce

    println {
      sc.makeRDD(List("hello spark", "hello word", "hello scala"))
        .flatMap(line => line.split(" "))
        .map(word => mutable.Map[String, Long]((word, 1)))
        .reduce(
          (map1, map2) => {
            map2.foreach {
              case (word, count) => {
                val newCount = map1.getOrElse(word, 0L) + count
                map1.update(word, newCount)
              }
            }
            map1
          }
        )
    }


    sc.stop()


  }
}
