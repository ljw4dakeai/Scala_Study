package chapter05

import org.apache.spark.{SparkConf, SparkContext}

object Test12_RDD_Fuction_TransDoubleValue {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      .setAppName("Operator")
      .setMaster("local[*]")

    val sc = new SparkContext(conf)

    val RDD1 = sc.makeRDD(List(1, 2, 3, 4))
    val RDD2 = sc.makeRDD(List(3, 4, 5, 6))
    val RDD3 = sc.makeRDD(List("1", "22", "4", "6"))

//1.并集，差集，交集 要求数据类型一样
    //zip 不需要数据类型一样，但是要求两个数据源分区一样
    //    分区中数据数量也需要保持一致
    //交集
    //[3, 4 ]
    val rdd1 = RDD1.intersection(RDD2)
    println(rdd1.collect().mkString(" "))

    //并集
    //[1.2.3.4.5.6]
    val rdd2 = RDD1.union(RDD2)
    println(rdd2.collect().mkString(" "))


    //差集

    //[1, 2]
    val rdd3 = RDD1.subtract(RDD2)
    println(rdd3.collect().mkString(" "))
    //[5,6]
    val rdd4  = RDD2.subtract(RDD1)
    println(rdd4.collect().mkString(" "))


    //zip
    //[1,3] [2,4] [3, 5] [4, 6]
    val rdd5 = RDD1.zip(RDD2)
    println(rdd5.collect().mkString(" "))

    val rdd6 = RDD1.zip(RDD3)
    println(rdd6.collect().mkString(" "))

    sc.stop()

  }

}
