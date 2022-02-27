package chapter05
/*
checkpoint 需要落盘,需要指定保存路径 sc.setCheckpointDir()一般保存数据在分布式文件系统中HDFS
           执行完作业后，检查点数据不会被删除
           persist，是缓存文件，执行作业后会删除数据的
 */


/*
cache: 将数据临时存储在内存中，进行数据重用 -> 会添加新的依赖
                                                  易丢失，不安全,性能高
persisst: 将数据临时存放在磁盘文件中,进行数据重用
          涉及磁盘IO，临时保存的数据，运行完以后也会丢失  易丢失，安全  ,性能低
checkpoint: 将数据长久保存在磁盘中进行数据重用 -> 执行过程中，会切断血缘关系，建立新的血缘关系（建立新的数据源）
            涉及磁盘IO，临时保存的数据                不丢失，安全  ,性能低
            一般配合cache使用
            先cache在checkpoine -> 提高效率         不丢失，安全  ,性能高


 */
import org.apache.spark.{SparkConf, SparkContext}

object Test17_RDD_CheckPoint {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("WoedCount")
      .setMaster("local[*]")


    val sc = new SparkContext(conf)
    sc.setCheckpointDir("src/main/Spark/chapter05/checkpointdir")


    val  mapRDD =
      sc.makeRDD(List("hello spark", "hello word", "hello scala"))
        .flatMap(line => line.split(" "))
        .map((_,1))

    mapRDD.reduceByKey(_+_)
      .collect()
      .foreach(println)

    mapRDD.cache()
    mapRDD.checkpoint()

    mapRDD.groupByKey()
      .collect()
      .foreach(println)
  }
}
