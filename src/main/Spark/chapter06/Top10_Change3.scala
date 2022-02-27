package chapter06

import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

object Top10_Change3 {
  //累加器实现(不需要shuffle，效率最高)
  case class Top(cid: String, var click: Int, var order: Int,var pay: Int ){

  }

  //IN: CID(产品种类) OUTmutable.Map[String, Top]  (产品种类,(click, order, pay))
  class  TopAcc extends AccumulatorV2[(String, String), mutable.Map[String,Top]]{
    private val Map = mutable.Map[String,Top]()

    override def isZero: Boolean = {
      Map.isEmpty
    }

    override def copy(): AccumulatorV2[(String, String), mutable.Map[String, Top]] = {

      new TopAcc
    }

    override def reset(): Unit = {
      Map.clear()
    }

    override def add(v: (String, String)): Unit = {

      val c = v._1
      val Type = v._2
      val top = Map.getOrElse(c, Top(c,0,0,0))
      Type match {
        case "click" => top.click += 1
        case "order" => top.order += 1
        case "pay"   => top.pay += 1
      }
      Map.update(c, top)
    }

    override def merge(other: AccumulatorV2[(String, String), mutable.Map[String, Top]]): Unit = {
      val map1 = this.Map
      val map2 = other.value

      map2.foreach {
        case (c, top) => {
          val topg = map1.getOrElse(c, Top(c, 0, 0, 0))
          topg.click += top.click
          topg.order += top.order
          topg.pay += top.pay
          map1.update(c, topg)
        }
      }
    }

    override def value: mutable.Map[String, Top] = {
      Map
    }
  }

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      .setAppName("Top10")
      .setMaster("local[*]")

    //TODO top10 热门品类前10
    val topAcc = new TopAcc()

    val sc = new SparkContext(conf)
    sc.register(topAcc, "topAcc")
    val RDD = sc.textFile("src/main/Spark/DataFile/user_visit_action.csv")
    RDD.foreach(
      string => {
        val daatas = string.split(",")
        if (daatas(6) != "-1") {topAcc.add((daatas(6),"click"))}
        else if (daatas(8).nonEmpty) {daatas(8).split("-").foreach(id => topAcc.add((id,"order")))}
        else if (daatas(10).nonEmpty) {daatas(10).split("-").foreach(id => topAcc.add((id,"pay")))}
      }
    )

    topAcc.value
      .map(_._2)
      .toList
      .sortWith(
        (left, right) => {
          if (left.click > right.click) true
          else if (left.click == right.click) {
            if (left.order > right.order) true
            else if (left.order == right.order) left.pay > right.pay
            else false}
          else false
        }
      ).foreach(println)


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
    //    3.reduceBykey (聚合算子，spark会提供优化, 自动缓存)
    //    4.存在shuffle 还是不够完美 -> 累加器实现


//    sc.textFile("src/main/Spark/DataFile/user_visit_action.csv")
//    //转化数据结构 => (品类, (点击数, 下单数, 支付数))
//      .flatMap(value => {
//        val strings = value.split(",")
//        if (strings(6) != "-1") {List((strings(6), (1, 0, 0)))}
//        else if (strings(8).nonEmpty) {strings(8).split("-").map(id => (id, (0, 1, 0)))}
//        else if (strings(10).nonEmpty) {strings(10).split("-").map(id => (id, (0, 0, 1)))}
//        else Nil})
//      .reduceByKey((num1, num2) => (num1._1+num2._1,num1._2 + num2._2, num1._3 + num2._3))
//      .sortBy(value => value._2, false)
//      .collect()
//      .foreach(println)
//
//
////    //1. 统计品类的点击数(能过滤一定要先过滤)
////    val ClickRDD = RDD.filter(
////      string => {
////        val datas =  string.split(",")
////        datas(6) != "-1"
////      }
////    )
////      .map(
////        string => {
////          val datas =  string.split(",")
////          (datas( 6),1)
////        }
////      )
////      .reduceByKey((num1, num2) => num1 + num2)
////
////
////    //2. 统计品类的下单数
////    val OrderRDD = RDD.filter(
////      string => {
////        val datas =  string.split(",")
////        datas(8).nonEmpty
////      }
////    )
////      .flatMap(
////        string => {
////          val datas =  string.split(",")
////          val IDs = datas(8).split("-")
////          IDs.map(string => (string, 1))
////        }
////      )
////      .reduceByKey((num1, num2) => num1 + num2)
////
////    //3. 统计品类的支付数
////    val PayRDD = RDD.filter(
////      string => {
////        val datas =  string.split(",")
////        datas(10).nonEmpty
////      }
////    )
////      .flatMap(
////        string => {
////          val datas =  string.split(",")
////          val IDs = datas(10).split("-")
////          IDs.map(string => (string, 1))
////        }
////      )
////      .reduceByKey((num1, num2) => num1 + num2)
//////
//////    //4. 排序 点击数排序， 下单数量排序，支付数量排序
//////    //元组排序为 比较第一个，再比较第二个，在比较第三个
//////    //(品类, (点击数, 下单数, 支付数))
//////    //cogroup
//////
//////    val CoGroupRDD: RDD[(String, (Iterable[Int], Iterable[Int], Iterable[Int]))] = ClickRDD.cogroup(OrderRDD, PayRDD)
//////    CoGroupRDD.mapValues {
//////      //每个都会点击但是不一定会支付，我们需要统计的是支付的，如果支付那就是，一定下单了
//////      case (click, order, pay) => {
//////        var clickcou = 0
//////        if (click.iterator.hasNext) {
//////          clickcou = click.iterator.next()
//////        }
//////        var ordercou = 0
//////        if (order.iterator.hasNext) {
//////          ordercou = order.iterator.next()
//////        }
//////        var paycou = 0
//////        if (pay.iterator.hasNext) {
//////          paycou = pay.iterator.next()
//////        }
//////        (clickcou, ordercou, paycou)
//////      }
//////    }
//////      .sortBy(_._2,false)
//////      .collect()
//////      .foreach(println)
////
////    //(品类, (点击数, 下单数, 支付数))
////
////    //(品类,  点击) => (品类, (点击数量, 0, 0))
////    //        下单 => (品类, (点击数量, 下单数量, 0))
////    //        支付 => (品类, (点击数量, 下单数量, 支付数量))
////    val ClickRDDM: RDD[(String, (Int, Int, Int))] = ClickRDD.map {
////      case (c, count) => (c, (count, 0, 0))
////    }
////    val OrderRDDM: RDD[(String, (Int, Int, Int))] = OrderRDD.map {
////      case (c, count) => (c, (0, count, 0))
////    }
////
////    val PayRDDM: RDD[(String, (Int, Int, Int))] = OrderRDD.map {
////      case (c, count) => (c, (0, 0, count))
////    }
////
////    //将三个数据源合并在一起统一进行聚合
////    ClickRDDM.union(OrderRDDM).union(PayRDDM)
////      .reduceByKey((num1, num2) => (num1._1+num2._1,num1._2 + num2._2, num1._3 + num2._3))
////      .sortBy(value => value._2, false)
////      .collect()
////      .foreach(println)

    sc.stop()
  }

}
