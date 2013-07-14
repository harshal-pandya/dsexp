package com.github.harshal.dsexp.misc;

import com.github.harshal.dsexp.datastructures.TreeMap;

/**
 * @author harshal
 * @date: 7/7/13
 */
public class Questions {
    /** Given a circularly sorted array, describe the fastest way to locate the largest element. **/
    public static int largestElem(int[] array){
        int N = array.length;
        int i=0;
        int mid = N/2;
        int j=N;
        while(i<j){
            if(array[i]<array[mid]){
                i=mid;
                mid=i+(j-i)/2;
            }
            else{
                j=mid;
                mid=i+(j-i)/2;
            }
        }
        return array[i];
    }

    /** Given a binary search tree and an integer k,
     *  describe the most efficient way to locate two nodes of the tree that sum to k.
     */

     public static void findNodesForSum(int s){
         Integer[] a = {1,4,8,6,3,10};
         TreeMap<Integer,Integer> tm = new TreeMap<Integer, Integer>();
         for(int i=0; i<a.length;i++){
             tm.put(a[i],null);
         }
         TreeMap.Node head = tm.convertToLikedList();
         TreeMap.Node end = head.left;
         while(head!=end){
             int ans = (Integer)head.k+(Integer)end.k;
             if(ans==s){
                 System.out.println(head.k+" : "+end.k);
                 break;
             }
             else if(ans>s){
                 end = end.left;
             }
             else {
                 head = head.right;
             }
         }
     }

    /**
     * Given an arbitrarily connected graph, explain how to ascertain the reachability of a given node.
     */

    /**
     * If you have one billion numbers and one hundred computers,
     * what is the best way to locate the median of the numbers?
     */

    /**
     * Describe an API for a cache, with all of the associated function signatures.
     * Provide pseudocode for the simple one.
     */

    /**
     * Implement a special type of integer iterator. It will be a wrapper over a
     * regular integer iterator, except that this iterator will only iterate over
     * negative numbers. Show how you'd write the next() and hasNext() functions
     * to work with the next negative integer instead of just the next integer.
     */

    /**
     * Implement a special type of integer iterator. It will be a wrapper over a
     * regular integer iterator, except that this iterator will only iterate over
     * negative numbers. Show how you'd write the next() and hasNext() functions
     * to work with the next negative integer instead of just the next integer.
     */

    /**
     * You are given 1001 numbers in an array. There are 1000 unique numbers and
     * 1 duplicate. How do you locate the duplicate as quickly as possible?
     */

    /**
     * Say you are implementing exponentiation for a certain processor.
     * How would you minimize the number of multiplications required for this operation?
     */
    public static double expBySquaring(int x, int n){
        if(n==0) return 1;
        else if(n == 1) return x;
        else if(n%2 == 0) return expBySquaring(x<<1,n/2);
        else return x*expBySquaring(x<<1,(n-1)/2);
    }

    public static void main(String[] args){
        long t = System.currentTimeMillis();
        Questions.expBySquaring(3,Integer.MAX_VALUE);
        System.out.println(System.currentTimeMillis()-t);
        t=System.currentTimeMillis();
        int x = 3;
        for(int i = 0; i<Integer.MAX_VALUE; i++){
            x*=x;
        }
        System.out.println(System.currentTimeMillis()-t);
        findNodesForSum(35);
    }
}
