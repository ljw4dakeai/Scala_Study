package chapter05



import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object Test13_RDD_Fuction_TransKeyValue {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("Operator")
      .setMaster("local[*]")

    val sc = new SparkContext(conf)

//1.PartitionBy

//    sc.makeRDD(List(1, 2, 3, 4))
//      .map(num => (num,1))
//      .partitionBy(new HashPartitioner(2)) // RDD => pairRDDFuctions (隐士转换) 指定分区规则，在一个分区进行重分区
//      //               RangePartitioner ->排序用的比较多
//      // ｜｜｜源码分区器
////      class HashPartitioner(partitions: Int) extends Partitioner {
////        require(partitions >= 0, s"Number of partitions ($partitions) cannot be negative.")
////
////        def numPartitions: Int = partitions
////
////        def getPartition(key: Any): Int = key match {
////          case null => 0
////          case _ => Utils.nonNegativeMod(key.hashCode, numPartitions)
////        }
////
////        override def equals(other: Any): Boolean = other match {
////          case h: HashPartitioner =>
////            h.numPartitions == numPartitions
////          case _ =>
////            false
////        }
////
////        override def hashCode: Int = numPartitions
////      }
//      //可以自定义分区器
//      .saveAsTextFile("src/main/Spark/chapter05/output")


//2.ReduceBykey

//    //相同的Key进行value的聚合操作
//    //Scala一般是两两聚合 -> sparok 也是
//    //reduceBykey 如果只有一个数据的时候不会聚合，直接返回
//    sc.makeRDD(List(
//      ("a",1), ("b", 2), ("a", 3), ("b", 4),("c", 10), ("a" , 20)
//    ))
//
//      .reduceByKey((x, y) => {
//        println(s"x = $x; y =  ${y}")
//        x + y})
//      .collect()
//      .foreach(println )


//3.GroupBYkey,

//    相同key的数据放在一个元组中，形成对偶元组
//    (key,(val1, val2))
//
//    sc.makeRDD(List(
//      ("a",1), ("b", 2), ("a", 3), ("b", 4),("c", 10), ("a" , 20)
//    ))
////      .groupByKey()
////      (a, (a,1) (a,2) (a,3))
//      .groupBy(tuple => tuple._1)
////      (a, (1, 2, 3))
//      .collect()
//      .foreach(println)


    //groupByKey 会将数据打乱重新组合，存在shuffle操作(必须落盘操作 不然会导致内存溢出)

    //Reducebyke 分区内聚合（分区内进行预处理数据），（落盘减少）shuffle中就不有那么多数据 增加性能 ，性能比GroupBykey好

    //reduce，可以分组也可以聚合 ，group只能聚合，在需要运用到聚合的时候推荐使用ReduceBykey



//4.aggregateByKey

//    //存在函数柯里化，有两个参数列表
//    //第一个参数
//    //  主要用于碰见第一个Key的时候，和value进行分区内计算
//    //第二个参数列表
//    //  第一个参数 -> 分区内计算规则
//    //  第二个参数 -> 分区间计算规则
//    sc.makeRDD(List(
//      ("a",1), ("b", 2), ("a", 3), ("b", 4)
//    ), 2)
//      //aggregateByKey返回的数据，应该和初始值类型一致
//      //  def aggregateByKey[U: ClassTag](zeroValue: U, partitioner: Partitioner)(seqOp: (U, V) => U,
//      //      combOp: (U, U) => U): RDD[(K, U)]
////      .aggregateByKey(0)(
////        (x, y) => math.max(x, y) ,
////        (x, y) => x + y
////      )
//      //获取形同key的平均值
//      .aggregateByKey((0.0, 0.0))(
//        (tuple, v) => (tuple._1 + v , tuple._2 + 1), //(tuple._1 + v string后面数的和 ， tuple._v + 1 string的个数)
//        (tuple1, tuple2) => (tuple1._1 + tuple2._1, tuple1._2 + tuple2._2)
//      )
//      .mapValues(
//        tuple => tuple match {
//      case (total, count) => total / count
//    }
//      )
//      .collect()
//      .foreach(println)


//5.foldBYkey

//    //  分区内和分区见计算规则相同
//    sc.makeRDD(List(
//      ("a",1), ("b", 2), ("a", 3), ("b", 4)
//    ), 2)
//      .foldByKey(0)(
//        (x,y) => x + y
//      )
//      .collect()
//      .foreach(println)



//6.conbineByKey

    //需要三个参数
    //  1.相同的Key进行数据转换，实现操作
    //  2.分区内的计算规则
    //  3.分区见的计算规则

    sc.makeRDD(List(
      ("a",1), ("b", 2), ("a", 3), ("b", 4)
    ), 2)
      .combineByKey(
        v => (v, 1),
        (tuple: (Int, Int), v) => (tuple._1 + v , tuple._2 + 1), //(tuple._1 + v string后面数的和 ， tuple._v + 1 string的个数)
        (tuple1: (Int, Int), tuple2: (Int, Int)) => (tuple1._1 + tuple2._1, tuple1._2 + tuple2._2)
      )
      .collect()
      .foreach(println)
    sc.stop()

  }
}
