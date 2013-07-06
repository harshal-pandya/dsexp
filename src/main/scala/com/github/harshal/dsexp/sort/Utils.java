package com.github.harshal.dsexp.sort;

/**
 * @author harshal
 * @date: 7/5/13
 */
public class Utils {
    public static void swap(Object[] a, int i, int j){
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    public static boolean lessThan(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }
    public static boolean isSorted(Comparable[] a, int i, int j){
        for(; i<=j; i++) {
            if(lessThan(a[i],a[i-1])) {
                return false;
            }
        }
        return true;
    }
}
