package Spark_One

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("WordCount")
      .set("spark.executor.memory", "512m")
      .setMaster("spark://192.168.95.136:7077")
      .setJars(List("/Users/zoujiahao/IDEA/Scala_Study/out/artifacts/Spark_WordCount_jar/Scala_Study.jar"))

//    val conf = new SparkConf()
//          .setAppName("WordCount")
//          // 设置yarn-client模式提交
//          .setMaster("spark://192.168.95.136:7077")
//          // 设置resourcemanager的ip
//          .set("yarn.resourcemanager.hostname","192.168.95.136")
//          // 设置executor的个数
//          .set("spark.executor.instance","3")
//          // 设置executor的内存大小
//          .set("spark.executor.memory", "512M")
//          // 设置提交任务的yarn队列
//          .set("spark.yarn.queue","spark")
//          .setJars(List("/Users/zoujiahao/IDEA/Scala_Study/out/artifacts/Spark_WordCount_jar/Scala_Study.jar"))

    System.setProperty("HADOOP_USER_NAME", "root")
    val sc = new SparkContext(conf)

    val input = sc.textFile("hdfs://192.168.95.136:9000/user/zoujiahao1905140016/MapReduce/WordCount/input.txt")

//    """
//      |hello spark
//      |hello scala
//      |
//      |  --tetxfile——> input1 =  hello spark , input2 = hello scala |
//      |  --flatMap——>  lines1 = hello ,lines2 = spark, lines3 = hello, lines4 = scala |
//      |
//      |  --Map——> count1 = (hello, 1) ,count2 = (spark, 1), count3 = (hello, 1),count4 = (scala, 1)｜
//      |
//      |  --reduceBykey ——> couts1 = (hellow, 2), couts2 = (spark,1) , couts3 = (spark, 1)｜
//      |
//      |""".stripMargin
//      RDD数据处理类似于IO字符流的数据处理，也有装饰着设计模式，
//      只有调用集合方法时才会真正执行业务逻辑操作
//      RDD不保存数据（包涵了分区的）


    val lines = input.flatMap(_.split(" "))
    val count = lines.map((_, 1)).reduceByKey{case(x,y)=>x+y}
    count.saveAsTextFile("hdfs://192.168.95.136:9000/user/zoujiahao1905140016/Spark/WordCount/output")

    sc.stop()
  }

}
