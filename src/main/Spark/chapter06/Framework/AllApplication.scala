package chapter06.Framework

import org.apache.spark.SparkConf

object AllApplication extends App {

  val conf = new SparkConf()
    .setAppName("AllApplication")
    .setMaster("saprk://192.168.95.136")
    .setJars(List("","","",""))

}
