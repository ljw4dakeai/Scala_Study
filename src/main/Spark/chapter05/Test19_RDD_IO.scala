package chapter05

import org.apache.spark.{SparkConf, SparkContext}

object Test19_RDD_IO {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("Operator")
      .setMaster("local[*]")


    val sc = new SparkContext(conf)


//save

////1.saveAsTextFile
//
//
//    sc.makeRDD(List(("a", 1), ("b", 1), ("a", 2)))
//      .saveAsTextFile("src/main/Spark/chapter05/saveAsTextFile")
//
//
////2.saveAsObjectFile
//
//
//    sc.makeRDD(List(("a", 1), ("b", 1), ("a", 2)))
//      .saveAsObjectFile("src/main/Spark/chapter05/saveAsObjectFile")
//
//
//
////3.saveAsSequenceFile
//
//    //要求必须为Kv类型的数据
//
//    sc.makeRDD(List(("a", 1), ("b", 1), ("a", 2)))
//      .saveAsSequenceFile("src/main/Spark/chapter05/saveAsSequenceFile")

//load
////1.textFile
//    sc.textFile("src/main/Spark/chapter05/saveAsTextFile")
//      .collect()
//      .foreach(println)

////2.objectFile[(T, T)]
//
//    sc.objectFile[(String, Int)]("src/main/Spark/chapter05/saveAsObjectFile")
//      .collect()
//      .foreach(println)

//3.sequenceFile[T, T]
    sc.sequenceFile[String, Int]("src/main/Spark/chapter05/saveAsSequenceFile")
      .collect()
      .foreach(println)



  }

}
