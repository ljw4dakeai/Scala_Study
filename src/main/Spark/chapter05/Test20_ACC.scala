package chapter05


//累加器
import org.apache.spark.{SparkConf, SparkContext}

object Test20_ACC {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("Operator")
      .setMaster("local[*]")


    val sc = new SparkContext(conf)
//reduce: 分区内计算，分区间计算
//    println(sc.makeRDD(List(1, 2, 3, 4))
//      .reduce(_ + _))


//    var sum = 0
//    val RDD = sc.makeRDD(List(1, 2, 3, 4))
//    RDD.foreach(
//      num => sum + num
//    )
//    println("sum" + sum ) //=> 0


    //获取系统累加器
    //spark默认提供了简单数据聚合的累加器
    val sumACC = sc.longAccumulator("sum")
//    sc.longAccumulator()
//    sc.doubleAccumulator()
//    sc.collectionAccumulator
    val RDD = sc.makeRDD(List(1, 2, 3, 4))
    RDD.foreach(
      num => sumACC.add(num)
      //sumAcc.add(num)
      //num ==> 如果转换算子中调用累加器，如果没有行动算子，也不会执行累加器=> 少加
      //        如果转换算子中调用累加器，如果有行动算子，会执行两次累加器 => 多加
      //        一般情况下，累加器会放入行动算子中
    )
    println("sum" + sumACC.value) //=> 0





  }
}
