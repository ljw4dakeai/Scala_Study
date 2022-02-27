package chapter05

import org.apache.spark.{SparkConf, SparkContext}

/*

血缘关系
RDD不会保存数据，但是会保存血缘关系，当数据出错后，可以根据血缘关系进行从新计算
RDD1 -> RDD2 -> RDD3 -> RDD4
RDD1 < -- -- 血缘 -- --  RDD4
toDebugString => 输出血缘关系
dependences   => 依赖关系

数据依赖
新的RDD的一个分区的数据依赖旧的RDD的一个分区 onetoone(窄依赖narrowDependency) 不需要等待操作
新的RDD的一个分区的数据依赖旧的RDD的多个分区 shuffle (宽依赖)                 需要等待操纵，并且task增加

阶段划分
当RDD存在shuffle依赖时，阶段会增加一个
阶段 = shuffle + 1



任务划分
每一个阶段中，最后一个分区数量等于任务数量 Partitions = task


 */




object Test15_RDD_Dependence {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("WordCount")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)
//    val input = sc.textFile("src/main/Spark/Spark_One/text")
//    println(input.toDebugString)
//    println("======================")
//    val lines = input.flatMap(_.split(" "))
//    println(lines.toDebugString)
//    println("======================")
//    val count = lines.map((_, 1)).reduceByKey{case(x,y)=>x+y}
//    println(count.toDebugString)
//    println("======================")
//    count.saveAsTextFile("src/main/Spark/Spark_One/output")

    val input = sc.textFile("src/main/Spark/Spark_One/text")
    println(input.dependencies)
    println("======================")
    val lines = input.flatMap(_.split(" "))
    println(lines.dependencies)
    println("======================")
    val count = lines.map((_, 1)).reduceByKey{case(x,y)=>x+y}
    println(count.dependencies)
    println("======================")
    count.saveAsTextFile("src/main/Spark/Spark_One/output")


    sc.stop()

  }




}
