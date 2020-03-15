package com.kyriejk

import scala.math.pow

object chapter02 {
  /**
   * 2.1 一个数字如果为正数，则signum为1；如果是负数，则signum为-1；如果是0，则signum为0
   */
  def signum(n: Int): Int = {
    if (n > 0) 1 else if (n < 0) -1 else 0
  }

  /**
   * 2.2 一个空的块表达式{}的值是什么？类型是什么？
   */
  def emptyBlockType: Unit = {
    val x = {}
    println(x)
    println({}.getClass())
  }

  /**
   * 2.3 指出Scala中何种情况下赋值语句x=y=1是合法的（提示：给x找个合适的类型定义）
   * 赋值语句的值是Unit类型的，因此最好别把它们串接在一起。
   * y=1的值是()，你几乎不太可能想把一个Unit类型的值赋给x
   *
   * @param args
   */
  def assignLegal: Unit = {
    var x: Unit = {}
    println("x's type is " + x.getClass)
    var y = 1
    x = y = 1
  }

  /**
   * 2.4 针对下列Java循环编写一个Scala版本：
   * for(int i=10;i>=0;i--) System.out.println(i);
   *
   * @param args
   */
  def scalaForeach: Unit = {
    (0 to 10 reverse) foreach (println)
  }

  /**
   * 2.5 编写一个过程countdown(n:Int)，打印从n到0的数字
   *
   * @param args
   */
  def countdown(n: Int): Unit = {
    if (n >= 0) {
      (0 to n reverse) foreach (println)
    } else if (n < 0) {
      (n to 0) foreach (println)
    }
  }

  /**
   * 2.6 编写一个for循环，计算字符串中所有字母的Unicode代码的乘积。举例来说，"Hello"中所有字符的乘积为9415087488L
   *
   * @param args
   */
  def calculateUnicode(s: String) = {
    var result: Long = 1
    s foreach (result *= _.toLong)
    result
  }

  /**
   * 2.7 同样是解决前一个练习的问题，但是这次不使用循环（提示：在Scaladoc中查看StringOps）
   *
   * @param args
   */
  def calculateUnicodeWithNoloop(s: String) = {
    s.foldLeft(1.toLong)(_ * _)
  }

  /**
   * 2.8 编写一个函数product(s:String)，计算前面练习中提到的乘积
   * 2.9 把前一个练习中的函数改成递归函数
   *
   * @param args
   */
  def product(s: String):Long = {
    if (s.length == 1) {
      s(0).toLong
    } else {
      s(0).toLong * product(s.tail)
    }
  }

  /**
   * 2.10 编写函数计算xn，其中n是整数，使用如下的递归定义：
   *
   * @param args
   */
  def xPowerN(x: Double, n: Int):Double = {
    if (n > 0) {
      if (n % 2 == 0) pow(xPowerN(x, n / 2), 2)
      else x * xPowerN(x, n - 1)
    } else if (n == 0) {
      1
    } else {
      1 / xPowerN(x, -n)
    }
  }

  def main(args: Array[String]): Unit = {
    printf("(2.0,2)=%5f\n", xPowerN(2.0, 2))
    printf("(2.0,2)=%5f\n", xPowerN(2.0, 3))
    printf("(2.0,2)=%5f\n", xPowerN(2.0, 0))
  }
}
