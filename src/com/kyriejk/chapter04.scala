package com.kyriejk

import java.io.File
import java.util
import java.util.{Calendar, Scanner, StringTokenizer}

import scala.collection.{SortedMap, immutable, mutable}
import scala.collection.JavaConversions.mapAsScalaMap

object chapter04 {


  /**
   * 1.设置一个映射，其中包含你想要的一些装备及其价格。然后构建另一个映射，采用同一组键，但在价格上打9折
   * @param args
   */
    def q1(originMap:Map[String,Double])={
      for ((k,v)<-originMap) yield (k,0.9*v)
    }

  /**
   * 2.编写一段程序，从文件中读取单词。用一个可变映射来清点每一个单词出现的频率。读取这些单词的操作使用java.util.Scanner:
   * val in = new java.util.Scanner(new java.io.File(myfile.txt))
   * while(in.hasNext()) 处理in.next()
   * @param args
   */
    def q2(filepath:String)={
      val res = mutable.Map[String,Int]()
      val in = new Scanner(new File(filepath))
      while (in.hasNext()){
        val currKey = in.next()
        val currFreq=res.getOrElse(currKey,0)
        res(currKey)=currFreq+1
      }
    }

  /**
   * 3.重复前一个练习，这次用不可变的映射
   * @param args
   */
    def q3(filepath:String)={
      var res=immutable.Map[String,Int]()
      val in = new Scanner(new File(filepath))
      while (in.hasNext()){
        val currKey = in.next()
        res+=(currKey->(res.getOrElse(currKey,0)+1))
      }
      res
    }

  /**
   * 4.重复前一个练习，这次用已排序的映射，以便单词可以按顺序打印出来
   * @param args
   */
    def q4(filepath:String)={
      var res = SortedMap[String,Int]()
      val in =new Scanner(new File(filepath))
      while(in.hasNext()){
        val currKey = in.next()
        res+=(currKey->(res.getOrElse(currKey,0)+1))
      }
      res
    }

  /**
   * 5.重复前一个练习，这次用java.util.TreeMap并使之适用于ScalaAPI
   * @param args
   */
    def q5(filepath:String)={
      import java.util.TreeMap
      val res:scala.collection.mutable.Map[String,Int] = new TreeMap[String,Int]
      val in = new Scanner(new File(filepath))
      while (in.hasNext()){
        val currKey = in.next()
        res(currKey)=res.getOrElse(currKey,0)+1
      }
    }

  /**
   * 6.定义一个链式哈希映射，将"Monday"映射到java.util.Calendar.MONDAY,以此类推加入其它日期。展示元素是以插入的顺序被访问的
   * @param args
   */
    def q6()={
      var map=Map[String,Int]()
      val list = classOf[Calendar].getFields
      list.foreach(x=>map+=(x.getName->x.getInt(null)))
      map
    }

  /**
   * 7.打印出所有Java系统属性的表格，类似于下面这样：
   * java.runtime.name  |Java(TM)SERuntimeEnvironment
   * sun.boot.library.path  |/home/apps/jdk1.6.0_21/jre/lib/i386
   * @param args
   */
    def q7()={
      val properties:scala.collection.Map[Object,Object] = System.getProperties().take(10)
      var maxLen=0

      maxLen=properties.map(f=>f._1.toString.length).max
      properties.foreach(x=>println(x._1+" "*(maxLen-x._1.toString.length+1)+"| "+x._2))
    }

  /**
   * 8.编写一个函数minmax(values:Array[Int]),返回数组中最小值和最大值的对偶
   * @param args
   */
    def minmax(values:Array[Int])={
      (values.min,values.max)
    }

  /**
   * 9.编写一个函数lteqgt(values:Array[Int],v:Int),返回数组中小于v、等于v和大于v的数量，要求三个值一起返回
   * @param args
   */
    def q9(values:Array[Int],v:Int)={
      (values.count(_<v),values.count(_==v),values.count(_>v))
    }

  /**
   * 10.当你将两个字符串拉链在一起时，比如"Hello".zip("World")，会是什么结果？想出一个讲得通的用力
   * @param args
   */
    def q10()={
      println("Hello".zip("World"))
    }

  def main(args: Array[String]): Unit = {
    q10()
  }

}
