package chapter05

import org.apache.spark.{Partitioner, SparkConf, SparkContext}

object Test18_RDD_Part {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      .setAppName("WordCount")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)
//    sc.makeRDD(List("zoujiahao", "nihao", "zoujiahao", "nihao"))
//      .map((_, 1))
//      .partitionBy(new part)
//      .saveAsTextFile("src/main/Spark/chapter05/output")

    sc.makeRDD(List(("spark",1), ("spark", 2) ,("nihao",3), ("zoujiahao", 4)))
      .partitionBy(new part)
      .saveAsTextFile("src/main/Spark/chapter05/output")

  }


  /**
   *自定义分区器
   * 1.继承Partitioner
   * 2.重写方法

   **/
  class part extends Partitioner{
    //分区数量
    override def numPartitions: Int = 3

    //根据数据的key返回数量分区索引
    override def getPartition(key: Any): Int = {
      key match {
        case "zoujiahao" => 0
        case "nihao" => 1
        case _ => 2
      }
    }
  }

}

