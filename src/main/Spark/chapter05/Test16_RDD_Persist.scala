package chapter05

//若果一个RDD，需要重复使用，那么需要从头执行获取数据

//RDD对象重用，但是数据不会重复用
//但是可以把数据缓存起来 -> 持久化操作 -> 不一定要进行多次重操作的时候才使用，数据量大，数据重要时以可以使用
//RDD缓存cache -> 内存当中 MEMORY_ONLY
//      persist-> 磁盘当中 (很多方式)
//      行动算子执行时进行缓存操作

import org.apache.spark.{SparkConf, SparkContext}

object Test16_RDD_Persist   {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("WoedCount")
      .setMaster("local[*]")


    val sc = new SparkContext(conf)


//    sc.makeRDD(List("hello spark", "hello word", "hello scala"))
//      .flatMap(line => line.split(" "))
//      .map((_,1))
//      .reduceByKey(_+_)
//      .collect()
//      .foreach(println)
//
//    sc.makeRDD(List("hello spark", "hello word", "hello scala"))
//      .flatMap(line => line.split(" "))
//      .map((_,1))
//      .groupByKey()
//      .collect()
//      .foreach(println)

    val  mapRDD =
      sc.makeRDD(List("hello spark", "hello word", "hello scala"))
        .flatMap(line => line.split(" "))
        .map((_,1))

    println("==============")
    mapRDD.cache()
    mapRDD.reduceByKey(_+_)
      .collect()
      .foreach(println)

    println("++++++++++++")
    mapRDD.groupByKey()
      .collect()
      .foreach(println)


  }
}
