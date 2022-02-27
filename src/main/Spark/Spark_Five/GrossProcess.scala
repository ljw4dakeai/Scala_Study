package Spark_Five

import org.apache.spark.{Partitioner, SparkConf, SparkContext}

import java.text.SimpleDateFormat

object GrossProcess {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("GrossProcess")
      .setMaster("local[*]")

    //13,848,1998-01-20,3,999,1,1232.16
//    ID   year mouth        num total
    val sc = new SparkContext(conf)
    sc.textFile("src/main/Spark/DataFile/localinput.csv")
      .map(
        lines => {
          val line = lines.split(",")
          val dataFormat = new SimpleDateFormat("yyyy-MM-dd")
          val yaerMouth = (1900 + dataFormat.parse(line(2)).getYear).toString + "-" +  (1 + dataFormat.parse(line(2)).getMonth).toString
          (yaerMouth, (line(5).toInt, line(6).toFloat))
        }
      )
      .reduceByKey((tuple1, tuple2) => (tuple1._1 + tuple2._1 , tuple1._2 +  tuple2._2))
      .sortByKey()
      .partitionBy(new part)
      .saveAsTextFile("src/main/Spark/Spark_Five/output")


    sc.stop()

  }

  class part extends Partitioner{
    //分区数量
    override def numPartitions: Int = 4

    //根据数据的key返回数量分区索引
    override def getPartition(key: Any): Int = {
      val s: String = key match {
        case string: String => string.split("-")(0)
        case _ => "else"
      }
      s match {
        case "1998" => 0
        case "1999" => 1
        case "2000" => 2
        case _ => 3
      }
    }
  }

}
