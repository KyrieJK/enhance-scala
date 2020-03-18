package com.kyriejk

object chapter12 extends App {

  /**
   * 1.编写函数values(fun:(Int)=Int,low:Int,high:Int)，该函数需要交出一个集合，
   * 对应给定区间内给定函数的输入和输出。
   * 比如values(x=>x*x,-5,5)应该产出一个对偶的集合(-5,25),(-4,16),(-3,9),...,(5,25)
   */
  def values(fun:Int=>Int,low:Int,high:Int)={
    for (i<-low to high) yield(i,fun(i))
  }

  /**
   * 2.如何用reduceLeft得到数组中的最大元素
   */
  def q2(arr:Iterable[Int]):Int={
    arr.reduceLeft((x,y)=>if (x>y) x else y)
  }

  /**
   * 3.用to和reduceLeft实现阶乘函数，不得使用循环或递归
   */
  def q3(low:Int,high:Int)={
    (low to high).reduceLeft(_*_)
  }

  /**
   * 4.前一个实现需要处理一个特殊情况，即n<1的情况，展示如何用reduceLeft来免除这个必要
   * 在Scala中查找foldLeft的说明。它和reduceLeft很像，只不过所有需要结合在一起的这些值的首值在调用的时候给出
   */
  def q4(low:Int,high:Int)={
    (low to high).foldLeft(1)(_*_)
  }

  /*
   * 12.5
   * 编写函数largest(fun:(Int)=>Int,inputs:Seq[Int]),
   * 输出在给定输入序列中给定函数的最大值。
   * 举例来说，largest(x=>10*x-x*x,1 to 10)应该返回25,不得使用循环或递归
   */
  def largest(fun:Int=>Int,inputs:Seq[Int]): Unit ={
    fun(inputs.reduceLeft((x,y)=>if (fun(x)>fun(y)) x else y))
  }

  /*
   * 12.6
   * 修改前一个函数，返回最大的输出对应的输入。
   * 举例来说,largestAt(fun:(Int)=>Int,inputs:Seq[Int])应该返回5。
   * 不得使用循环或递归
   */
  def largestAt(fun:Int=>Int,inputs:Seq[Int])={
    inputs.reduceLeft((x,y)=>if (fun(x)>fun(y)) x else y)
  }

}
