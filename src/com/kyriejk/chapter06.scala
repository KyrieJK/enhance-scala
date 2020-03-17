package com.kyriejk

object chapter06 extends App {

  /**
   * 1.编写一个Conversions对象，加入inchesToCentimeters、gallonsToLiters和milesToKilometers方法
   *
   * @param args
   */
  def q1() = {
    object Conversations {
      def inches2Centimeters(inch: Double) = {
        inch * 2.54
      }

      def gallonsToLiters(gallon: Double) = {
        gallon * 3.78541178
      }

      def milesToKilometers(mile: Double) = {
        mile * 0.6213712
      }
    }
  }

  /**
   * 2.前一个练习不是很面向对象。提供一个通用的超类UnitConversion并定义扩展该超类的InchesToCentimeters、GallonsToLiters
   * 和MilesToKilometers对象
   *
   * @param args
   */
  def q2() = {
    abstract class UnitConversion {
      def inchesToCentimeters() {}

      def gallonsToLiters() {}

      def milesToKilometers() {}
    }

    object InchesToCentimeters extends UnitConversion {
      override def inchesToCentimeters(): Unit = {}
    }
    object GallonsToLiters extends UnitConversion {
      override def gallonsToLiters(): Unit = {}
    }
    object MilesToKilometers extends UnitConversion {
      override def milesToKilometers(): Unit = {}
    }
  }

  /**
   * 3.定义一个扩展自java.awt.Point的Origin对象。为什么说这实际上不是一个好主意（仔细看Point类的方法）
   *
   * @param args
   */
  def q3() = {
    class Origin extends java.awt.Point {}
  }


  /**
   * 4.定义一个Point类和一个伴生对象，使得我们可以不用new而直接用Point(3,4)来构造Point实例
   *
   * @param args
   */
  def q4() = {
    class Point(val x: Int, val y: Int) {}
    object Point {
      def apply(x: Int, y: Int): Point = new Point(x, y)
    }
  }

  /**
   * 5.编写一个Scala程序，使用App特质，以反序打印命令行参数，用空格隔开。
   * 举例来说，scala Reverse Hello World应该打印出World Hello
   *
   * @param args
   */
  def q5() = {
    args.reverse.foreach(x => print(x + " "))
  }


  /**
   * 6.编写一个扑克牌四种花色的枚举，让其toString方法分别返回♣、♦、♥、♠
   *
   * @param args
   */
  def q6() = {
    object Joker extends Enumeration {
      val M: Value = Value("♣")
      val F: Value = Value("♦")
      val H: Value = Value("♥")
      val B: Value = Value("♠")

      def isRed(card: Joker.Value):Boolean = card == H || card == F
    }
    println(Joker.values)
    println(for (c<-Joker.values) yield (c,Joker.isRed(c)))
  }

  /**
   * 7.实现一个函数，检查某张牌的花色是否为红色
   *
   * @param args
   */
  def q7()={
    q6()
  }


  /**
   * 8.编写一个枚举，描述RGB立方体的八个角。ID使用颜色值（例如，红色/Red是0xff0000）
   *
   * @param args
   */
  def q8()={
    object RGBCube extends Enumeration{
      val black=Value(0x000000,"Black")
      val red = Value(0xff0000,"Red")
      val green =Value(0x00ff00,"Green")
      val blue=Value(0x0000ff,"Blue")
      val yellow=Value(0xffff00,"Yellow")
      val magenta=Value(0xff00ff,"Magenta")
      val cyan=Value(0x00ffff,"Cyan")
      val white=Value(0xffffff,"White")
    }
    for (c<-RGBCube.values) println("0x%06x:%s".format(c.id,c))
  }
}
