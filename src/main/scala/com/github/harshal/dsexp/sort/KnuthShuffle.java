package com.github.harshal.dsexp.sort;

import java.util.Random;

/**
 * @author harshal
 * @date: 7/5/13
 */
public class KnuthShuffle {
    public static void apply(Object[] a){
        Random r = new Random();
        int N = a.length;
        for(int i=0; i<N; i++){
            int j = r.nextInt(i+1);
            Utils.swap(a,i,j);
        }
    }
    public static void main(String[] args){
        Integer[] a = {1,2,3,4,5,6,7};
        KnuthShuffle.apply(a);
        for(int i = 0; i<a.length; i++){
            System.out.print(a[i]+" ");
        }
    }
}
