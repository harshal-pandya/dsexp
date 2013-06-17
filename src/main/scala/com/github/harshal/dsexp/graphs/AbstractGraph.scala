package com.github.harshal.dsexp.graphs

import java.io.InputStream
import collection._
import annotation.tailrec

/**
 * @author harshal
 * @date: 6/16/13
 */
abstract class AbstractGraph(val Vertices:Int) {
//  def this(In:InputStream) =
  def addEdge(v:Int,w:Int)
  def adj(v:Int):Iterable[Int]
  def E:Int
  def degree(v:Int) = adj(v).size
  def maxDegree = { (0 until Vertices).map(degree(_)).max }
  def avgDegree = (2.0*Vertices)/E
  override def toString = {
    val ls = for (v <- 0 until Vertices) yield {
      adj(v).map( w => "( "+ v +"-"+ w +" )" )
    }
    ls.flatten.mkString("\n")
  }
}

class Graph(val V:Int) extends AbstractGraph(V) {
  private val adjacencyList = Array.fill(V)(mutable.LinkedList[Int]())
  override def addEdge(v:Int, w:Int) {
    adjacencyList(v) = adjacencyList(v) :+ w
    adjacencyList(w) = adjacencyList(w) :+ v
  }
  override def adj(v:Int) = adjacencyList(v)
  override def E = adjacencyList.foldLeft(0)((a,b)=>a+b.size)/2
}

abstract class Paths[G<:AbstractGraph](g:G, v:Int) {
  def hasPathTo(w:Int):Boolean
  def pathTo(w:Int):Option[Iterable[Int]]
}

class DepthFirstSearchPaths[G<:AbstractGraph](g:G, s:Int) extends Paths(g,s){
  private val marked = Array.fill(g.Vertices)(false)
  private val edgeTo = Array.fill(g.Vertices)(-1)

  dfs(g,s)

  override def hasPathTo(w:Int) = marked(w)

  override def pathTo(w:Int) = {
    if (!hasPathTo(w)) None
    val path = mutable.Stack[Int]()
    var v = w
    while(v!=s){
      path.push(v)
      v = edgeTo(v)
    }
    path.push(s)
    Some(path)
  }

  private def dfs(g:G, v:Int){
    marked(v) = true
    for (w <- g.adj(v)) if (!marked(w)) { dfs(g,w); edgeTo(w)=v }
  }
}

