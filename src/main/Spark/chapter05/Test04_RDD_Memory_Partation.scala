package chapter05

import org.apache.spark.{SparkConf, SparkContext}

object Test04_RDD_Memory_Partation {
  def main(args: Array[String]): Unit = {
    //准备环境
    //TODO 准备环境
    val conf = new SparkConf()
      .setAppName("RDD")
      .setMaster("local[*]")
      .set("spark.default.parallelism", "5") //分区，可配置参数
    val sc = new SparkContext(conf)

    //TODO 创建RDD
    //RDD的并行度 && 分区
    //makeRDD 方法可以传入两个参数， 第二个参数表示分区数量
    //第二个参数可以不传，默认值为配置参数 totalCores（当前环境的最大核心数，或者配置文件）
    sc.makeRDD(
      List(1, 22, 2, 4)
    )
      .saveAsTextFile("src/main/Spark/chapter05/output")

    //TODO 关闭资源
    sc.stop()
  }
}
