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


////10.saveAsTextFile
//
//
//    sc.makeRDD(List(("a", 1), ("b", 1), ("a", 2)))
//      .saveAsTextFile("src/main/Spark/chapter05/saveAsTextFile")
//
//
////10.saveAsObjectFile
//
//
//    sc.makeRDD(List(("a", 1), ("b", 1), ("a", 2)))
//      .saveAsObjectFile("src/main/Spark/chapter05/saveAsObjectFile")
//
//
//
////10.saveAsSequenceFile
//
//    //要求必须为Kv类型的数据
//
//    sc.makeRDD(List(("a", 1), ("b", 1), ("a", 2)))
//      .saveAsSequenceFile("src/main/Spark/chapter05/saveAsSequenceFile")


//11.foreach

//    //算子：Operator(操作)
//    //     RDD方法和Scala集合对象方法不一样
//    //     集合对象方法是在同一个节点完成的。
//    //     RDD方法可以将计算逻辑发送给Executor端（分布式节点）并行执行
//    //     RDD-> 算子（方法外部操作都在Driver中执行），方法内部逻辑都在Executor端执行
//
//    //foreach driver端内存集合循环遍历 发送给executor后采集回来执行打印
//    sc.makeRDD(List(("a", 1), ("b", 1), ("a", 2)))
//      .collect()
//      .foreach(println)
//    println("===============")
//
//
//    sc.makeRDD(List(("a", 1), ("b", 1), ("a", 2)))
//      .foreach(println)

    val User = new User()
    //Task not serializable -> java.io.NotSerializableException: chapter05.User
    //Drive => Executor 传输需要
    //RDD算子中，会包涵闭包操作，会进行检测功能 ->闭包检测
    sc.makeRDD(List(1, 2, 3, 4))
      .foreach(num => {//executor端内存中进行 数据打印
        println("age = " + (num + User.age))
      })


    sc.stop()
  }
}


////不序列化就会报错
//class User() extends Serializable {
//  var age = 30
//}

//样例类在编译时会自动混入序列化特质（实现序列化接口）
case class User(){
  var age = 30
}
