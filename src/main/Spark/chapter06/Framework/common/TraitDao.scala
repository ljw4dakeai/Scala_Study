package chapter06.Framework.common

import chapter06.Framework.util.EnvUtil
import org.apache.spark.rdd.RDD


// 读取输入文件
trait TraitDao {
  def input(path: String): RDD[String] ={
    EnvUtil.get().textFile(path)
  }


}
