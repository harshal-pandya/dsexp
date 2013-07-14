package com.github.harshal.dsexp.datastructures;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author harshal
 * @date: 7/6/13
 */
public class TreeMap<Key extends Comparable, Value> {
    private Node root;
    public class Node{
        public Key k;
        private Value v;
        private int count = 1;
        public Node left, right;
        public Node(Key k, Value v){
            this.k = k;
            this.v = v;
        }
    }

    public void put(Key k, Value v)
    {   root = put(root, k, v);    }
    private Node put(Node n, Key k, Value v){
        if(n == null) return new Node(k,v);
        int cmp = k.compareTo(n.k);
        if(cmp < 0)         n.left =  put(n.left, k, v);
        else if(cmp > 0)    n.right =  put(n.right, k, v);
        else                n.v = v;
        n.count = 1 + size(n.left) + size(n.right);
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
        Node x = min(root);
        if(x==null) return null;
        return min(root).k;
    }
    private Node min(Node n){
        if(root == null) return null;
        while(n.left != null)   n = n.left;
        return n;
    }

    public Key max(){
        Node x = max(root);
        if(x==null) return null;
        else return x.k;
    }
    private Node max(Node n){
        if(n==null) return null;
        while(n.right != null)   n = n.right;
        return n;
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
        Node n = ceil(root, k);
        if(n != null) return n.k;
        return null;
    }
    private Node ceil(Node n, Key k){
        if(n == null) return null;
        int cmp = k.compareTo(n.k);
        if( cmp == 0) return n;
        else if(cmp > 0) return ceil(n.right, k);
        else {
            Node r = ceil(n.left, k);
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

    public Key select(int k){
        k=k-1;
        if(k<0 || k>size()) return null;
        Node n = select(k, root);
        return n.k;
    }
    private Node select(int k, Node n){
        if(n == null) return null;
        int t = size(n.left);
        if(t > k)       return select(k, n.left);
        else if(t < k)  return select(k-t-1, n.right);
        else            return n;
    }
    public Key median(){
        System.out.println(size()/2);
        return select(size()/2);
    }

    public int depth(){
        if(root == null) return 0;
        else return depth(root);
    }
    private int depth(Node n){
        if(n==null) return 0;
        return 1 + Math.max(depth(n.left),depth(n.right));
    }

    public Node convertToLikedList(){
        Node head = convertToLikedList(root);
        return head;
    }

    private Node convertToLikedList(Node n){
        if(n == null) return null;
        Node t1 = convertToLikedList(n.left);
        Node t2 = convertToLikedList(n.right);
        n.left = n;
        n.right = n;
        Node x = append(t1, n);
        x = append(x, t2);
        return x;
    }

    private Node append(Node a, Node b){
        if(a == null) return b;
        if(b == null) return a;

        Node aLast = a.left;
        Node bLast = b.left;

        aLast.right = b;
        b.left = aLast;

        a.left = bLast;
        bLast.right = a;

        return a;
    }

    public static void main(String[] args){
//        String[] a = {"s","e","x","a","r","c","h","m","w","z"};
        String[] a = {"b","a","c"};
        TreeMap<String,Integer> tm = new TreeMap<String, Integer>();
        for(int i=0; i<a.length;i++){
            tm.put(a[i],1);
        }
        for(String e : tm.keys()){
            System.out.print(e+" ");
        }
        System.out.println(tm.min());
        System.out.println(tm.max());
        TreeMap.Node ans = tm.convertToLikedList();
        System.out.println(tm.median());
    }
}
