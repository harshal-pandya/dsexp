package com.github.harshal.dsexp.sort;

import java.util.Random;

/**
 * @author harshal
 * @date: 7/5/13
 */
public class Test {
    public static void main(String[] args){
        Random r = new Random();
        Integer[] a = new Integer[10];
        for(int i = 0; i<a.length; i++){
            a[i]=r.nextInt(100);
        }
        Integer[] b = a.clone();
        for(int i1 = 0; i1<a.length; i1++){
            System.out.print(a[i1]+" ");
        }
        System.out.println();
        long i = System.currentTimeMillis();
//        InsertionSort.sort(a);
        MergeSort.sort(a);
        long j = System.currentTimeMillis()-i;
//        System.out.println(j);
        i = System.currentTimeMillis();
        QuickSort.sort(b);
        j = System.currentTimeMillis()-i;
        System.out.println(j);

        for(int i1 = 0; i1<a.length; i1++){
            System.out.print(b[i1]+" ");
        }
    }
}
