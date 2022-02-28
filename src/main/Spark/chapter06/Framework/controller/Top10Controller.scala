package chapter06.Framework.controller

import chapter06.Framework.common.TraitController
import chapter06.Framework.service.Top10Service

class Top10Controller extends TraitController{


  private val top10Service = new Top10Service()
  override def scheduling(): Unit = {
    val array = top10Service.dataAnalysis()
    array.foreach(println)
  }
}
