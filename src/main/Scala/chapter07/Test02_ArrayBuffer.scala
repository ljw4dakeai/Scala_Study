package chapter07

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object Test02_ArrayBuffer {
  def main(args: Array[String]): Unit = {
    val arr1: ArrayBuffer[Int] = new ArrayBuffer[Int]()
    val arr2 = ArrayBuffer(12, 10 ,100)

    println(arr1.mkString(" "))
    println(arr2.toString())
    println(arr2)

    //访问元素
//    println(arr1(0)) //error 注意不要越界
    println(arr2(2))
    arr2(1) = 20
    println(arr2(1))

    println("=============== ")
    //添加元素
    val arr3: ArrayBuffer[Int] = arr1 :+ 15 //  :+针对不可变集合，但是要赋值给新的数组 ,那么arr3就是新的引用

    println(arr3 == arr1)
    val arr4: ArrayBuffer[Int] = arr1 += 15 //  +=直接对arr1 操作，不改变引用

    //前面追加
    val arr5: ArrayBuffer[Int] = 77 +=: arr1
    println(arr4 == arr1)
    println(arr3)
    println(arr4) //arr4和arr5都是对arr1的引用
    println(arr5)


    println("=============== ")
    //追加append方法和preappend

    arr2.append(1)
    arr2.prepend(2)
    arr2.insert(1, 20,100) //第一个为索引位置，后面的的全部为插入的数据
    println(arr2)

    println("=============== ")

    arr2.insertAll(1, arr1)
    println(arr2)
    println("=============== ")
    //删除
    arr1.remove(1)
    println(arr1)
    arr2.remove(0, 2)
    println(arr2)

    arr2 -= 1 //在arrybuffer中查找删除
    println(arr2)


    println("================")

    //可变数组，转变为不可变数组
    val arr: ArrayBuffer[Int] = ArrayBuffer[Int](10, 20 ,30)
    val arrI: Array[Int] = arr.toArray
    println(arr)
    println(arrI.mkString(" "))

    //不可变变为可变数组
    val arrB: mutable.Buffer[Int] = arrI.toBuffer
    println(arrB)





  }
}
