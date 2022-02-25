package chapter05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Test01_RDD_Memory {
  def main(args: Array[String]): Unit = {
    //准备环境
    //TODO 准备环境
    val conf = new SparkConf()
      .setAppName("RDD")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)

    //TODO 创建RDD
    //内存中创建

    val seq = Seq[Int](1, 2, 3, 4)

//    val read: RDD[Int] = sc.parallelize(seq) //并行

    val rdd: RDD[Int] = sc.makeRDD(seq) //底层调用parallize方法


    rdd.collect().foreach(println)

    sc.stop()
  }

}
