package com.company;

public class Main {

    public static void main(String[] args) {
	// step 1: create a class that implements the interface Runnable, which does the
	// stuff we want done on a separate thread
        // We just created a class that did this

        System.out.println("Starting the main thread");


        // step 2: Instantiate the class and pass each instance into an object of the Java
        // built-in class Thread
        MyRunnable myRunnable1=new MyRunnable();
        MyRunnable myRunnable2= new MyRunnable();

        Thread t1 = new Thread(myRunnable1);
        Thread t2 = new Thread(myRunnable2);

        // That worked! Now let's try running the code by modifying it in 1 little way -
        // we will pass the same runnable object to both threads
        // In theory this should cause the program to finish executing twice as fast
        // because now the same variable (i.e. the same member variabale in the same object) is
        // now being incremented on 2 threads
        // BTW we will also modify MyRunnable class so that it prints the value of the
        // varaiable in each run of the loop

        // Cool! Now let's try the same experiment but we will shorten the period for which
        // each thread sleeps and also increment how long the program must run to finish

        // Bingo! Did you see what just happened? The same value was printed twice ( for instance 890
        // was printed twice and so was 894

        // What just happened? Something very strange - our first little synchronization bug!

        // thread t1 and t2 are both operating on the same Runnable object. Each increments the value
        // of the same member variable of the same object. Now what happened was - each of these 2 threads
        // interrupted each other during their execution, and so t1 incremented the value
        // of our counter 890, but before it could print, thread t2 came in interrupted it
        // ie. the increment was a lost write
        // what if we had been incrementing the $ balance in somebody's bank account?
        // This lost incremembt would have been a serious issue. this is why multi-threaded code is so
        // complicated


        // this is where the 'synchronized' keyword comes in. We will mark the run method as synchronized
        // that will fix the problem

        // Cool! Seems to have fixed the synchronization bug as we did not seem to come across
        // any value that was printed twice. (Aside: thread related bugs are virtually impossible
        // to reproduce because each time the code runs, the bug will appear in a slightly different
        // place. So these bugs are far easier to prevent than to fix


        // Now - what if the member variable was static, rather than being an object member variable?
        // Would 'synchronized' fix the lost writes problem?

        // So - in this particular run of the code, it seemed like the problem did not occur (at least
        // from our eyeballing the output) but in fact the synchronized keyword will *not* fix
        // lost writes on a static member variable
        // That is because the member variable is shared across objects of the class,
        // while our use of the 'synchronized' keyword on the run method merely prevents the same object
        // from being entered twice by different threads
        // In fact, if we go back to 2 MyRunnable objects,
        // the lost writes problem is much more likely to occur

        // And there it is! 992 and 993 are printed twice.
        // Remember 2 important lessons though:
        // 1. synchronized will only work on restricting access to the same object method ( not across
        // objects)
        // 2. In general, threading related bugs appear mysteriously once in a while (not always,
        // and not predictably) this is why writing and debugging code with multiple threads is so
        // complicated even for relatively simple tasks



        // step 3: Kick off the new thread by calling the start method on each object of the thread
        // class

        t1.start();
        t2.start();


        // step 4: Wait until the thread ( or threads) that we kicked off finish executing


        try {
            t1.join();
            t2.join();
            // The join method of a thread will wait until that thread has finished
            // executing. By placing both these 2 join calls ( on the 2 threads) inside a
            // while loop  we are effectively forcing the main thread to wait until the 2 threads t1
            // and t2 both finish executing
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
        // just  like Thread.sleep (btw that is a static member, which is why we invoke it using the class
        // name, .join() ( which is not a static method and so must be invoked on an object instance of the
        // class) also throws an InterruptedException/ That's fine - we are
        // ok with these threads being interrupted before they are finished, so we will do
        // nothing in the catch block


    }
}
