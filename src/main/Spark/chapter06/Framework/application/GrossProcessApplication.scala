package chapter06.Framework.application

import chapter06.Framework.common.TraitApplication
import chapter06.Framework.controller.GrossProcessController

object GrossProcessApplication extends App with TraitApplication{

  start(){
    val controller = new GrossProcessController()
    controller.scheduling()
  }
}
