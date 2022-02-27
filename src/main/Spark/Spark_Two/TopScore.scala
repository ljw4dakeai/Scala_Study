package Spark_Two

import org.apache.spark.{SparkConf, SparkContext}

object TopScore {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("TopScore")
      .setMaster("local[*]")

    val sc = new SparkContext(conf)
    //语文s 73s -> 语文s 73int
    sc.textFile("src/main/Spark/DataFile/localinput.txt")
      .map(
        lines => {
          val datas = lines.split(" ")
          (datas(0), datas(1).toInt)
        }
      )
      .groupByKey()
      .mapValues(
        iter => {
          iter.toList.sortBy(int => int)(Ordering[Int]reverse)
        }
      )
      .collect()
      .foreach(println)

    sc.stop()


  }

}
