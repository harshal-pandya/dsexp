package com.github.harshal.dsexp

import sun.text.normalizer.Trie
import scala.Array

/**
 * @author harshal
 * @date: 2/27/13
 */
object WordCompletion {
  def remove(s:Array[Char])={
    var p = 0
    var k = 0
    (1 until s.length).takeWhile(i=>s(i)!='#').foreach(
      i=>{
        if (s(i) == 'a' || s(i) == 'i')
          p+=1;
        else
          s(i - p) = s(i)
        k=i
      }
    )
    s(k - p) = '#'
    s
  }

  def main(args:Array[String]){
    remove("bacac".toCharArray).foreach(print(_))
  }
}
