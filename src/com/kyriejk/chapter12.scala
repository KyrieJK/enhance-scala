package com.kyriejk

object chapter12 extends App {

  /**
   * 1.编写函数values(fun:(Int)=Int,low:Int,high:Int)，该函数需要交出一个集合，
   * 对应给定区间内给定函数的输入和输出。
   * 比如values(x=>x*x,-5,5)应该产出一个对偶的集合(-5,25),(-4,16),(-3,9),...,(5,25)
   */
  def values(fun: Int => Int, low: Int, high: Int) = {
    for (i <- low to high) yield (i, fun(i))
  }

  /**
   * 2.如何用reduceLeft得到数组中的最大元素
   */
  def q2(arr: Iterable[Int]): Int = {
    arr.reduceLeft((x, y) => if (x > y) x else y)
  }

  /**
   * 3.用to和reduceLeft实现阶乘函数，不得使用循环或递归
   */
  def q3(low: Int, high: Int) = {
    (low to high).reduceLeft(_ * _)
  }

  /**
   * 4.前一个实现需要处理一个特殊情况，即n<1的情况，展示如何用reduceLeft来免除这个必要
   * 在Scala中查找foldLeft的说明。它和reduceLeft很像，只不过所有需要结合在一起的这些值的首值在调用的时候给出
   */
  def q4(low: Int, high: Int) = {
    (low to high).foldLeft(1)(_ * _)
  }

  /*
   * 5.编写函数largest(fun:(Int)=>Int,inputs:Seq[Int]),
   * 输出在给定输入序列中给定函数的最大值。
   * 举例来说，largest(x=>10*x-x*x,1 to 10)应该返回25,不得使用循环或递归
   */
  def largest(fun: Int => Int, inputs: Seq[Int]): Unit = {
    fun(inputs.reduceLeft((x, y) => if (fun(x) > fun(y)) x else y))
  }

  /*
   * 6.修改前一个函数，返回最大的输出对应的输入。
   * 举例来说,largestAt(fun:(Int)=>Int,inputs:Seq[Int])应该返回5。
   * 不得使用循环或递归
   */
  def largestAt(fun: Int => Int, inputs: Seq[Int]) = {
    inputs.reduceLeft((x, y) => if (fun(x) > fun(y)) x else y)
  }

  /**
   * 7.要得到一个序列的对偶很容易，比如：val pairs=(1 to 10) zip (11 to 20)
   * 假定你想要对这个序列做某种操作-比如给对偶中的值求和，但是你不能直接用：
   * pairs.map(_+_)
   * 函数_+_接受两个Int作为参数，而不是(Int,Int)对偶。编写函数adjustToPair
   * 该函数接受一个类型为(Int,Int)=>Int的函数作为参数，并返回一个等效、可以以对偶作为参数的函数
   * 举例来说是：adjustPair(_*_)((6,7))
   * 然后用这个函数通过map计算出各个对偶的元素之和
   */
  def adjustPair(fun: (Int, Int) => Int)(pair: (Int, Int)) = {
    fun(pair._1, pair._2)
  }

  1 to 10 zip (11 to 20) map (adjustPair(_ * _)(_)) foreach (println)

  /**
   * 8.在12.8节中，你看到了用于两组字符串数组的corresponds方法。做出一个对该方法的调用，
   * 让它帮我们判断某字符串数组里的所有元素的长度是否和某个给定的整数数组相对应
   */
  def q8(strArr: Array[String], intArr: Array[Int]): Boolean = {
    strArr.corresponds(intArr)(_.length == _)
  }

  println(q8(Array("d", "dd"), Array(1, 2)))

  /**
   * 9.不使用柯里化实现corresponds。然后尝试从前一个练习的代码来调用。你遇到了什么问题
   */
  def q9(): Unit = {
    val arr = Iterator("asdf", "dd")

    def corresponds[B](that: Iterator[B], p: (String, B) => Boolean):Boolean = {
      while (arr.hasNext && that.hasNext) {
        if (!p(arr.next(), that.next())) {
          return false
        }
      }
      !arr.hasNext && !that.hasNext
    }

    println(corresponds(Iterator(3, 2), (x: String, y: Int) => x.length == y))
  }

  /**
   * 10.实现一个unless控制抽象，工作机制类似于if，但条件是反过来的。第一个参数需要是换名调用的参数吗？需要柯里化吗？
   */
  def unless(condition: =>Boolean)(block: =>Unit){
    if (!condition){
      block
    }
  }

  unless(0>1){
    println("Unless!")
  }

}
