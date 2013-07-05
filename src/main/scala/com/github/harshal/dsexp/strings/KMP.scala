package com.github.harshal.dsexp.strings

/**
 * @author harshal
 * @date: 6/27/13
 */
object KMP {
  val R = 26

  private def generateDFA(pat:String): Array[Array[Int]]  = {
    val N = pat.length
    val dfa = Array.fill(R,N)(0)
    var x = 0
    dfa(pat.charAt(0).toInt%26)(0) = 1
    for (i <- 1 until N) {
      val c = pat.charAt(i).toInt%26
      for (r <- 0 until R) {
        dfa(r)(i) = dfa(r)(x)
      }
      dfa(c)(i) = i+1
      x = dfa(c)(x)
    }
    dfa
  }

  def indexOf(pat:String,s:String):Int = {
    var j = 0
    val dfa = generateDFA(pat)
    for (i <- 0 until s.length){
      val c = s.charAt(i)%26
      j = dfa(c)(j)
      if (j == pat.length) return i-j+1
    }
    -1
  }

}

object Test extends App{
  val s = "aabacaababacaa"
  val p = "ababac"
  val i = KMP.indexOf(p,s)
  println(s.substring(i))
}