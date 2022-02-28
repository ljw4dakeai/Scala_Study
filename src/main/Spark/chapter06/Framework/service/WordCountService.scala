package chapter06.Framework.service

import chapter06.Framework.common.TraitService
import chapter06.Framework.dao.WordCountDao

/**
 * 服务层,业务逻辑层面
 */
class WordCountService extends TraitService{

  private val woedcountDao = new WordCountDao()

  override def dataAnalysis(): Array[(String, Int)] = {

    val lines = woedcountDao.input("src/main/resources/Test06.txt")
    lines.flatMap(_.split(" "))
         .map((_,1))
         .reduceByKey(_+_)
         .collect()
  }
}
