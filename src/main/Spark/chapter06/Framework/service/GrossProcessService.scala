package chapter06.Framework.service

import Spark_Five.GrossProcess.part
import chapter06.Framework.common.TraitService
import chapter06.Framework.dao.GrossProcessDao

import java.text.SimpleDateFormat

class GrossProcessService extends TraitService{
  private val grossProcessDeo =new  GrossProcessDao()
  override def dataAnalysis(): Array[(String, (Int, Float))] = {
    val RDD = grossProcessDeo.input("src/main/Spark/DataFile/localinput.csv")
    RDD.map(
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
    .collect()


  }
}
