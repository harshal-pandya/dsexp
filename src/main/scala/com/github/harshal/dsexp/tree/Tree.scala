package com.github.harshal.dsexp.tree

import collection.mutable
import collection.mutable.ArrayBuffer
import util.Random
import javax.management.remote.rmi._RMIConnection_Stub

/**
 * @author harshal
 * @date: 2/24/13
 */

case class Node[K](value:K,var left:Option[Node[K]],var right:Option[Node[K]],var parent:Option[Node[K]])(implicit ord:K=>Ordered[K]){
  def hasLeft:Boolean = if (left!=None) true else false
  def hasRight:Boolean = if (right!=None) true else false
  def hasParent:Boolean = if (parent!=None) true else false
}

abstract class BinaryTree[K](implicit ord:K=>Ordered[K]){
  def add(value:K)
  def remove(value:K):Boolean
  def height:Int
  def size:Int
}

class Tree[K](implicit ord:K=>Ordered[K]) extends BinaryTree[K]{
  var root:Option[Node[K]] = None
  private var count = 0
  override def add(value:K){
    root match{
      case None => root = Some(new Node[K](value,None,None,None))
      case Some(node) => if(insert(node,value)) count+=1
    }
  }

  def insert(node:Node[K],newVal:K):Boolean={
    if(newVal<node.value){
      node match{
        case Node(_,None,_,_) => node.left = Some(new Node[K](newVal,None,None,Some(node))); true
        case Node(_,Some(left),_,_) => insert(left,newVal)
      }
    }else if(newVal>node.value){
      node match{
        case Node(_,_,None,_) => node.right = Some(new Node[K](newVal,None,None,Some(node))); true
        case Node(_,_,Some(right),_) => insert(right,newVal)
      }
    }
    else false
  }

  override def remove(value:K):Boolean={
    root match{
      case None => false
      case Some(node) => {
        binarySearch(value,node) match{
          case None => false
          case Some(node) => {
            count-=1
            delete(node)
            true
          }
        }
      }
    }
  }

  def delete(node:Node[K]) {
    node match {
      case Node(value,None,None,Some(parent)) => updateParent(parent,value,None)
      case Node(value,Some(child),None,Some(parent)) => {
        updateParent(parent,value,Some(child))
        child.parent = Some(parent)
      }
      case Node(value,None,Some(child),Some(parent)) => {
        updateParent(parent,value,Some(child))
        child.parent = Some(parent)
      }
      case Node(_,Some(child),None,None) => {
        root = Some(child)
        child.parent = None
      }
      case Node(_,None,Some(child),None) => {
        root = Some(child)
        child.parent = None
      }
      case Node(_,Some(left),Some(right),_) => {
        var child = right
        while(child.left!=None){
          child=child.left.get
        }
        node.parent match{
          case Some(parent) => updateParent(parent,node.value,Some(child))
          case None => root = Some(child)
        }
        child.left = node.left
        child.right = node.right
        left.parent = Some(child)
        right.parent = Some(child)
        if (child.hasParent && child.parent.get.hasLeft && child.parent.get.left.get == child) child.parent.get.left=None
        else child.parent.get.right=None
        child.parent = node.parent
      }
      case _ =>
    }
    def updateParent(parent:Node[K],value:K,newChild:Option[Node[K]]) {
      if(value<parent.value) parent.left = newChild
      else parent.right = newChild
    }
  }

  def binarySearch(value:K,node:Node[K]):Option[Node[K]]={
    if (value==node.value) Some(node)
    else if (value<=node.value) {
      node match{
        case Node(_,None,_,_) => None
        case Node(_,Some(left),_,_) => binarySearch(value,left)
      }
    }else{
      node match{
        case Node(_,_,None,_) => None
        case Node(_,_,Some(right),_) => binarySearch(value,right)
      }
    }
  }

  def inorder:Seq[K]={
    val nodes = new ArrayBuffer[K]()
    val stack = new mutable.Stack[Node[K]]()
    if (size!=0){
      var cur = root
      while(!stack.isEmpty || cur!=None){
        cur match{
         case Some(node) => {
           stack.push(node)
           cur = node.left
         }
         case None=> {
           val tmp = stack.pop()
           nodes += tmp.value
           cur = tmp.right
         }
       }
      }
    }
    nodes
  }

  override def toString:String={
    inorder.mkString(" :: ")
  }

  override def height:Int= depth(root)

  def depth(node:Option[Node[K]]):Int = {
    node match {
      case None => 0
      case Some(n) => 1+ scala.math.max(depth(n.left),depth(n.right))
    }

  }

  def size = count
}

object App{
  def main(args:Array[String]){
    val generator = new Random()
    val tree = new Tree[Int]()
    (1 until 100).foreach(_=>tree.add(generator.nextInt(100)))
    println(tree.size)
    println(tree.height)
    println(tree)
    println(tree.remove(10))
    println(tree.remove(5))
    println(tree.size)
    println(tree)
    println(tree.remove(1))
    println(tree.size)
    println(tree)
    println(tree.remove(6))
    println(tree.size)
    println(tree)
    println(tree.remove(7))
    println(tree.size)
    println(tree)
  }
}