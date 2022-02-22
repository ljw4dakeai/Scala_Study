package chapter02

import java.io.{File, PrintWriter}
import scala.io.Source

object Test06_FileIO {
  def main(args: Array[String]): Unit = {
    //文件读取
    Source.fromFile("src/main/resources/Test06.txt").foreach(print)


    //数据写入文件
    val writer = new PrintWriter(new File("src/main/resources/Test06.txt"))

    writer.write("hello scala from Writer")

    writer.close()
  }

}
