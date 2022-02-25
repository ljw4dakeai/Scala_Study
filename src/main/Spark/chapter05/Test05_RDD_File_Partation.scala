package chapter05

import org.apache.spark.{SparkConf, SparkContext}

object Test05_RDD_File_Partation {
  def main(args: Array[String]): Unit = {

    //准备环境
    //TODO 准备环境
    val conf = new SparkConf()
      .setAppName("RDD")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)


    //TODO 创建RDD
    //默认设定分区
    //  minPartition ： 最小分区数量
    //  math.min(defaultParallelism, 2) spark底层为Hadoop的读取文件方式
    // 计算方式
    //totalsize = 字节数
    //goalsize = 7/2 = 3(byte)
    //7/3 = 2...1(1.1) 如果剩下的自己数大于分区字节数的10% 会产生新的分区
    sc.textFile("src/main/Scala/chapter05/Test01*")
      .saveAsTextFile("src/main/Spark/chapter05/output")

    //TODO 关闭资源
    sc.stop()
  }
}
