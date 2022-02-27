package chapter05

import org.apache.spark.{SparkConf, SparkContext}

//旧的RDD -> 新的RDD （转换算子）
object Test08_RDD_Fuction_Trans {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("Operator")
      .setMaster("local[*]")


    val sc = new SparkContext(conf)
//1.map

    sc.makeRDD(List(1, 2, 4, 5))
      .map(num => num * 2)
      .collect()
      .foreach(println)

// 2.mapPartitions
    sc.makeRDD(List(1, 2, 4, 5),2)
      //批处理
      //mapPartitions可以以分区为单位进行数据转换操作
      //  但是会将整个分区的数据加载到内存中
      // 如果处理完数据是不会释放的，存在对象引用 （可能会内存溢出）
      .mapPartitions(
        iter => {
          println("------->")
          iter.map(_ * 2)
        }
      )
      .collect()
      .foreach(println)


//3.mapPartitionsWithIndex

//1,2,3,4 (8个分区，默认使用所有核心)
//1->1 3->2 5->3 7->4
//输出(1,1) (3,2) (5,3) (7,4)
    sc.makeRDD(List(1, 2, 3, 4))
//      .mapPartitionsWithIndex(
//        (index, iter) => {
//          if (index == 1) iter else Nil.iterator
//          }
//      )
//      .collect()
//      .foreach(println)
      .mapPartitionsWithIndex(
        (index, iter) => iter.map(num => (index, num))
      )
      .collect()
      .foreach(println)


// 4.扁平化faltmap

    sc.makeRDD(List(List(1,2),List(3,4),List(5,6)))
      .flatMap(
        list => list
      )
      .collect()
      .foreach(println)


    sc.textFile("src/main/resources/Test06.txt")
      .flatMap(s => s.split(" "))
      .collect()
      .foreach(println)

    sc.makeRDD(List(List(1,2),3,List(5,6)))
      .flatMap(
        data => data match {
          case list: List[Int] => list
          case int: Int => List(int)
        }
      )
      .collect()
      .foreach(println)



//5.glom

    sc.makeRDD(List(1, 2, 3, 4), 2)
      .glom()
      .collect()
      .foreach(array => println(array.mkString("-")))






//6.groupby

    sc.makeRDD(List(1, 2, 3, 4), 2)
      //将数据源中每个数据进行分组判断，在根据返回分组的key进行分组
      //相同的key会放在同一个分组中
      .groupBy(num => num %  2 )
      .collect()
      .foreach(println)

    sc.makeRDD(List("hello", "spark", "scala", "hadoop"))
      .groupBy(string => string.charAt(0)) //返回一个key，通过key分组
      .collect()
      .foreach(println)

    //分组和分区没有必然关系 ：  数据打乱 -> 重新组合  (shuffle)


//7.filter

    sc.makeRDD(List(1, 2, 3, 4), 2)
      .filter(num => num % 2 == 0)
      .collect()
      .foreach(println)


//8.sample(防止数据倾斜)

    //sample需要传入3个参数
    //  1. 抓取数据后是否需要放回 true（放回） false （不放回）
    //  2. 不放回 ->数据源中每条数据被抽取的概率 -> bernoullisampler（伯努利算法）（false）
    //          基准值
    //     放回 ->数据源中每条数据被抽取的次数
    //  3. 抽取数据随机算法的种子(如果不传入第三个参数，，那么传入的就是系统时间)
    sc.makeRDD(List(1, 2, 3, 4,5, 6, 7, 8, 9, 10))
      .sample(
        false,
        0.4,
        1
      )
      .collect()
      .mkString("-")

//9.distinct(去重)

    sc.makeRDD(List(1, 2, 3, 4, 1, 2, 3, 4))
      .distinct()
      //map(x => (x,null)).reducebykey((x, _) => x, nullPartitons).map(_._1)
      //(1, null)(2, null)(33, null)(4, null)(1, null)(2, null)(33, null)(4, null)
      //多个key对应的null做聚合变成
      //（1，null)
      .collect()
      .foreach(println)


//10.coalesce
//  1.默认不会将分区数据打乱重新组合，可能回导致数据侵袭（下面的例子）
//  2.如果想要均衡，可以使用shuffle处理(默认为false)但是没有规律

    //缩小分区
    sc.makeRDD(List(1, 2, 3, 4, 5, 6), 4)
      //1 2 3 4 5 6 -> 12   3456 (coalesece = 3)
      .coalesce(3, true)
      .saveAsTextFile("src/main/Spark/chapter05/output")
    //扩大分区(需要使用shuffle)
    sc.makeRDD(List(1, 2, 3, 4, 5, 6), 2)
      //1 2 3 4 5 6 -> 12   3456 (coalesece = 3)
//      .coalesce(3, true)
//      一般使用repartition方法扩大分区
      .repartition(3) //->底层调用coalesce(3, true)
      .saveAsTextFile("src/main/Spark/chapter05/output")


//11.sortby
//  1.默认为升序，第二个参数可以调节(false -> 降序)
//    默认情况下不会改变分区，但是中间存在shuffle
//    sc.makeRDD(List(6, 2, 4, 4, 5, 6),2)
//      .sortBy(num => num)
//      .collect()
//      .foreach(println)

    sc.makeRDD(List(("1", 1), ("1000", 220), ("300", 101)), 2)
      .sortBy(tuple => tuple._1.toInt, false )
      .collect()
      .foreach(println)

    sc.stop()

    

  }
}
