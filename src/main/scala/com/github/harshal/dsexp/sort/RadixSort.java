package com.github.harshal.dsexp.sort;

/**
 * @author harshal
 * @date: 7/13/13
 */
public class RadixSort {
    public static void sort(int[] a, int R){
        int N = a.length;
        int count[] = new int[R+1];
        int[] aux = new int[N];

        //count frequencies
        for(int i = 0; i<N; i++)
            count[a[i]+1]++;
        //compute cumulants
        for(int r = 0; r < R; r++)
            count[r+1]+=count[r];
        //move items
        for(int i = 0; i<N; i++)
            aux[count[a[i]]++] = a[i];

        for(int i=0; i<N; i++)
            a[i] = aux[i];
    }

    public static void main(String[] args){
        int[] a = {1,4,2,5,4,4,6,7,8,3,9,3,2};
        RadixSort.sort(a, 10);
        for(int i =0; i<a.length; i++)
            System.out.print(a[i]+" ");
    }
}
