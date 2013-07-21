package com.github.harshal.dsexp.utils;

/**
 * @author harshal
 * @date: 7/20/13
 */
public class Math {
    public static int factorial(int n){
        if(n==0) return 1;
        if(n==1) return 1;
        return n*factorial(n-1);
    }
}
