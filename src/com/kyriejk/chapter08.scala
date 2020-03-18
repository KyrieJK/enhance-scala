package com.kyriejk

object chapter08 extends App {

  /**
   * 1.扩展如下的BankAccount类，新类CheckingAccount对每次存款和取款都收取1美元的手续费
   * class BankAccount(initialBalance:Double){
   * private var balance=initialBalance
   * def currentBalance=balance
   * def deposit(amount:Double)={balance+=amount;balance}
   * def withdraw(amount:Double)={balance-=amount;balance}
   * }
   *
   * @param args
   */
  class BankAccount(initialBalance: Double) {
    var per = 0.001
    private var balance = initialBalance

    def deposit(n: Double): Unit = {
      balance += n
    }

    def withdraw(n: Double): Unit = {
      balance -= n
    }

    def earnMonthlyInterest(): Unit = {
      balance *= per
    }
  }

  class CheckingAccount(initialBalance: Double) extends BankAccount(initialBalance) {
    override def deposit(n: Double): Unit = {
      super.deposit(n - 1)
    }

    override def withdraw(n: Double): Unit = {
      super.withdraw(n + 1)
    }
  }

  /**
   * 2.扩展前一个练习的BankAccount类，新类SavingsAccount每个月都有利息产生(earnMonthlyInterest方法被调用)
   * 并且有每月三次免手续费的存款或者取款。在earnMonthlyInterest方法中重置交易计数
   *
   * @param args
   */
  class SavingsAccount(initialBalance: Double) extends BankAccount(initialBalance) {

    private var sum = 0

    override def deposit(n: Double): Unit = {
      super.deposit(if (sum < 3) n else n - 1)
      sum += 1
    }

    override def withdraw(n: Double): Unit = {
      super.withdraw(if (sum < 3) n else n + 1)
      sum += 1
    }

    override def earnMonthlyInterest(): Unit = {
      super.earnMonthlyInterest()
      sum=0
    }
  }

  /**
   * 3.翻开你喜欢的Java或者C++教科书，一定会找到来讲解继承层级的示例，这些示例可能是员工、宠物、图形或类似的东西。
   * 用Scala来实现这个示例
   *
   * @param args
   */

  /**
   * 4.定义一个抽象类Item，加入方法price和description。SimpleItem是一个在构造器中给出价格和描述的物件
   * 利用val可以重写def这个事实。Bundle是一个可以包含其他物件的物件。其价格是打包中所有物件的价格之和。
   * 同时提供一个将物件添加到打包中的机制，以及一个合适的description方法
   *
   * @param args
   */

  abstract class Item{
    def price():Double
    def description():String
  }

  class SimpleItem(var p:Double,var des:String) extends Item{
    def this(p:Double){
      this(p,"")
    }

    override def price(): Double = p

    override def description(): String = des
  }

  class Bundle(var th:Array[SimpleItem]) extends Item{
    override def price(): Double = {
      var p=0.0
      th.foreach(p+=_.price())
      p
    }

    override def description(): String = {
      var s=""
      th.foreach(s+=_.description())
      s
    }
  }

  /**
   * 5.设计一个Point类，其x和y坐标可以通过构造器提供。提供一个子类LabeledPoint，其构造器接受一个标签值和x、y坐标
   * 比如：new LabeledPoint("Black Thursday",1929,230.07)
   *
   * @param args
   */
  class Point(var x:Double,var y:Double){}
  class LabeledPoint(var des:String,x:Double,y:Double) extends Point(x,y){}

  /**
   * 6.定义一个抽象类Shape、一个抽象方法centerPoint，以及该抽象类的子类Rectangle和Circle。
   * 为子类提供合适的构造器，并重写centerPoint方法
   *
   * @param args
   */
  abstract class Shape(var p:Point){
    def centerPoint():Point
  }
  class Rectangle(p:Point) extends Shape(p){
    override def centerPoint(): Point = {
      p
    }
  }
  class Circle(p:Point) extends Shape(p){
    override def centerPoint(): Point = {
      p
    }
  }

  /**
   * 7.提供一个Square类，其扩展自java.awt.Rectangle并且有三个构造器：
   * 一个以给定的端点和宽度构造正方形
   * 一个以（0，0）为端点和给定的宽度构造正方形
   * 还有一个以（0，0）为端点，0为宽度构造正方形
   *
   * @param args
   */
  class Square(p:java.awt.Point,d:java.awt.Dimension) extends java.awt.Rectangle(p,d){
    def this(){
      this(new java.awt.Point(0,0),new java.awt.Dimension(0,0))
    }
    def this(p:java.awt.Point){
      this(p,new java.awt.Dimension(0,0))
    }
  }

  /**
   * 8.编译8.6节中的Person和SecretAgent类并使用javap分析类文件。总共有多少个name字段？总共有多少个对应name字段的getter方法
   * 它们分别取什么值
   *
   * @param args
   */


  /**
   * 9.在8.10节的Creature类中，将val range替换成一个def。如果你在Ant子类中也用def的话会有什么效果
   * 如果在子类中使用val又会有什么效果？为什么
   *
   * @param args
   */

  /**
   * 10.文件scala/collection/immutable/Stack.scala包含如下定义：
   * class Stack[A] protected (protected val elems:List[A])
   * 请解释protected关键字的含义
   *
   * @param args
   */

  /**
   * 11.定义值类Point，将整数的x和y坐标打包成一个Long（你应该将这个Long做成私有的）（译者注：在64位的Long中，两个整数各占32位）
   *
   * @param args
   */
}
