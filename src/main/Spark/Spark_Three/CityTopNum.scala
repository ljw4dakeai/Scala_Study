package Spark_Three

import org.apache.spark.{SparkConf, SparkContext}

object CityTopNum {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("TopScore")
      .setMaster("local[*]")

    val sc = new SparkContext(conf)

    //1516609143869   9    4     75    18
    //原始数据 （时间， 省份， 城市， 用户， 广告）
    sc.textFile("src/main/Spark/DataFile/agent.log")

    //=>转换数据 map方法 =>((省份， 广告)， 1)
      .map(
      lines => {
        //时间， 省份， 城市， 用户， 广告
        val datas = lines.split(" ")
        ((datas(1), datas(4)), 1)
      }
    )
    //((省份， 广告)， 1) => ((省份， 广告)， sum)
      .reduceByKey((num1, num2) => num1 + num2 )
    //((省份， 广告)， sum) =>(省份，(广告， sum)) (模式匹配)
      .map(tuple => tuple match {
        case ((s, g), num) => (s,(g,num))
      })
    //(省份, (告a, suma) (告b， sumb))
      .groupByKey()
    //排序，对可迭代的集合操作
      .mapValues(
        iter => {
          //通过比对sum的大小排序（list.sortby默认生序）(降序操作)          (取前三个数)
          iter.toList.sortBy(list => list._2)(Ordering[Int].reverse).take(3)
        }
      )
      .collect()
      .foreach(println)


    sc.stop()
  }

}
