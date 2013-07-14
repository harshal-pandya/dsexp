package com.github.harshal.dsexp.datastructures;

import com.github.harshal.dsexp.sort.Utils;

/**
 * @author harshal
 * @date: 7/5/13
 */
public class PriorityQueue<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;
    public PriorityQueue(int capacity){
        pq = (Key[]) new Comparable[capacity+1];
    }
    public PriorityQueue(Key[] a){
        pq = (Key[]) new Comparable[a.length+1];
        for(int i=0; i<a.length; i++) insert(a[i]);
    }

    public void insert(Key k) {
        if(N==pq.length) {
            Key[] temp = (Key[]) new Comparable[pq.length*2];
            System.arraycopy(pq,0,temp,0,N);
            pq = temp;
        }
        pq[N++] = k;
        swim(N);

    }

    private void swim(int k){
        while(k>1 && Utils.lessThan(pq[k/2-1],pq[k-1])){
            Utils.swap(pq,k-1,k/2-1);
            k = k/2;
        }
    }

    private void sink(int k){
        while(2*k<=N){
            int j = 2*k;
            if(j<N && Utils.lessThan(pq[j-1],pq[j])) j++;
            if(Utils.lessThan(pq[j-1],pq[k-1])) break;
            Utils.swap(pq,k-1,j-1);
            k=j;
        }
    }

    public Key delMax() {
        Key max = pq[0];
        pq[0] = pq[--N];
        sink(1);
        return max;
    }

    public boolean isEmpty() {
        return N==0;
    }

    public Key max() {
        return pq[0];
    }

    public int size() {
        return N;
    }

    public static void main(String[] args){
        Integer[] a = {1,4,2,5,6,3,0};
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(a);
        while(!pq.isEmpty()){
            System.out.println(pq.delMax());
        }
    }

}
