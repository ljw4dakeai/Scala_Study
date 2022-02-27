package chapter05

import org.apache.spark.{SparkConf, SparkContext}

//触发作业调度和执行

object Test09_RDD_Fuction_Active {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("Operator")
      .setMaster("local[*]")


    val sc = new SparkContext(conf)
////1.collect (采集) 行动算子 ->触发作业执行
//    //把不同分区的数据按照分区数据采集到driver端内存中，形成数组
//
////    //底层代码调用的是对象的runjob方法
////    //底层会创建activejob，并且submitjob（提交作业）
////    sc.makeRDD(List(1, 2, 3, 4))
////
////      .collect()
//
////2.reduce
//
////    println(sc.makeRDD(List(1, 2, 3, 4))
////      .reduce(_ + _))
//
////3.count
//
////    //数据源中数据的个数
////    println(sc.makeRDD(List(1, 2, 3, 4))
////      .count())
//
//
////4.first
//
////    //数据源中数据的第一个
////    println(sc.makeRDD(List(1, 2, 3, 4))
////      .first())
//
////5.take
//
//    //获取n个数据
//    println {
//      sc.makeRDD(List(1, 2, 3, 4))
//        .take(3)
//        .mkString(",")
//    }
//
//
//
////6.takeOrdere
//
//    //排序后取n个数据
//    println {
//      sc.makeRDD(List(1, 4, 2, 3))
//        .takeOrdered(3)
//        .mkString(",")
//    }
//
////7.aggregate
//
//    //aggregateBykey只会参加分区内计算
//    //aggregate 会参与分区内计算，并且参与分区见计算
//    println {
//      sc.makeRDD(List(1, 2, 3, 4))
//        .aggregate(0)(_ + _, _ + _)
//    }
//
//
////8.fold
//
//    println {
//      sc.makeRDD(List(1, 2, 3, 4))
//        .fold(0)(_ + _)
//    }
//
////9.countByvalue
//
//    println {
//      sc.makeRDD(List(1, 2, 3, 4))
//        .countByValue()
//    }
//
////10.countByKey
//
//    println {
//      sc.makeRDD(List(("a", 1), ("b", 1), ("a", 2)))
//        .countByValue()
//    }


//10.saveAsTextFile


    sc.makeRDD(List(("a", 1), ("b", 1), ("a", 2)))
      .saveAsTextFile("src/main/Spark/chapter05/saveAsTextFile")


//10.saveAsObjectFile


    sc.makeRDD(List(("a", 1), ("b", 1), ("a", 2)))
      .saveAsObjectFile("src/main/Spark/chapter05/saveAsObjectFile")



//10.saveAsSequenceFile

    //要求必须为Kv类型的数据

    sc.makeRDD(List(("a", 1), ("b", 1), ("a", 2)))
      .saveAsSequenceFile("src/main/Spark/chapter05/saveAsSequenceFile")


//11.foreach







    sc.stop()
  }
}
