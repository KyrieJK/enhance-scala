package com.kyriejk

object chapter11 extends App {
  /**
   * 1.根据优先级规则，3+4->5和3->4+5是如何被求值的
   */
  def q1() {
    val res1 = 3 + 4 -> 5
    val res2 = 3 -> (4 + 5)
    println(res1)
    println(res2)
  }

  /**
   * 3.实现Fraction类，支持+ - * /操作。支持约分，例如将15/-6变成-5/2.除以最大公约数，像这样
   * class Fraction(n:Int,d:Int){
   * ......
   * }
   */
  class Fraction {
    var n: Int = _
    var d: Int = _

    def +(that: Fraction): Fraction = Fraction(this.n * that.d + that.n * this.d, this.d * that.d)

    def -(that: Fraction): Fraction = Fraction(this.n * that.d - that.n * this.d, this.d * that.d)

    def *(that: Fraction): Fraction = Fraction(this.n * that.n, this.d * that.d)

    def /(that: Fraction): Fraction = Fraction(this.n * that.d, this.d / that.n)


    def this(n: Int, d: Int) = {
      this()
      this.n = n
      this.d = d
      simplify()
    }

    def simplify(): Unit = {
      val r = n % d
      if (r == 0) {
        n /= d
        d = 1
      } else if (d % r == 0) {
        n /= r
        d /= r
      }
    }

    override def toString: String = {
      1.0 * n / d.toString
    }
  }

  object Fraction {
    def apply(n: Int, d: Int): Fraction = new Fraction(n, d)
  }

  /*
   * 11.4
   * 实现一个Money类,加入美元和美分字段。提供+,-操作符以及比较操作符==和<。
   * 举例来说，Money(1,75)+Money(0,50)==Money(2,25)应为true。
   * 你应该同时提供*和/操作符吗？为什么？
   */
  class Money(var cent: Int) {
    def this(dollar: Int, cent: Int) {
      this(dollar * 100 + cent)
    }

    def +(that: Money): Money = Money(this.cent + that.cent)

    def -(that: Money): Money = Money(this.cent - that.cent)

    def ==(that: Money): Boolean = this.cent == that.cent

    def <(that:Money):Boolean=if (this.cent-that.cent<0) true else false

  }

  object Money {
    def apply(cent: Int): Money = new Money(cent)

    def apply(dollar: Int, cent: Int): Money = new Money(dollar, cent)
  }

  /**
   * 7.实现一个BigSequence类，将64个bit的序列打包在一个Long值中
   * 提供apply和update操作来获取和设置某个具体的bit
   */
  class BigSequence{
    var bits=new Array[Boolean](64)
    def this(bits:Array[Boolean])={
      this()
      this.bits=bits
    }

    def apply(n:Int):Boolean=this.bits(n)
    def update(n:Int,value:Boolean)=bits(n)=value
  }

}
