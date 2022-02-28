package chapter06.Framework.controller

import chapter06.Framework.common.TraitController
import chapter06.Framework.service.WordCountService

/*
控制层
 */
class WordCountController extends TraitController{

  private val wordCountService = new WordCountService()

  def scheduling(): Unit = {
    //TODO 执行业务（重写TraitController）

    val array = wordCountService.dataAnalysis()
    array.foreach(println)


  }


}
