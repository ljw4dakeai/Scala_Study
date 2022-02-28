package chapter06.Framework.application

import chapter06.Framework.common.TraitApplication
import chapter06.Framework.controller.Top10Controller

object Top10Application extends TraitApplication with App {

  start(){
    val controller = new Top10Controller()
    controller.scheduling()
  }

}
