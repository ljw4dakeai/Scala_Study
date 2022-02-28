package chapter06.Framework.service

import chapter06.Framework.common.TraitService
import chapter06.Framework.dao.Top10Dao
import org.apache.spark.rdd.RDD

class Top10Service extends TraitService{
  private val top10Dao = new Top10Dao()

  override def dataAnalysis(): Array[(String, (Int, Int, Int))] = {

    val RDD = top10Dao.input("src/main/Spark/DataFile/user_visit_action.csv")

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

    //4. 排序 点击数排序， 下单数量排序，支付数量排序
    //元组排序为 比较第一个，再比较第二个，在比较第三个
    //(品类, (点击数, 下单数, 支付数))
    //cogroup

    val CoGroupRDD: RDD[(String, (Iterable[Int], Iterable[Int], Iterable[Int]))] = ClickRDD.cogroup(OrderRDD, PayRDD)
    CoGroupRDD.mapValues {
      //每个都会点击但是不一定会支付，我们需要统计的是支付的，如果支付那就是，一定下单了
      case (click, order, pay) => {
        var clickcou = 0
        if (click.iterator.hasNext) {
          clickcou = click.iterator.next()
        }
        var ordercou = 0
        if (order.iterator.hasNext) {
          ordercou = order.iterator.next()
        }
        var paycou = 0
        if (pay.iterator.hasNext) {
          paycou = pay.iterator.next()
        }
        (clickcou, ordercou, paycou)
      }
    }
      .sortBy(_._2,false)
      .collect()


  }
}
