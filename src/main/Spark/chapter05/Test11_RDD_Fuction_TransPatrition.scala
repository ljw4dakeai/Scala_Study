package chapter05

import org.apache.spark.{SparkConf, SparkContext}

object Test11_RDD_Fuction_TransPatrition {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("Operator")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)

//    //RDD计算
//    //  1.一个分区，一个一个执行,只有前面的数据逻辑全部执行完毕后，才会执行下一个数据
//    //  ->分区内数据执行是有序的
//    //  2.不同分区之间是无序的

//    sc.makeRDD(List(1, 2, 4, 5))
//      .map(num => {
//        println("num1:" + num)
//        num * 2
//      })
//      .map(num => {
//        println("num2:" + num)
//        num * 2
//      })
//      .collect()
//      .foreach(println)

    val rdd = sc.makeRDD(List(1, 2, 4, 5), 2)
      rdd.map(_*2)
      rdd.saveAsTextFile("src/main/Spark/chapter05/output")
    val mapRdd = rdd.map(_*2)
    mapRdd.saveAsTextFile("src/main/Spark/chapter05/output1")

    sc.stop()
  }
}
