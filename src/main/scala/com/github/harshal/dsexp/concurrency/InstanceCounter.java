package com.github.harshal.dsexp.concurrency;

/**
 * @author harshal
 * @date: 7/16/13
 */
public class InstanceCounter {
    static int instances = 0;

    public InstanceCounter(){
        instances++;
    }
    public static void main(String[] args){
        new InstanceCounter();
        new InstanceCounter();
        new InstanceCounter();
        new InstanceCounter();
        new InstanceCounter();
        new InstanceCounter();
        System.out.println(InstanceCounter.instances);
        new InstanceCounter();
        new InstanceCounter();
        new InstanceCounter();
        new InstanceCounter();
        new InstanceCounter();
        new InstanceCounter();
        System.out.println(InstanceCounter.instances);

    }
}
