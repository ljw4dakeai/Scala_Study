package chapter06

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Top10_Change1 {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      .setAppName("Top10")
      .setMaster("local[*]")

    //TODO top10 热门品类前10
    val sc = new SparkContext(conf)
    //时间       用户Id   sessionID                      页面ID 动作时间            搜索关键字 点击ID  下单品类ID和产品ID  支付品类ID和产品ID
    //2019-05-05,85,e2eef06e-beaa-4b49-acaf-38e057e1cd6e,14,2019-05-05 01:53:25,       ,  14 ,62,        ,       ,       ,        ,   2
    //2019-05-05,85,e2eef06e-beaa-4b49-acaf-38e057e1cd6e,8,2019-05-05 02:02:38,        ,   -1,-1,        ,       ,  1-2-3,1-2-3   ,  20
    //2019-05-05,85,e2eef06e-beaa-4b49-acaf-38e057e1cd6e,31,2019-05-05 02:54:16,苹果    ,   -1,-1,        ,       ,       ,        ,  19
    //2019-05-05,85,e2eef06e-beaa-4b49-acaf-38e057e1cd6e,2,2019-05-05 02:21:38,        ,    -1,-1,  1-2-3,   1-2-3,      ,        ,   5
    //品类 点击数 下单数量 支付数量
    /*
    需求分析
    1. 统计品类的点击数
    2. 统计品类的下单数
    3. 统计品类的支付数
    4. 排序 点击数排序， 下单数量排序，支付数量排序
       元组排序为 比较第一个，再比较第二个，在比较第三个
       (品类, (点击数, 下单数, 支付数))
     */

    //Q : 1.RDD重复使用  -> RDD.chche()
    //    2.cogroup(有可能shuffle -> 数据量大)
    val RDD = sc.textFile("src/main/Spark/DataFile/user_visit_action.csv")
    //1. 统计品类的点击数(能过滤一定要先过滤)
    val ClickRDD = RDD.filter(
      string => {
        val datas =  string.split(",")
        datas(6) != "-1"
      }
    )
      .map(
        string => {
          val datas =  string.split(",")
          (datas( 6),1)
        }
      )
      .reduceByKey((num1, num2) => num1 + num2)


    //2. 统计品类的下单数
    val OrderRDD = RDD.filter(
      string => {
        val datas =  string.split(",")
        datas(8).nonEmpty
      }
    )
      .flatMap(
        string => {
          val datas =  string.split(",")
          val IDs = datas(8).split("-")
          IDs.map(string => (string, 1))
        }
      )
      .reduceByKey((num1, num2) => num1 + num2)

    //3. 统计品类的支付数
    val PayRDD = RDD.filter(
      string => {
        val datas =  string.split(",")
        datas(10).nonEmpty
      }
    )
      .flatMap(
        string => {
          val datas =  string.split(",")
          val IDs = datas(10).split("-")
          IDs.map(string => (string, 1))
        }
      )
      .reduceByKey((num1, num2) => num1 + num2)
//
//    //4. 排序 点击数排序， 下单数量排序，支付数量排序
//    //元组排序为 比较第一个，再比较第二个，在比较第三个
//    //(品类, (点击数, 下单数, 支付数))
//    //cogroup
//
//    val CoGroupRDD: RDD[(String, (Iterable[Int], Iterable[Int], Iterable[Int]))] = ClickRDD.cogroup(OrderRDD, PayRDD)
//    CoGroupRDD.mapValues {
//      //每个都会点击但是不一定会支付，我们需要统计的是支付的，如果支付那就是，一定下单了
//      case (click, order, pay) => {
//        var clickcou = 0
//        if (click.iterator.hasNext) {
//          clickcou = click.iterator.next()
//        }
//        var ordercou = 0
//        if (order.iterator.hasNext) {
//          ordercou = order.iterator.next()
//        }
//        var paycou = 0
//        if (pay.iterator.hasNext) {
//          paycou = pay.iterator.next()
//        }
//        (clickcou, ordercou, paycou)
//      }
//    }
//      .sortBy(_._2,false)
//      .collect()
//      .foreach(println)

    //(品类, (点击数, 下单数, 支付数))

    //(品类,  点击) => (品类, (点击数量, 0, 0))
    //        下单 => (品类, (点击数量, 下单数量, 0))
    //        支付 => (品类, (点击数量, 下单数量, 支付数量))
    val ClickRDDM: RDD[(String, (Int, Int, Int))] = ClickRDD.map {
      case (c, count) => (c, (count, 0, 0))
    }
    val OrderRDDM: RDD[(String, (Int, Int, Int))] = OrderRDD.map {
      case (c, count) => (c, (0, count, 0))
    }

    val PayRDDM: RDD[(String, (Int, Int, Int))] = OrderRDD.map {
      case (c, count) => (c, (0, 0, count))
    }

    //将三个数据源合并在一起统一进行聚合
    ClickRDDM.union(OrderRDDM).union(PayRDDM)
      .reduceByKey((num1, num2) => (num1._1+num2._1,num1._2 + num2._2, num1._3 + num2._3))
      .sortBy(value => value._2, false)
      .collect()
      .foreach(println)

    sc.stop()
  }

}
