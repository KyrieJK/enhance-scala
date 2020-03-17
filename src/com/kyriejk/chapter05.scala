package com.kyriejk

import scala.beans.BeanProperty

object chapter05 {

  /**
   * 1.改进5.1节的Counter类，让它不要在Int.MaxValue时变成负数
   *
   * @param args
   */
  def q1 = {
    class Counter {
      private var value = 0

      def increment = if (value == Int.MaxValue) value else value += 1

      def current = value

      def set(n: Int) = value = n
    }

    val c = new Counter
    c.set(Int.MaxValue)
    println(c.increment)
  }

  /**
   * 2.编写一个BankAccount类，加入deposit和withdraw方法，以及一个只读的balance属性
   *
   * @param args
   */
  def q2 = {
    class BankAccount {
      private var account: Double = 0

      def deposit(value: Double) = {
        account += value
      }

      def withdraw(value: Double) = {
        if (value < account) {
          account -= value
        } else {
          throw new Exception("Your balance is not enough")
        }
      }

      def balance = account
    }

    val b = new BankAccount
    b.deposit(1000)
    b.withdraw(300)
    println(b.balance)
  }

  /**
   * 3.编写一个Time类，加入只读属性hours和minutes，以及一个检查某一时刻是否早于另一时刻的方法before(other:Time):Boolean。
   * Time对象应该以new Time(hrs,min)方式构建，其中hrs小时数以军用时间格式呈现(介于0和23之间)
   *
   * @param args
   */
  def q3(hr: Int, min: Int) = {
    class Time(hr: Int, min: Int) {
      val hours = hr
      val minutes = min

      def before(other: Time): Boolean = {
        if (hours < other.hours) true else if (hours > other.hours) false else if (minutes < other.minutes) true else false
      }
    }
  }

  /**
   * 4.重新实现前一个练习中的Time类，将内部呈现改成自午夜起的分钟数(介于0到24x60-1之间)。不要改变公有接口。也就是说，客户端
   * 代码不应因你的修改而受到影响
   *
   * @param args
   */
  def q4(hr: Int, min: Int) = {
    class Time(hr: Int, min: Int) {
      val minute = hr * 60 + min

      def before(other: Time): Boolean = {
        if (minute < other.minute) true else false
      }
    }
  }

  /**
   * 5.创建一个Student类，加入可读写的JavaBeans属性name(类型为String)和id(类型为Long)。有哪些方法被生成
   * 你可以在Scala中调用JavaBeans版的getter和setter方法吗？应该这样做吗
   *
   * @param args
   */

  def q5 = {
    class Student {
      @BeanProperty var id = 0L
      @BeanProperty var name = ""
    }

    var s = new Student
    s.setId(1)
    s.setName("aaa")
    println(s.getId + ":" + s.getName)
  }

  /**
   * 6.在5.1节的Person类中提供一个主构造器，将负年龄转换为0
   *
   * @param args
   */
  def q6 = {
    class Person(var age: Int = 0) {
      if (age < 0) age = 0
    }
    val p = new Person(5)
    println(p.age)
    val n = new Person(-5)
    println(n.age)
  }

  /**
   * 7.编写一个Person类，其主构造器接受一个字符串，该字符串包含名字、空格和姓名，比如new Person("Fred Smith").
   * 提供只读属性firstName和lastName。主构造器参数应该是var、val还是普通参数呢？为什么
   *
   * @param args
   */
  def q7 = {
    class Person(name: String) {
      val firstName = name.split(" ")(0)
      val lastName = name.split(" ")(1)
    }
  }

  /**
   * 8.创建一个Car类，以只读属性对应制造商、型号名称、型号年份以及一个可读写的属性用于车牌。提供四组构造器。
   * 每一个构造器都要求制造商和型号名称为必填。型号年份以及车牌为可选，如果未填写，则型号年份设置为-1
   * 车牌设置为空字符串。你会选择哪一个作为你的主构造器？为什么
   *
   * @param args
   */
  def q8 = {
    class Car(val manufacture: String, val model: String, val year: Int = -1, var license: String = "") {
      override def toString: String = "Car(%s %s %d %s)".format(manufacture, model, year, license)
    }

    val c1 = new Car("Benz", "Civic", 2011, "asd13")
    val c2 = new Car("Hummer", "Z2", 2008)
    val c3 = new Car("Tesla", "Astra")
    println(c3)
  }

  /**
   * 9.在Java、C#或C++中重做前一个练习。相比之下Scala精简了多少？
   *
   * @param args
   */

  /**
   * 考虑如下类：
   * class Employee(val name:String,var salary:Double){
   * def this(){this("John Q. Public",0.0)}
   * }
   *
   * 重写该类，使用显式的字段定义和一个默认主构造器。你更倾向于使用哪一种形式？为什么？
   *
   * @param args
   */
  def q10: Unit = {
    class Employee(val name: String = "John Q. Public", var salary: Double = 0) {

    }
  }


  def main(args: Array[String]): Unit = {


  }

}
