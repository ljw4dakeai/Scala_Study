package chapter05

import org.apache.spark.{SparkConf, SparkContext}

object Test03_RDD_FilePlus {
  def main(args: Array[String]): Unit = {
    //准备环境
    //TODO 准备环境
    val conf = new SparkConf()
      .setAppName("RDD")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)


    //TODO 创建RDD
    //文件中创建

    //textFile : 以行为单位
    //wholeTetxFiles : 以文件为单位
    //结果 ： Tuple（文件路径， 文件内容）
    sc.wholeTextFiles("src/main/Scala/chapter05")
      .collect()
      .foreach(println)
  }
}
