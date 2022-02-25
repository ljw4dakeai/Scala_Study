package chapter05

import org.apache.spark.{SparkConf, SparkContext}

object Test02_RDD_File {
  def main(args: Array[String]): Unit = {
    //准备环境
    //TODO 准备环境
    val conf = new SparkConf()
      .setAppName("RDD")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)


    //TODO 创建RDD
    //文件中创建
    //可以是现对路径，也可以是绝对路径（默认是根路径）
//    sc.textFile("src/main/resources/Test06.txt")
//      sc.textFile("src/main/resources") //可以是一个目录（但是目录下面不可以有目录）
      sc.textFile("src/main/Scala/chapter05/Test1*") //可以是一个路径下面的通配符文件Test1*表示示前面是Test1的所有文件
      .collect()
      .foreach(println)

    sc.stop()
  }
}
