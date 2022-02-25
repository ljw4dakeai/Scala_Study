package chapter05

import org.apache.spark.{SparkConf, SparkContext}

object Test06_RDD_Memory_PartationPlus {
  def main(args: Array[String]): Unit = {
    //准备环境
    //TODO 准备环境
    val conf = new SparkConf()
      .setAppName("RDD")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)

    //TODO 创建RDD

    //[1,2] [3,4] 对          [1,3] [2,4]错
    //[1] [2] [3, 4]
    //[1] [2,3] [4,5]


    //len = 5 (数组大小)
    //num = 3 (分区个数)
    // 0 => (0, 1) 1
    //1 =>  (1, 3) 2,3
    //2 =>  (3, 5) 4.5
    sc.makeRDD(
      List(1, 22, 2, 4), 2
    )
      .saveAsTextFile("src/main/Spark/chapter05/output")

    //TODO 关闭资源
    sc.stop()
  }
}
