package com.github.harshal.dsexp

import collection.mutable.ListBuffer

/**
 * @author harshal
 * @date: 2/27/13
 */
abstract class AbstractQueue[T] {
  def enqueue(value:T)
  def dequeue:T
  def isEmpty:Boolean
}

class Queue[T] extends AbstractQueue[T]{
  val queue = ListBuffer[T]()
  override def enqueue(value:T){
    queue+=value
  }
  override def dequeue:T=queue.remove(0)
  override def isEmpty:Boolean = queue.length==0
}
