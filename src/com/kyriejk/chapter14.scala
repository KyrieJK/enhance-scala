package com.kyriejk

object chapter14 extends App {

  /**
   * 1.JDK发行包有一个src.zip文件包含了JDK的大多数源代码，
   * 解压并搜索样例标签(用正则表达式case [^:]+:)
   * 然后查找以//开头并包含[Ff]alls? thr的注释，捕获类似//Falls through或//just fall thru这样的注释
   * 假定JDK的程序员们遵守Java编码习惯，在该写注释的地方写下了这些注释，有多少百分比的样例是会掉入下一分支的
   **/

  /**
   * 2.利用模式匹配，编写一个swap函数，接受一个整数的对偶，返回对偶的两个组成部件互换位置的新对偶
   */
  def fun2(pair: (Int, Int)): (Int, Int) = {
    pair match {
      case (x, y) => (y, x)
    }
  }

  /**
   * 3.利用模式匹配，编写一个swap函数，交换数组中前两个元素的值，前提条件时数组程度至少为2
   */
  def q3(arr: Array[Int]): Array[Int] = {
    arr match {
      case Array(a, b, rest@_*) => Array(b, a) ++ rest
    }
  }

  /**
   * 4.添加一个样例类Multiple，作为Item类的子类，举例来说，
   * Multiple(10,Article("Blackwell Toster",29.95))描述的是10个烤面包机
   * 当然了，你应该可以在第二个参数的位置接受任何Item，不论是Bundle还是另一个Multiple。扩展price函数以应对这个新的样例
   */
  abstract class Item

  case class Article(description: String, price: Double) extends Item

  case class Bundle(description: String, discount: Double, items: Item*) extends Item

  case class Multiple(count: Int, item: Item) extends Item

  def price(item: Item) = {
    item match {
      case Article(_, price) => price
      case Bundle(_, discount, items@_*) => items.map(price _).sum - discount
      case Multiple(count, item) => price(item) * count
    }
  }

  val x = Bundle("Father's day special", 20.0, Multiple(1, Article("Scala for the Impatient", 39.95)),
    Bundle("Anchor Distillery Smapler", 10.0, Article("Old Potrero Straight Rye Whiskey", 79.95), Article("JunApero Gin", 32.95))
  )

  /*
   * 5.
   * 我们可以用列表制作只在叶子节点存放值的树, 举例来说,
   * 列表((3 8)2 (5))描述的是如下这样一棵树:
   *      .
   *     /|\
   *    . 2 .
   *   / \  |
   *  3  8  5
   * 不过, 有些列表元素是数字, 而另一些是列表. 在Scala中, 你不能拥有异构的列表,
   * 因此你必须使用List[Any].
   * 编写一个leafSum函数, 计算所有叶子节点中的元素之和, 用模式匹配来区分数字和列表.
   */
  def leafSum(tree: List[Any]): Int = {
    var sum = 0
    tree.foreach(
      _ match {
        case n: Int => sum += n
        case lst: List[Any] => sum += leafSum(lst)
      }
    )
    sum
  }

  /*
   * 6、7、8合并
   * 6.
   *   制作这样的树更好的做法是使用样例类, 我们不妨从二叉树开始.
   *   sealed abstract class BinaryTree
   *   case class Leaf(value: Int) extends BinaryTree
   *   case class Node() extends BinaryTree
   *   编写一个函数计算所有叶子节点中的元素之和
   * 7.
   *   扩展前一个练习中的树, 使得每个节点可以有任意多的后代, 并重新实现leafSum函数.
   *   第五题中的树应该能够通过下述代码表示:
   *     Node(Node(Leaf(3),Leaf(8)),Leaf(2),Node(Leaf(5)))
   * 8.
   *   扩展前一个练习中的树, 使得每个非叶子节点除了后代值外, 能够存放一个操作符.
   *   然后编写一个eval函数来计算它的值, 举例来说:
   *         +
   *        /|\
   *       * 2 -
   *      /\   |
   *     3  8  5
   * 上面这棵树的值为(3 * 8) + 2 -5 = 21
   */
  sealed abstract class BinaryTree {
    def leafSum(): Int

    var parent: BinaryTree = null
    var children: Iterable[BinaryTree] = null
    var op: (Int, Int) => Int = {
      _ + _
    }
    var initValue = 0

    def eval(oper: String): (Int, Int) => Int = {
      oper match {
        case "+" => _ + _
        case "-" => _ - _
        case "*" => _ * _
        case "/" => _ / _
      }
    }

    def this(oper: String) = {
      this()
      this.op = eval(oper)
    }
  }

  case class Leaf(value: Int) extends BinaryTree {
    override def leafSum(): Int = value
  }

  case class Node() extends BinaryTree {
    override def leafSum(): Int = {
      children.foldLeft(initValue)((sum, l) => op(sum, l.leafSum()))
    }

    def this(children: BinaryTree*) {
      this()
      this.children = children
      children.foreach(_.parent = this)
    }

    def this(oper: String, children: BinaryTree*) {
      this(children: _*)
      if ("*/".contains(oper)) initValue = 1
      this.op = eval(oper)
    }
  }

  object Node {
    def apply(children: BinaryTree*): Node = new Node(children: _*)

    def apply(oper: String, children: BinaryTree*): Node = new Node(oper, children: _*)
  }

  /**
   * 9.
   * 编写一个函数，计算List[Option[Int]]中所有非None值之和
   * 不得使用match语句
   */
  def fun9(list: List[Option[Int]]): Int = {
    list.collect {
      case Some(x) => x
    }.sum
  }

  /*
   * 10
   * 编写一个函数, 将两个类型为Double => Option[Double]的函数组合在一起,
   * 产生另一个同样类型的函数. 如果其中一个函数返回None, 则组合函数也应返回None
   * 例如:
   * 		def f(x:Double) = if (x >= 0) Some(sqrt(x)) else None
   *    def g(x:Double) = if (x != 1) Some(1 / (x-1)) else None
   *    val h = compose(f, g)
   * h(2)将得到Some(1), 而h(1)和h(0)将得到None.
   * 这里简单加一下
   */
  def compose(f1: Double => Option[Double], f2: Double => Option[Double]): Double => Option[Double] = {
    x =>
      (f1(x), f2(x)) match {
        case (None,_)=>None
        case (_,None)=>None
        case (Some(a),Some(b))=>Some(a+b)
      }
  }
}
