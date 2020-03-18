package com.kyriejk

import java.io.{BufferedWriter, File, FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream, OutputStreamWriter, PrintWriter}

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object chapter09 {


  /**
   * 1.编写一小段Scala代码，将某个文件中的行倒转顺序（将最后一行作为第一行，以此类推）
   *
   * @param args
   */
  def q1(input: String, output: String) = {
    val src = Source.fromFile(input, "UTF-8")
    val lines = src.getLines()
    val bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)))
    try {
      (lines.toArray.reverse).foreach(
        x => {
          println(x)
          bw.write(x + "\r\n")
          bw.flush()
        }
      )
    } finally {
      src.close()
      bw.close()
    }
  }


  /**
   * 2.编写Scala程序，从一个带有制表符的文件读取内容，将每个制表符替换成一组空格，使得制表符隔开的n列仍然保持纵向对齐
   * 并将结果写入同一个文件
   *
   * @param args
   */
  def q2(input: String, output: String) = {
    val src = Source.fromFile(input).mkString
    val pattern = """\t""".r
    var result = pattern.replaceAllIn(src, " ")
    val out = new PrintWriter(output)
    out.print(result)
    out.close()
  }

  /**
   * 3.编写一小段Scala代码，从一个文件读取内容并把所有字符数大于12的单词打印到控制台。如果你能用单行代码完成还有额外奖励
   *
   * @param args
   */
  def q3(input: String) = {
    Source.fromFile(input, "UTF-8").getLines().foreach(x => """\b\w{13,}\b""".r.findAllIn(x).foreach(d => println(d)))
  }

  /**
   * 4.编写Scala程序，从包含浮点数的文本文件读取内容，打印出文件所有浮点数之和，平均值、最大值和最小值
   */
  def q4(input: String) = {
    val arr = new ArrayBuffer[Double]()
    Source.fromFile(input, "UTF-8").getLines().map(x => """\b\d(\.\d+)?\b""".r.findAllIn(x).foreach(x => x.foreach(arr += _.toDouble)))
    Predef.println("sum:" + arr.sum + ",avg:" + arr.sum / arr.length + ",min:" + arr.min)
  }

  /**
   * 5.编写Scala程序，向文件中写入2的n次方及其倒数，指数n从0到20.对齐各列：
   * 1  1
   * 2  0.5
   * 4  0.25
   * ...  ...
   *
   * @param args
   */
  def q5(output: String, n: Int) = {
    val bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)))
    try {
      if (n >= 0) {
        0 to n foreach (x => {
          val r = (BigInt(2).pow(x)).toString
          bw.write(r + " " * (3 - r.length()) + 1.0 / r.toInt + "\r\n")
        })
      }
    } finally {
      bw.close()
    }
  }


  /**
   * 6.编写正则表达式，匹配Java或C++程序代码中类似于"like this，maybe with\" or \\"这样的带引号的字符串
   * 编写Scala程序将某个源文件中所有类似的字符串打印出来
   *
   * @param args
   */
  def q6(input: String): Unit = {
    """.+(?<!\\)""".r.findAllIn(input).foreach(println)
  }


  /**
   * 7.编写Scala程序，从文本文件读取内容，并打印出所有非浮点数的词法单元。要求使用正则表达式
   *
   * @param args
   */

  /**
   * 8.编写Scala程序，打印出某个网页中所有img标签的src属性。使用正则表达式和分组
   *
   * @param args
   */

  /**
   * 9.编写Scala程序，盘点给定目录及其子目录中总共有多少以.class为扩展名的文件
   *
   * @param args
   */
  def q9(files: File*) = {
    val dirs = files.filter(_.isDirectory)
    if (dirs.length > 0) {
      (Iterable[File]() /: dirs) {
        (list, f) => list ++ files.filter( x => x.isFile && x.getName.endsWith(".class")) ++ q9(f.listFiles(): _*))
      }
    }else{
      files.filter(x=>x.isFile&&x.getName.endsWith(".class"))
    }
  }

  /**
   * 10.扩展9.8节的示例。构造出一些Person对象，让其中的一些人成为朋友，然后将Array[Person]保存到文件
   * 将这个数组从文件中重新读出来，校验朋友关系是否完好
   *
   * @param args
   */
    def q10()={
      val p=new Person("Tom")
      p.addFriend(new Person("Jack"))

      val os = new ObjectOutputStream(new FileOutputStream("result.txt"))
      os.writeObject(p)
      os.close()
      val oi=new ObjectInputStream(new FileInputStream("result.txt"))
      var sp =oi.readObject().asInstanceOf[Person]
      oi.close()

      println(sp)
    }

  class Person(val name:String) extends Serializable{
    var friends = new collection.mutable.ArrayBuffer[Person]()

    def addFriend(p:Person*): Unit ={
      friends.appendAll(p)
    }

    override def toString: String={
      var res="My name is:"+name+",my friends are:"
      friends.foreach(res+=_.name+",")
      res.dropRight(1)
    }
  }



  def main(args: Array[String]): Unit = {

    
  }
}
