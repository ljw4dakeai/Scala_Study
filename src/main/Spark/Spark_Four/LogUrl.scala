package Spark_Four

import org.apache.spark.{SparkConf, SparkContext}

object LogUrl {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("LogUrl")
      .setMaster("local[*]")

    val sc = new SparkContext(conf)
    //9442589358450719	[langke]	1 1	www.langke.net/
    sc.textFile("src/main/Spark/DataFile/localinput.filter")
//      .map(lines => {
//        val datas = lines.split("\\s")
//        if (datas(2) == "2" && datas(3) == "1" && datas.length == 5)
//          datas(0) + "\t" + datas(1) + "\t" + datas(2) + "\t" + datas(3) + "\t" +datas(4)
//      })
      .filter(string => {
        val datas = string.split("\\s")
        datas(2) == "2" && datas(3) == "1" && datas.length == 5
      })
      .saveAsTextFile("src/main/Spark/Spark_Four/output")

    sc.stop()

  }

}
