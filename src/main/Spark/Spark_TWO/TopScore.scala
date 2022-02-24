package Spark_TWO

import org.apache.spark.{SparkConf, SparkContext}

object TopScore {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("TopScore")
      .set("saprk.executor.memory", "256M")
      .setMaster("spark://192.168.95.136:7077")
      .setJars(List(""))

    val sc = new SparkContext()
    val input = sc.textFile("")
    val lines = input.flatMap(lines => lines.split(" "))
  }

}
