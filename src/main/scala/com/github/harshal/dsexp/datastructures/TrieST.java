package com.github.harshal.dsexp.datastructures;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author harshal
 * @date: 7/14/13
 */
public class TrieST<Value>
{
    private static final int R = 256;
    private Node root = new Node();

    private static class Node
    {
        private Object value;
        private Node[] next = new Node[R];
    }

    public void put(String key, Value val)
    {   root = put(root, key, val, 0);  }

    private Node put(Node n, String key, Value val, int d)
    {
        if(n == null) n = new Node();
        if(d == key.length()) { n.value = val; return n; }
        char c = key.charAt(d);
        n.next[c] = put(n.next[c], key, val, d+1);
        return n;
    }

    public boolean contains(String key)
    {   return get(key) != null;    }

    public Value get(String key)
    {
        Node n = get(root, key, 0);
        if (n == null) return null;
        else return (Value) n.value;
    }

    private Node get(Node n, String key, int d)
    {
        if(n == null) return null;
        if (d == key.length()) return n;
        int c = key.charAt(d);
        return get(n.next[c], key, d+1);
    }

    public void delete(String key)
    {   root = delete(root, key, 0);    }

    private Node delete(Node n, String key, int d){
        if(n == null) return null;
        if(d == key.length()) {
            n.value = null;
            int c = 0;
            while (c < R){
                if(n.next[c] != null) return n;
                c++;
            }
            return null;
        }
        int c = key.charAt(d);
        n.next[c] = delete(n.next[c], key, d+1);
        if(n.value != null ) return n;
        c = 0;
        while (c < R){
            if(n.next[c] != null) return n;
            c++;
        }
        return null;
    }

    public Iterable<String> keys(){
        Queue<String> queue = new LinkedList<String>();
        collect(root, "", queue);
        return queue;
    }

    public Iterable<String> keysWithPrefix(String prefix){
        Queue<String> queue = new LinkedList<String>();
        Node start = get(root, prefix, 0);
        collect(start, prefix, queue);
        return queue;
    }

    private void collect(Node x, String prefix, Queue<String> q){
        if(x == null) return;
        if(x.value != null) q.add(prefix);
        for(char c = 0; c<R; c++)
            collect(x, prefix+c, q);
    }

    public String longestPrefixOf(String query)
    {
        int length = search(root, query, 0, 0);
        return query.substring(0, length);
    }

    private int search(Node n, String query, int d, int length)
    {
        if(n==null) return length;
        if(n.value != null) length = d;
        if(d == query.length()) return length;
        char c = query.charAt(d);
        return search(n.next[c], query, d+1, length);
    }

}
