package com.company;

/**
 * Created by swethakolalapudi on 10/11/15.
 */
public class MyRunnable implements Runnable{
    // Runnable is a standard Java interface with a single method, called 'run'
    // We can implement whatever we want in this method

    // we will create a simple class that maintains a member variable and adds to
    // it in the run method

    private static int m_myCount=0;

    public static  int getM_myCount() {
        return m_myCount;
    }

    public static void setM_myCount(int m_myCount) {
        m_myCount = m_myCount;
    }

    public synchronized void run(){
        while(m_myCount<=1000){
            m_myCount++;
            System.out.println(m_myCount);

            try {
                Thread.sleep(10);
                // This line above, Thread.sleep, calls the static sleep method of the Thread class
                // The parameter is the number of milliseconds for which this thread should do
                // nothing. Thread.sleep should always be placed in a try/catch loop, because it is
                // always possible that a sleeping thread (ie a thread that is doing nothing) will be
                // interrupted (awakened) by the Java Virtual Machine
            } catch (InterruptedException e) {
                // e.printStackTrace();
                // WE don't really care if we are awakened, so we will not print an error for this exception

            }
        }
    }
}










































