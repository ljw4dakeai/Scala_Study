package chapter06.Framework.application

//Application -> controller -> service -> Dao
//Application <- controller <- service <- Dao
//应用层         控制层          业务逻辑层   持久层(数据层)


import chapter06.Framework.common.TraitApplication
import chapter06.Framework.controller.WordCountController

object WordCountApplication extends App with TraitApplication{

  start() {
    val controller = new WordCountController()
    controller.scheduling()
  }

}
