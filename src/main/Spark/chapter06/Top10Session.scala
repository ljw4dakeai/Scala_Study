package chapter06

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Top10Session {
  def main(args: Array[String]): Unit = {
    //时间       用户Id   sessionID                      页面ID 动作时间            搜索关键字 品类  商品 下单品类ID和产品ID  支付品类ID和产品ID
    //时间       用户Id   sessionID                      页面ID 动作时间            搜索关键字  点击ID     下单品类ID和产品ID  支付品类ID和产品ID
    //2019-05-05,85,e2eef06e-beaa-4b49-acaf-38e057e1cd6e,14,2019-05-05 01:53:25,       ,   14 ,62,        ,       ,       ,        ,   2
    //2019-05-05,85,e2eef06e-beaa-4b49-acaf-38e057e1cd6e,8,2019-05-05 02:02:38,        ,   -1,-1,         ,       ,  1-2-3,1-2-3   ,  20
    //2019-05-05,85,e2eef06e-beaa-4b49-acaf-38e057e1cd6e,31,2019-05-05 02:54:16,苹果    ,   -1,-1,         ,       ,       ,        ,  19
    //2019-05-05,85,e2eef06e-beaa-4b49-acaf-38e057e1cd6e,2,2019-05-05 02:21:38,        ,   -1,-1,  1-2-3  ,  1-2-3,       ,        ,   5

    val conf = new SparkConf()
      .setAppName("Top10")
      .setMaster("local[*]")

    //TODO top10 热门品类前10
    val sc = new SparkContext(conf)
    val RDD = sc.textFile("src/main/Spark/DataFile/user_visit_action.csv")
    RDD.cache()
    //热度前10的商品
    val top10Ids: Array[String] = top10(RDD)

    //过滤原属数据,拿到种类前10的ID的对应的数据(有可能为-1)
    RDD
      .filter(
        values => {
          val line = values.split(",")
          line(6) != "-1" && top10Ids.contains(line(6))
        }
      )
      //((品类ID，sessionId), 1)
      .map(values => {
        val line = values.split(",")
        ((line(1), line(2)), 1)
        }
      )
      //((品类ID，sessionID)， num)
      .reduceByKey((num1, num2) => num1 + num2)
      //((品类ID，sessionID)，sum) -> (品类，(sessionID, sum))
      .map {
        case (((cid, sid), sum)) => (cid, (sid, sum))
      }
      //相同的品类分组
      .groupByKey()
      //分组后的数据排序
      .mapValues(value => {
        value.toList.sortBy(value => value._2)(Ordering[Int])reverse
        }
      )
      .collect()
      .foreach(println)

    sc.stop()



  }


  def top10(RDD: RDD[String]): Array[String] = {
    RDD
    .flatMap(value => {
      val strings = value.split(",")
      if (strings(6) != "-1") {List((strings(6), (1, 0, 0)))}
      else if (strings(8).nonEmpty) {strings(8).split("-").map(id => (id, (0, 1, 0)))}
      else if (strings(10).nonEmpty) {strings(10).split("-").map(id => (id, (0, 0, 1)))}
      else Nil}
    )
    .reduceByKey((num1, num2) => (num1._1+num2._1,num1._2 + num2._2, num1._3 + num2._3))
    .sortBy(value => value._2, false)
      .map(value => value._1)
    .take(10)

  }

}
