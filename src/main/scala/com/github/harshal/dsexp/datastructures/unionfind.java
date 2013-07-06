package com.github.harshal.dsexp.datastructures;

/**
 * @author harshal
 * @date: 7/5/13
 */
public class UnionFind {
    private int[] id;
    private int[] size;
    public UnionFind(int n){
        id = new int[n];
        for(int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }
    private  int root(int i){
        while (i != id[i]){
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
    public boolean connected(int p, int q){
        return root(p) == root(q);
    }
    public void union(int p, int q){
        int i = root(p);
        int j = root(q);
        if (size[i] < size[j])  { id[i] = j; size[j] += size[i]; }
        else                    { id[j] = i; size[i] += size[j]; }
        id[i] = j;
    }
}
