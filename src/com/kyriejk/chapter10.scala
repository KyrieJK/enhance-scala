package com.kyriejk

import java.awt.Point
import java.awt.geom.Ellipse2D
import java.io.InputStream


object chapter10 extends App {

  /**
   * 1.java.awt.Rectangle类有两个很有用的方法translate和grow，但可惜的是像java.awt.geom.Ellipse2D这样的类中没有
   * 在Scala中，你可以解决这个问题。定义一个RectangleLike特质，加入具体的translate和grow方法。
   * 提供任何你需要用来实现的抽象方法。以便你可以像如下代码这样混入该特质：
   * val egg=new java.awt.geom.Ellipse2D.Double(5,10,20,30) with RectangleLike
   * egg.translate(10,-10)
   * egg.grow(10,20)
   *
   * @param args
   */
  trait RectangleLike {
    this: Ellipse2D.Double =>
    def translate(x: Double, y: Double): Unit = {
      this.x = x
      this.y = y
    }

    def grow(x: Double, y: Double): Unit = {
      this.x += x
      this.y += y
    }
  }

  /**
   * 2.通过把scala.math.Ordered[Point]混入java.awt.Point的方式，定义OrderedPoint类。
   * 按字典顺序排序，也就是说，如果x<x'或者x=x'且y<y'则(x,y)<(x',y')
   */
  class OrderedPoint extends java.awt.Point with Ordered[java.awt.Point] {
    override def compare(that: Point): Int = {
      if (this.x <= that.x && this.y <= that.y) -1 else if (this.x == that.x && this.y == that.y) 0 else 1
    }
  }

  /**
   * 3.查看BitSet类，将它的所有超类和特质绘制成一张图。忽略类型参数([...]中的所有内容)。然后给出该特质的线性化规格说明
   */

  /**
   * 4.提供一个CryptoLogger类，将日志消息以凯撒密码的方式加密。默认情况下密钥为3，不过使用者也可以重写它。
   * 提供默认密钥和-3作为密钥时的使用示例
   */
  trait Logger{
    def log(str:String,key:Int)
  }

  class CryptoLogger extends Logger{
    override def log(str: String, key: Int=3): Unit = {
      println((for (i <- str) yield (97 + (i - 97 + key + (if (key < 0) 26 else 0)) % 26).toChar).toString)
    }
  }

  /*
     * 10.6
     * 在Java AWT类库中, 我们有一个Container类, 一个可以用于各种组件的Component子类, 举例来说,
     * Button是一个Component, 但Panel是Container, 这是一个运转中的组合模式,
     * Swaing有JComponent和JContainer,但如果你仔细看的话,你会发现一些奇怪的细节,
     * 尽管把其他组件添加到比如JButton中毫无意义, JComponent依然扩展自Container,
     * Swing的设计者们理想情况下应该会更倾向于图10-4中的设计,
     * 但在Java中那是不可能的, 请解释这是为什么?Scala中如何用特质来设计出这样的效果呢?
     */
  trait Component{}
  trait JComponent extends Component{}
  class JButton extends JComponent{}
  trait Container extends Component{}
  trait JContainer extends JComponent with Container{}
  class JPanel extends JContainer{}


  /**
   * 9.在java.io类库中，你可以通过BufferedInputStream修饰器来给输入流增加缓冲机制。用特质来重新实现缓冲。为简单起见，重写read方法
   */
  trait Buffering{
    this:InputStream=>

    val BUF_SIZE=5
    private val buf=new Array[Byte](BUF_SIZE)
    private var bufsize=0
    private var pos=0

    override def read(): Int = {
      if (pos>=bufsize){
        bufsize=this.read(buf,0,BUF_SIZE)
        if (bufsize>0) -1
        pos=0
      }
      pos+=1
      buf(pos-1)
    }
  }
}
