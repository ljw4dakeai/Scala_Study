package chapter05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Test14_RDD_Fuction_Serial {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("Operator")
      .setMaster("local[*]")


    val sc = new SparkContext(conf)

    val RDD = sc.makeRDD(List("hello spark", "hello word", "hello scala", "hive"))
    val search1 = new Serach("h")
    val serach2 = new Serach("h")

//    search1.getMatch1(RDD).collect().foreach(println)
    serach2.getMatch2(RDD).collect().foreach(println)
  }


  //1 序列化
  //2 属性和类剥离，不参与序列化
  //查询对象
  //类的构造参数其实是类的属性 quary -> 类的属性 -> 需要进行闭包检测，等同类的闭包检测
  class Serach(quary: String){
    def isMatch(s: String): Boolean = {
      s.contains(this.quary)
    }

    //函数序列化
    def getMatch1(RDD: RDD[String]): RDD[String] = {
      RDD.filter(isMatch)
    }

    //属性序列化
    def getMatch2(RDD: RDD[String]): RDD[String] = {
      RDD.filter(x => x.contains(quary))
      //另一种解决方法
      //val s = quary
      //RDD.filter(x => x.contains(s))
    }


  }

}


