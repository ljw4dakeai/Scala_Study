package chapter05

import org.apache.spark.{SparkConf, SparkContext}

object Test07_RDD_File_PartationPlus {
  def main(args: Array[String]): Unit = {
    //准备环境
    //TODO 准备环境
    val conf = new SparkConf()
      .setAppName("RDD")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)

    //TODO 创建RDD
    //TODO 数据分区分配
    //spark读取文件，采用行为单位
    //    采用Hadoop读取方法读取，一行一行读取，和字节数无关
    //读取数据以偏移量读取(偏移量不会被重新读取)
    //1@@ => 012
    //2@@ => 345
    //3   => 6
    //偏移量计算范围
    //0 => [0, 3] => 1 2
    //1 => [3, 6] =>3
    //2 => [6. 7]


    //如果数据有多个数据源，计算分区时会以文件为单位
    sc.textFile("src/main/resources/Test06.txt")
      .saveAsTextFile("src/main/Spark/chapter05/output")

    //TODO 关闭资源
    sc.stop()
  }
}
