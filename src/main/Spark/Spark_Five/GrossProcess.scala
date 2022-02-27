package Spark_Five

import org.apache.spark.{SparkConf, SparkContext}

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
          val yaerMouth = dataFormat.parse(line(2)).getYear.toString + "-" +  dataFormat.parse(line(2)).getMonth.toString
          (yaerMouth, (line(5).toInt, line(6).toFloat))
        }
      )
      .reduceByKey((tuple1, tuple2) => (tuple1._1 + tuple2._1 , tuple1._2 +  tuple2._2))
      .sortByKey()
      .collect()
      .foreach(println)


    sc.stop()

  }

}
