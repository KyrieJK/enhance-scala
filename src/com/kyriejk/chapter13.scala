package com.kyriejk

object chapter13 extends App {

  /**
   * 1.编写一个函数，给定字符串，产出一个包含所有字符的下标的映射。举例来说，indexes("Mississippi")应返回一个映射
   * 让'M'对应集(0)，'i'对应集合(1,4,7,10)，依次类推。使用字符到可变集的映射。另外，你如何保证集是经过排序的？
   */
  def fun1(str:String)={
    
  }

  /**
   * 2.重复前一个练习，这次用字符到列表的不可变映射。
   */

  /**
   * 3.编写一个函数，从一个ListBuffer中移除在偶数位的元素。采用两种不同的方式。从列表尾端开始调用remove(i)移除所有i为偶数的元素
   * 将奇数位的元素复制到新的列表中。比较这两种方式的性能表现。
   */

  /**
   * 4.编写一个函数，接受一个字符串的集合，以及一个从字符串到整数值的映射。
   * 返回整型的集合，其值为能和集合中某个字符串相对应的映射的值。举例来说，
   * 给定Array("Tom","Fred","Harry")和Map("Tom"->3,"Dick"->4,"Harry"->5)，返回Array(3,5)
   * 提示：用flatMap将get返回的Option值组合在一起。
   */

  /**
   * 5.实现一个函数，作用与mkString相同，使用reduceLeft
   */

  /**
   * 6.给定整形列表lst，(lst:\ List[Int]())(_::_)得到什么？(List[Int]()/:lst)(_:+_)又得到什么？
   * 如何修改它们中的一个，以对原列表进行反向排列？
   */

  /**
   * 7.在13.10节中，表达式(prices zip quantities) map {p=>p._1 * p._2}，有些不够优雅。
   * 我们不能用(prices zip quantities) map {_ * _ }，因为_ * _是一个带两个参数的函数，而我们需要的
   * 是一个带单个类型为元组的参数的函数。Function对象的tupled方法可以将带两个参数的函数改为以元组为参数的函数。
   * 将tupled应用于乘法函数，以便我们可以用它来映射由对偶组成的列表
   */

  /**
   * 8.编写一个函数，将Double数组转换成二维数组。传入列数作为参数。举例来说：
   * Array(1,2,3,4,5,6)和三列，返回Array(Array(1,2,3),Array(4,5,6))。用grouped方法
   */

  /**
   * 9.Scala编译器将for/yield表达式
   * for(i<-1 to 10;j<-1 to i) yield i*j
   * 变换成flatMap和map的调用，就像这样：
   * (1 to 10).flatMap(i=>(1 to i).map(j=>i*j))
   * 解释flatMap的使用。提示：当i为1，2，3时，(1 to i).map(j=>i*j)将交出什么？
   * 当for/yield表达式有三个生成器时会发生什么？
   */

  /**
   * 10.java.util.TimeZone.getAvailableIDs交出的是诸如Africa/Cairo和Asia/Chungking这样的时区。哪一个洲的时区最多？提示：groupBy
   */

  /**
   * 11.Harry Hacker把某个文件的内容读取到字符串中，然后想对字符串的不同部分用并行集合来并发地更新字母出现的频率。他用了如下代码：
   * val frequencies=new scala.collection.mutable.HashMap[Char,Int]
   * for(c<-str.par) frequencies(c)=frequencies.getOrElse(c,0)+1
   * 为什么说这个想法很糟糕？要真正地并行化这个计算，他应该怎么做呢？
   * 提示：用aggregate
   */


}
