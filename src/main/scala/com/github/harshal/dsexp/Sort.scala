package com.github.harshal.dsexp

import collection.mutable.ArrayBuffer
import util.Random

/**
 * @author harshal
 * @date: 2/28/13
 */
object Sort {
  def mergeSort(array:ArrayBuffer[Int]):ArrayBuffer[Int]={
    if (array.length<=1) return array
    val m = array.length/2
    val arr1 = mergeSort(array.take(m))
    val arr2 = mergeSort(array.drop(m))
    merge(arr1,arr2)
  }

  def merge(arr1:ArrayBuffer[Int],arr2:ArrayBuffer[Int]):ArrayBuffer[Int]={
    val array = new ArrayBuffer[Int]()
    while(!arr1.isEmpty && !arr2.isEmpty){
      if (arr1.head<=arr2.head) array+=arr1.remove(0)
      else array+=arr2.remove(0)
    }
    if (!arr1.isEmpty) array++=arr1
    if (!arr2.isEmpty) array++=arr2
    array
  }

  def quick_sort(array:ArrayBuffer[Int],gen:Random):ArrayBuffer[Int]={
    if (array.length>1) {
      val index = gen.nextInt(array.length-1)
      val pivot = array(index)
      val less = array.filter(_<pivot)
      val more = array.filter(_>pivot)
      quick_sort(less,gen)+pivot++quick_sort(more,gen)
    }
    else array
  }



  def main(args:Array[String]){
    val array = new ArrayBuffer[Int]()
    val generator = new Random()
    (1 until 20).foreach(_=>array+=generator.nextInt(100))
    println(quick_sort(array,generator))
  }
}
