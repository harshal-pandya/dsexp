package com.github.harshal.dsexp.sort;

/**
 * @author harshal
 * @date: 7/5/13
 */
public class ShellSort {
    public static void sort(Comparable[] array){
        int N = array.length;
        int h = 1;
        while(h<N/3) h = 3*h+1;
        while (h>=1){
            for(int i = h; i<array.length; i++){
                for(int j = i; j>=h && Utils.lessThan(array[j],array[j-h]); j-=h){
                        Utils.swap(array,j-h,j);
                }
            }
            h=h/3;
        }
    }
}
