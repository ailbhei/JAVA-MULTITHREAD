/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edm.threadjava;

import java.util.LinkedList;

/**
 *
 * @author user
 */
public class javaMultiThread {
    
    public static void main(String[] args) 
        throws InterruptedException 
    { 
        final Facemask facemask = new Facemask(); 
  
        Thread t1 = new Thread(new Runnable() { 
            @Override
            public void run() 
            { 
                try { 
                    facemask.produce(); 
                } 
                catch (InterruptedException e) { 
                    e.printStackTrace(); 
                } 
            } 
        }); 
  
        Thread t2 = new Thread(new Runnable() { 
            @Override
            public void run() 
            { 
                try { 
                    facemask.consume(); 
                } 
                catch (InterruptedException e) { 
                    e.printStackTrace(); 
                } 
            } 
        }); 
  
        t1.start(); 
        t2.start(); 
  
        t1.join(); 
        t2.join(); 
    } 
    public static class Facemask { 
  
        LinkedList<Integer> list = new LinkedList<>(); 
        int capacity = 2; 
  
        public void produce() throws InterruptedException 
        { 
            int fm = 0; 
            while (true) { 
                synchronized (this) 
                { 
                    while (list.size() == capacity) 
                        wait(); 
  
                    System.out.println("Suppliers producing "+ fm + " more."); 
  
                    list.add(fm++); 
  
                    notify(); 
                    
                    Thread.sleep(100); 
                } 
            } 
        } 
  
        // Function called by consumer thread 
        public void consume() throws InterruptedException 
        { 
            while (true) { 
                synchronized (this) 
                { 
                    while (list.size() == 0) 
                        wait(); 
   
                    int fmm = list.removeFirst(); 
  
                    System.out.println("Nation to nation consuming "+ fmm + " more facemask from day to day use."); 
                    notify(); 
  
                    Thread.sleep(200); 
                } 
            } 
        } 
    } 
    
}
