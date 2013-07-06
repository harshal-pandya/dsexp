package com.github.harshal.dsexp.sort;

/**
 * @author harshal
 * @date: 7/5/13
 */
public class InsertionSort {
    public static void sort(Comparable[] array){
        for(int i = 0; i<array.length; i++){
            for(int j = i; j>0 && Utils.lessThan(array[j],array[j-1]); j--){
                Utils.swap(array,j-1,j);
            }
        }
    }
}
