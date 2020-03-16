package com.kyriejk

import java.util.TimeZone

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object chapter03 {

  /**
   * 3.1 编写一段代码，将a设置为一个n个随机整数的数组，要求随机数介于0（包含）和n（不包含）之间
   * @param args
   */
    def q1(n:Int)={
      (for (i<-1 to n) yield Random.nextInt(n)).toArray
    }

  /**
   * 3.2 编写一个循环，将整数数组中相邻的元素置换。例如，Array(1，2，3，4，5)经过置换后变为Array(2，1，4，3，5)
   * @param args
   */
    def q2(arr:Array[Int]):Array[Int]={
      val l:Int=arr.length
      for (i<-0 to l-1 by 2){
        if (i+1<l){
          val t = arr(i+1)
          arr(i+1)=arr(i)
          arr(i)=t
        }
      }
      arr
    }

  /**
   * 3.3 重复前一个练习，不过这一次生成一个新的值交换过的数组。使用for/yield
   * @param args
   */
  def q3(arr:Array[Int])={
    for (i<-0 to arr.length-1) yield {
      if(i%2==0){
        if (i+1<arr.length) arr(i+1) else arr(i)
      } else {
        arr(i-1)
      }
    }
  }

  /**
   * 3.4 给定一个整数数组，产出一个新的数组，包含元数组中的所有正值，以原有顺序排列；之后的元素是所有零或者负值，以原有顺序排列
   * @param args
   */
    def q4(arr:Array[Int]):Array[Int]={
      arr.filter(_>0)++arr.filter(_<=0)
    }

  /**
   * 3.5 如何计算Array[Double]的平均值
   * @param args
   */
    def q5(arr:Array[Int])={
      if (arr.length==0) 0 else arr.sum/arr.length
    }

  /**
   * 3.6 如何重新组织Array[Int]的元素将它们以反序排列？对于ArrayBuffer[Int]，你又会怎么做呢
   * @param args
   */
    def q6(arr:Array[Int])={
      arr.reverse
    }

  /**
   * 3.7 编写一段代码，产出数组中的所有值，去掉重复项
   * @param args
   */
    def q7(arr:Array[Int])={
      arr.distinct
    }

  /**
   * 3.8 重新编写3.4节结尾的示例。收集负值元素的下标，反序，去掉最后一个下标，然后对每一个下标调用a.remove(i)。比较这样做的效率和3.4节中另外两种方法的效率
   * @param args
   */
    def q8(arr:ArrayBuffer[Int])={
      var indexes=ArrayBuffer[Int]()
      for (i<-0 to arr.length-1 if (arr(i)<0)) indexes.insert(0,i)
      indexes.init.foreach(arr.remove(_))
      arr
    }

  /**
   * 3.9 创建一个由java.util.TimeZone.getAvailableIDs返回的时区集合，判断条件是它们在美洲，去掉"America/"前缀并排序
   * @param args
   */
    def q9={
      TimeZone.getAvailableIDs.filter(_ startsWith("America")).map(_ drop(8)).sortBy(x=>x)
    }

  def main(args: Array[String]): Unit = {
    val a1 = q1(10)
    a1.foreach(println)
    var a2 =Array(1,2,3,4,5)
    a2 = q2(a2)
    a2.foreach(println)
  }
}
