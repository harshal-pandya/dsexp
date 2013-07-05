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

class BreadthFirstSearchPaths[G<:AbstractGraph](g:G, s:Int) extends Paths(g,s){
  private val marked = Array.fill(g.Vertices)(false)
  private val edgeTo = Array.fill(g.Vertices)(-1)

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

  private def bfs(g: G, v: Int){
    val queue = new mutable.Queue[Int]()
    marked(v) = true
    queue += v
    while(!queue.isEmpty){
      val current = queue.dequeue()
      for (n <- g.adj(current)) {
        if (!marked(n)) {
          marked(n) = true
          edgeTo(n) = current
          queue += n
        }
      }
    }
  }

}

abstract class Edge(private val v: Int, private val w: Int, private val weight: Double) extends Ordered[Edge] {

  def compare(that: Edge) = if (this.weight > that.weight) 1 else if (this.weight < that.weight) -1 else 0

}

class UndirectedEdge(private val v: Int, private val w: Int, private val weight: Double) extends Edge(v,w,weight) {

  def either = v

  def other(o: Int) = if (o == v) w else v
}

class EdgeWeightedGraph(val V:Int) {

  private val adjacencyList = Array.fill(V)(mutable.LinkedList[UndirectedEdge]())

  def addEdge(e: UndirectedEdge) {
    val v = e.either
    val w = e.other(v)
    adjacencyList(v) = adjacencyList(v) :+ e
    adjacencyList(w) = adjacencyList(w) :+ e
  }

  def adj(v: Int) = adjacencyList(v)

  def E = adjacencyList.foldLeft(0)((a,b)=>a+b.size)/2
}

class MST(g : EdgeWeightedGraph) {

  def edges: Iterable[Edge] = Nil

  def weight: Double = 0

}

class DirectedEdge(private val v: Int, private val w: Int, private val weight: Double) extends Edge(v, w, weight) {

  def from = v

  def to = w

}

class FlowEdge(private val v: Int, private val w: Int, private val capacity: Double) extends Edge(v, w, capacity) {

  private var _flow = 0

  def from = v

  def to = w

  def other(o: Int) = if (o == v) w else v

//  def flow:Double = _flow
//
//  def residualCapacity(vertex: Int): Double = {
//    if(vertex == v) flow
//    else if(vertex == w) capacity-flow
//    else throw new IllegalArgumentException
//  }
//
//  def addResidualFlow(vertex: Int, delta: Double){
//    if(vertex == v) _flow-=delta
//    else if(vertex == w) _flow+=delta
//    else throw new IllegalArgumentException
//  }

}