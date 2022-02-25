package chapter05

import org.apache.spark.{SparkConf, SparkContext}

object Test10_RDD_Fuction_TransTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("Operator")
      .setMaster("local[*]")

    val sc = new SparkContext(conf)
//1.map
//    sc.textFile("src/main/resources/salecourse.log")
////      .map(line=> {
////        val lines = line.split(",")
////
////        lines(6)})
//      .map(_.split(",")(5))
//      .saveAsTextFile("src/main/Spark/chapter05/output")

//2.mapPartitions(分区内最大值)
//    sc.makeRDD(List(1, 2, 3, 4), 2)
//      .mapPartitions(
//        iter => {
//          List(iter.max).iterator
//
//        }
//      )
//      .collect()
//      .foreach(println)

//3.glom(计算分区最大值，最大值求和)

//    sc.makeRDD(List(1, 2, 3, 4), 2)
//      .glom()
//      .map(array => array.max)
//      .collect()
//      .foreach(println)



//4.groupby

//    sc.textFile("src/main/resources/salecourse.log", 8)
//      .groupBy(string => string.split(",")(0).split(":")(1))
//      .map(string=>  string match {
//        case (string ,iter) => (string, iter.size)
//    })
//      .collect()
//      .foreach(println)
////      .saveAsTextFile("src/main/Spark/chapter05/output")


//5.falter(可能会数据倾斜)
    sc.textFile("src/main/resources/salecourse.log")
      .filter(lines => lines.split(",")(0).split(":")(1) == "1")
      .collect()
      .foreach(println)


    sc.stop()
  }
}
