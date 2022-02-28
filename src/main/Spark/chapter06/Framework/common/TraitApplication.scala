package chapter06.Framework.common


import chapter06.Framework.util.EnvUtil
import org.apache.spark.{SparkConf, SparkContext}

trait TraitApplication {

  def start(AppName: String = "Applicaation", Master: String = "local[*]")(op: => Unit): Unit ={
    val sparkConf = new SparkConf()
      .setAppName(AppName)
      .setMaster(Master)
    val sc= new SparkContext(sparkConf)

    //因为是一个主线程程序，把sc放入ThreadLocal中,提供给持久层Deo使用
    EnvUtil.put(sc)

    try{
      //要执行的代码块
      op

    }catch {
      case e => println(e.getMessage)
    }

    //关闭程序
    sc.stop()
    EnvUtil.clear()

  }


}
