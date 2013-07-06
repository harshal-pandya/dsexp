package com.github.harshal.dsexp.sort;

/**
 * @author harshal
 * @date: 7/5/13
 */
public class MergeSort {
    public static void sort(Comparable[] a){
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length-1);
    }
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
        if(hi <= lo) return;
        int mid = lo + (hi-lo)/2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
        assert(Utils.isSorted(a,lo,mid));
        assert(Utils.isSorted(a,mid,hi));
        for(int i = lo; i<=hi; i++) aux[i] = a[i];
        int i = lo; int j = mid+1;
        for(int k = lo; k<=hi; k++){
            if (i > mid)                            a[k] = aux[j++];
            else if (j > hi)                        a[k] = aux[i++];
            else if (Utils.lessThan(aux[j],aux[i])) a[k] = aux[j++];
            else                                    a[k] = aux[i++];
        }
        assert(Utils.isSorted(a,lo,hi));
    }
}
