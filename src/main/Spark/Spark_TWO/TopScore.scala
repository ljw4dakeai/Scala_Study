package Spark_TWO

import org.apache.spark.{SparkConf, SparkContext}

object TopScore {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("TopScore")
//      .set("saprk.executor.memory", "256M")
      .setMaster("local[*]")
//      .setJars(List(""))

  }

}
