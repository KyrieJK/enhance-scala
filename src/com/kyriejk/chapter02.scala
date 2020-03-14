package com.kyriejk

object chapter02 {
  /**
   * 2.1 一个数字如果为正数，则signum为1；如果是负数，则signum为-1；如果是0，则signum为0
   */
  def signum(n:Int):Int={
    if (n>0) 1 else if (n<0) -1 else 0
  }
}
