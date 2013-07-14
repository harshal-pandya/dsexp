package com.github.harshal.dsexp.datastructures;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author harshal
 * @date: 7/6/13
 */
public class RBTreeMap<Key extends Comparable, Value> {
    private Node root;
    private final boolean RED = true;
    private final boolean BLACK = false;
    private class Node{
        private Key k;
        private Value v;
        private int count = 1;
        private Node left, right;
        private boolean color;
        public Node(Key k, Value v, boolean color){
            this.k = k;
            this.v = v;
            this.color = color;
        }
    }

    private boolean isRed(Node x){
        if(x==null) return false;
        else return x.color == RED;
    }

    private Node rotateLeft(Node h){
        assert(isRed(h.right));
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }
    private Node rotateRight(Node h){
        assert(isRed(h.right));
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }
    private void flipColors(Node h){
        assert(isRed(h.right) && isRed(h.left) && !isRed(h));
        h.right.color = BLACK;
        h.left.color = BLACK;
        h.color = RED;
    }

    public void put(Key k, Value v)
    {   root = put(root, k, v);    }
    private Node put(Node n, Key k, Value v){
        if(n == null) return new Node(k,v,RED);
        int cmp = k.compareTo(n.k);
        if(cmp < 0)         n.left =  put(n.left, k, v);
        else if(cmp > 0)    n.right =  put(n.right, k, v);
        else                n.v = v;
        n.count = 1 + size(n.left) + size(n.right);

        if(!isRed(n.left) && isRed(n.right)) n = rotateLeft(n);
        if(isRed(n.left) && !isRed(n.right)) n = rotateRight(n);
        if(isRed(n.left) && isRed(n.right)) flipColors(n);

        return n;
    }

    public Value get(Key k){
        Node x = root;
        while(x != null){
            int cmp = k.compareTo(x.k);
            if( cmp == 0 )      return x.v;
            else if( cmp < 0 )  x = x.left;
            else                x = x.right;
        }
        return null;
    }

    public void delete(Key k){
        root = delete(k,root);
    }
    private Node delete(Key k, Node n){
        if(n == null) return null;
        int cmp = k.compareTo(n.k);
        if(cmp < 0) n.left = delete(k,n.left);
        else if(cmp>0) n.right = delete(k,n.right);
        else{
            if(n.right == null)   return n.left;
            if(n.left == null)   return n.right;
            Node t = n;
            n = min(t.right);
            n.right = deleteMin(t.right);
            n.left = t.left;
        }
        n.count = 1 + size(n.left) + size(n.right);
        return n;
    }

    public void deleteMin(){
        root = deleteMin(root);
    }
    private Node deleteMin(Node n){
        if(n.left == null) return n.right;
        n.left = deleteMin(n.left);
        n.count = 1 + size(n.left) + size(n.right);
        return n;
    }

    public Iterable<Key> keys(){
        Queue<Key> q = new LinkedList<Key>();
        inorder(root,q);
        return q;
    }
    private void inorder(Node x, Queue<Key> q){
        if(x==null) return;
        inorder(x.left,q);
        q.add(x.k);
        inorder(x.right,q);
    }

    public int size(){
        return size(root);
    }
    private int size(Node n) {
        if(n==null) return 0;
        return n.count;
    }

    public boolean isEmpty(){
        return root==null;
    }

    public Key min(){
        if(root == null) return null;
        return min(root).k;
    }
    private Node min(Node n){
        while(n.left != null)   n = n.left;
        return n;
    }

    public Key max(){
        Node x = root;
        while(x.right != null)   x = x.right;
        return x.k;
    }

    public Key floor(Key k){
        Node n = floor(root,k);
        if(n != null) return n.k;
        return null;
    }
    private Node floor(Node n, Key k){
        if(n == null) return null;
        int cmp = k.compareTo(n.k);
        if( cmp == 0) return n;
        else if(cmp < 0) return floor(n.left,k);
        else {
            Node r = floor(n.right,k);
            if(r == null) return n;
            else return r;
        }
    }

    public Key ceil(Key k){
        Node n = ceil(root,k);
        if(n != null) return n.k;
        return null;
    }
    private Node ceil(Node n, Key k){
        if(n == null) return null;
        int cmp = k.compareTo(n.k);
        if( cmp == 0) return n;
        else if(cmp > 0) return ceil(n.right,k);
        else {
            Node r = ceil(n.left,k);
            if(r == null) return n;
            else return r;
        }
    }

    public int rank(Key k){
        return rank(k,root);
    }
    private int rank(Key k, Node n){
        if(n==null) return 0;
        int cmp = k.compareTo(n.k);
        if( cmp < 0) return rank(k, n.left);
        else if(cmp > 0) return 1 + size(n.left) + rank(k, n.right);
        else return size(n.left);
    }

    public int depth(){
        if(root == null) return 0;
        else return depth(root);
    }
    private int depth(Node n){
        if(n==null) return 0;
        return 1 + Math.max(depth(n.left),depth(n.right));
    }
    public static void main(String[] args){
        RBTreeMap<Integer,Integer> rb = new RBTreeMap<Integer, Integer>();
        for(int i=0; i<=10000000; i++){
            rb.put(i,1);
        }
        long s = System.currentTimeMillis();
        rb.get(10000000);
        long e = System.currentTimeMillis()-s;
        System.out.println(e);
    }
}
