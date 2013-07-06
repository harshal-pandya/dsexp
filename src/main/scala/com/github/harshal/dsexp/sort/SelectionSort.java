package com.github.harshal.dsexp.sort;

/**
 * @author harshal
 * @date: 7/5/13
 */
public class SelectionSort {
    public static void sort(Comparable[] array){
        int min = -1;
        for(int i = 0; i<array.length; i++){
            min = i;
            for(int j = i; j<array.length; j++){
                if(Utils.lessThan(array[j],array[min])){
                    min = j;
                }
                Utils.swap(array,min,i);
            }
        }
    }
}

