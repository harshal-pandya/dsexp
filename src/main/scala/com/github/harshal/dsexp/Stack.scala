package com.github.harshal.dsexp

import collection.mutable.ListBuffer

/**
 * @author harshal
 * @date: 2/27/13
 */
abstract class AbstractStack[T] {
  def pop():T
  def push(value:T)
  def top:T
  def isEmpty:Boolean
}

class Stack[T] extends AbstractStack[T]{
  private val stack = ListBuffer[T]()
  override def push(value:T){
    stack.prepend(value)
  }
  override def pop():T={
    stack.remove(0)
  }
  override def top:T={
    stack.head
  }
  override def isEmpty:Boolean = stack.length==0
}
