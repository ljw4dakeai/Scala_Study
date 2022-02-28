package chapter06

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object PageFllow {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      .setAppName("Top10")
      .setMaster("local[*]")

    //TODO top10 热门品类前10
    val sc = new SparkContext(conf)
    val RDD = sc.textFile("src/main/Spark/DataFile/user_visit_action.csv")
    val useractionRDD =
      RDD
      .map(lines => {
        val datas = lines.split(",")
        userAction(datas(0), datas(1).toInt, datas(2), datas(3).toInt, datas(4), datas(5), datas(6).toInt, datas(7).toInt, datas(8), datas(9), datas(10), datas(11), datas(12).toInt)
        }
      )

    //TODO 计算分母 不同页面点击次数


    //页面跳转需求
    val needids = List(1,2,3,4,5,6)
    val needflowpage = needids.zip(needids.tail)

//    //分母过滤掉不需要的数据
//    useractionRDD.filter(
//      value => {
//        //最后一个页面不会出现zip
//        needids.init.contains(value.pageid)
//      }
//    )

    val pageidcount = useractionRDD
      .map(value => (value.pageid, 1))
      .reduceByKey((num1, num2) => num1 + num2)
      .collect()
      .toMap

    useractionRDD.cache()
    //TODO 计算分子

    //session进行分组
    val sessionRDD = useractionRDD
      .groupBy(value => value.sessionid)

    //分组后根据时间进行排序
    val mvRDD: RDD[(String, List[((Int, Int), Int)])] = sessionRDD
      .mapValues(value => {
        //对sessionRDD中的迭代器中的数据，根据他们的actiontime排序 -> 再拿到同一个sessionid的页面流转状态
        val flowpag: List[Int] = value.toList.sortBy(value => value.actiontime).map(value => value.pageid)
        // [1, 2, 3, 4] -> [1-2, 2-3,4-4] zip sliding
        //1, 2, 3, 4
        //2, 3, 4    =>  [1-2,2-3,3-4]
//        过滤不需要的页面流转
//        flowpag.filter(
//          value => {
//            needids.contains(value)
//          }
//        )
        flowpag.zip(flowpag.tail).map(value => (value, 1))
        }
      )

    val count: RDD[((Int, Int), Int)] = mvRDD
      //不需要sessionid
      .map(value => value._2)
      //list(((page1_m,page_n),1), ((page1_x,page_y),1)) => ((page1_m,page_n),1)
      .flatMap(value => value)
      //((page1_x,page_y),1) => ((page1_m,page_n),sum)
      .reduceByKey((num1, num2) => num1 + num2)

    //TODO 计算转换率

    count.foreach {
      case ((page_m, page_n), sum) => {
        println(s"页面${page_m}跳转到页面${page_n}的转换率为${sum.toDouble / pageidcount.getOrElse(page_m, 0)}")
      }
    }





  }



  //时间       用户Id   sessionID                      页面ID 动作时间            搜索关键字 品类  商品 下单品类ID和产品ID  支付品类ID和产品ID
  //时间       用户Id   sessionID                      页面ID 动作时间            搜索关键字  点击ID     下单品类ID和产品ID  支付品类ID和产品ID
  //2019-05-05,85,e2eef06e-beaa-4b49-acaf-38e057e1cd6e,14,2019-05-05 01:53:25,       ,   14 ,62,        ,       ,       ,        ,   2
  //2019-05-05,85,e2eef06e-beaa-4b49-acaf-38e057e1cd6e,8,2019-05-05 02:02:38,        ,   -1,-1,         ,       ,  1-2-3,1-2-3   ,  20
  //2019-05-05,85,e2eef06e-beaa-4b49-acaf-38e057e1cd6e,31,2019-05-05 02:54:16,苹果    ,   -1,-1,         ,       ,       ,        ,  19
  //2019-05-05,85,e2eef06e-beaa-4b49-acaf-38e057e1cd6e,2,2019-05-05 02:21:38,        ,   -1,-1,  1-2-3  ,  1-2-3,       ,        ,   5
  case class userAction(
                       data: String,
                       userid: Int,
                       sessionid: String,
                       pageid: Int,
                       actiontime: String,
                       searchname: String,
                       clicktype: Int,
                       clickentmans: Int,
                       ordertype: String ,
                       ordermans: String,
                       paytype: String,
                       paymans: String,
                       cityid: Int

                       )

}
