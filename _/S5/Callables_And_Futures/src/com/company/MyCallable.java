package com.company;

import java.util.concurrent.Callable;


public class MyCallable implements Callable<Integer> {

    // The Callable interface is the direct descendant of the Runnable interface.
    // Callable is the way to specify what action should be performed on another thread -
    // in this way it is exactly like Runnable, but Callable (which is part of new
    // concurrency libraries in Java) has 2 important advantages over Runnable

    // 1. In Callable a return value of the thread computation can be specified using a template
    // parameter. In Runnable, the .run() method does not return any value,
    // so the calling code has to rely on shared member variables to examine the result
    // As we saw previously shared memory leads to all kinds of complications around
    // memory errors etc
    // 2. In Callable, it is possible for the .call method to throw exceptions and for these exceptions
    // to be caught and handled by the main thread. This is a major advantage -
    // otherwise it is difficult for the main thread to catch exceptions from threading issues

    private static int m_myCount=0;

    public static int getM_myCount(){
        return m_myCount;
    }

    public static void setM_myCount(int m_myCount){
        MyCallable.m_myCount=m_myCount;
    }
    // we created a member variable and a getter and setter just like in the Runnable

    // Ok now we actually need to implement the main call function
    @Override
    public Integer call()  throws
            Exception{
        if(getM_myCount()<=1000){
            setM_myCount(getM_myCount()+1);
        }

        try{
            Thread.sleep(100);
        } catch (InterruptedException e){
            // Again we don't really care if our sleep is interrupted
        }
        return  getM_myCount();
        // Note that this function is very similar to the corresponding run function in MyRunnable class -
        // the main difference is that we return
        // an integer, rather than relying on the main thread to do the call to the getter
        // which has the usual risks of memory sharing with multiple threads.
        // But the main difference is not between Callable and Runnable, rather it is in the way
        // the threads are setup and executed.
    }

}
























