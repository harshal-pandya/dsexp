package com.github.harshal.dsexp.sort;

/**
 * @author harshal
 * @date: 7/5/13
 */
public class QuickSort {
    public static void sort(Comparable[] a){
        KnuthShuffle.apply(a);
        sort(a,0,a.length-1);
    }
    private static void sort(Comparable[] a, int lo, int hi){
        if(hi <= lo) return;
        int j = partition(a,lo,hi);
        sort(a,lo,j-1);
        sort(a,j+1,hi);
    }
    private static int partition(Comparable[] a, int lo, int hi){
        int i = lo; int j = hi+1;
        System.out.println(a[lo]);
        while(true){
            while(Utils.lessThan(a[++i],a[lo]))
                if(i == hi) break;
            while(Utils.lessThan(a[lo],a[--j]))
                if(j==lo) break;
            if(i>=j) break;
            Utils.swap(a,i,j);
            for(int k=lo; k<hi;k++){
                System.out.print(a[k]+" ");
            }
            System.out.println();
        }
        Utils.swap(a,lo,j);
        for(int k=lo; k<hi;k++){
            System.out.print(a[k]+" ");
        }
        System.out.println();
        return j;
    }
}
