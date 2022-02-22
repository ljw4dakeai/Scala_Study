package Spark_ONE

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("WordCount")
      .setMaster("spark://192.168.95.136:7077")
      .setJars(List("/Users/zoujiahao/IDEA/Scala_Study/out/artifacts/Scala_Study_jar/Scala_Study.jar"))

    System.setProperty("HADOOP_USER_NAME", "root")
    val sc = new SparkContext(conf)

    val input = sc.textFile("hdfs://192.168.95.136:9000/user/zoujiahao1905140016/MapReduce/WordCount/input.txt")
    val lines = input.flatMap(line=>line.split(" "))
    val count = lines.map(word=>(word,1)).reduceByKey{case(x,y)=>x+y}
    val output = count.saveAsTextFile("hdfs://192.168.95.136:9000/user/zoujiahao1905140016/Spark/WordCount")



  }

}
