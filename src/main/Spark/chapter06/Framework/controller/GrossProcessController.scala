package chapter06.Framework.controller

import chapter06.Framework.common.TraitController
import chapter06.Framework.service.GrossProcessService

class GrossProcessController extends TraitController{
  private val grossProcessService = new GrossProcessService()

  override def scheduling(): Unit = {
    val array = grossProcessService.dataAnalysis()
    array.foreach(println)

  }
}
