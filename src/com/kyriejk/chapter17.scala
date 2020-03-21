package com.kyriejk

object chapter17 extends App {

  /*
   * 1.
   * 定义一个不可变类Pair[T,S]，带一个swap方法，返回组件交换过位置的新对偶。
   */
  class Pair[T, S](val first: T, val second: S) {
    def swap: Pair[S, T] = new Pair[S, T](second, first)
  }

  /*
   * 2.
   * 定义一个可变类Pair[T]，带一个swap方法，交换对偶中组件的位置。
   */
  class Pair[T](val first: T, val second: T) {
    def swap: Pair[T] = new Pair[T](second, first)
  }

  /*
   * 3.
   * 给定类Pair[T,S]，编写一个泛型方法swap，接受对偶作为参数并返回组件交换过位置的新对偶。
   */
  object ex03 {

    class Pair[T, S](val first: T, val second: S) {
      def swap[T, S](p: Pair[T, S]): Pair[S, T] = {
        new Pair[S, T](p.second, p.first)
      }
    }

  }

  /*
   * 4.
   * 在17.3节中，如果我们想把Pair[Person]的第一个组件替换成Student，为什么不需要给replaceFirst方法定一个下界？
   */
  object ex04 extends App {

    class Person

    class Student extends Person

    class Pair[T](val first: T, val second: T) {
      def replaceFirst(r: T): Pair[T] = new Pair[T](r, second)
    }

    val personA = new Person
    val personB = new Person

    val pairA = new Pair[Person](personA, personB)
    val stuA = new Student

    val pairB = pairA.replaceFirst(stuA)
  }


  /*
   * 5.
   * 为什么RichInt实现的是 Comparable[Int]而不是Comparable[RichInt]？
   * --由于隐式转换的原因, Int可以被隐式转换成RinchInt, 所以 实现Comparable[Int]可以更通用,
   * 即可以调用compareTo(Int)也可以调用compareTo(RichInt)
   */

  /*
   * 6.
   * 编写一个泛型方法middle，返回任何Iterable[T]的中间元素。举例来说，middle["World"]应得到'r'。
   */
  def middle[T](it: Iterable[T]): T = {
    val l = it.toList
    it.foldLeft()
    l(l.size / 2)
  }

  /*
   * 7.
   * 查看Iterable[+A]特质。哪些方法使用了类型参数A？为什么在这些方法中类型参数位于协变点？"
   * foldLeft,groupBy等方法使用了类型参数A
   * 使用注释 +A，可以使一个泛型类的类型参数 A 成为协变。 对于某些类 class List[+A]，使 A 成为协变意味着对于两种类型 A 和 B，如果 A 是 B 的子类型，那么 List[A] 就是 List[B] 的子类型。 这允许我们使用泛型来创建非常有用和直观的子类型关系。
   */

  /*
   * 10.
   * 给定可变类Pair[S, T]，使用类型约束定义一个swap方法，当类型参数相同时可以被调用。
   */
  object ex10 extends App {

    class Pair[S, T](var first: S, var second: T) {
      def swap(implicit ev: S =:= T):Pair[T,S]= new Pair[T,S](second,first)
    }

  }

}
