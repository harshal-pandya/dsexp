package com.github.harshal.dsexp.tree

/**
 * @author harshal
 * @date: 2/24/13
 */

case class Node[T](value:T,right:Node,left:Node)

abstract class Tree(root:Node){
  def add(node:Node)
  def remove(node:Node)

}
