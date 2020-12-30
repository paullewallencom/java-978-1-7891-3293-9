package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
         //  pollingFutures();
        // OK! that example we just went through illustrated some of the ways in which the new
        // Java libraries for threading are cleaner than the old ones.
        // But that code did have one pretty inefficient bit.
        // Its the while loop at the end where we wait on each Future object till its done

        // That's pretty inefficient because we wait on the items in the order we submitted them -
        // so let's say Callable #100 finished first, we still would get to its result only once
        // all 99 Callable objects before it had finished. That is not very efficient. Let's say,
        // for instance that these callables were all alternative ways of doing something -
        // we might want to finish right as soon as at least one of the Future objects is done.

        // the new Java libraries hava a way to do exactly this - via another class called
        // CompletionService. This takes in an executor, and then very handily will
        // return a queue of Future objects in the order in which they finish

        // Create an executor as before
        ExecutorService executor = Executors.newFixedThreadPool(10);
        // Now wrap this in a CompletionService object

        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executor);
        // note how the completion service is templated also

        for (int i=0;i<100;i++){
            Callable<Integer> myCallable = new MyCallable();
            completionService.submit(myCallable);
            // notice how we passed the callable objet to the completionService and not directly
            // to the executor
        }

        // now let's wait on the results this time far more efficiently, ie in the order in which
        // the future objects finish

        for (int i=0; i<100;i++){
            try{
                // what just happened? The completionService.take() method will always return the most
                // recently finished future (this is a queue btw). The .get then returns the integer value
                Integer oneResult = completionService.take().get();
                System.out.println(oneResult + " was obtained from the callable that just finished executing");
            } catch (InterruptedException e){
                e.printStackTrace();
            } catch (ExecutionException e){
                e.printStackTrace();
                // had we chosen we could have bailed out of executing the remaining future objects here
            }
        }

        // now as before shut down the executor object
        executor.shutdown();




    }

    private static void pollingFutures() {
        // OK - now that we have implemented our Callable object, we can actually make the thread calls
        // this is where the real differences between the old and new ways of doing Threading in Java
        // arise

        // In the new way we never create or deal with Thread objects. Instead, we will deal with
        // 'executors' which are Java wrappers that take in a callable, then set up the thread and do
        // all that is needed.

        // There is more, the new Java library will for free give a pool of threads, which can all be used
        // without the main thread needing to worry about overloading the system by creating too many threads
        // keeping track of them etc. In the old days, any serious Java threading program would need to implement
        // its own ThreadBank - now this is built-in

        // Create a single executor from a threadbank of 10 threads
        ExecutorService executor= Executors.newFixedThreadPool(10);

        // Now when the executor runs a callable, the result is stored in the form of a Futures
        // onject. The Future<> object is a template, which stands for the result of the other thread.
        // This object is returned as soon as the call to the executor is made, but it probably will
        // not actually have a result ready right away
        // How to wait and get the actual result? by using the future.isDone() and the future.get() methods

        // Create a list of Future<Integer> objects, since we will be creating a bunch of these
        List<Future<Integer>> listOfFutures = new ArrayList<>();

        // Now create a bunch of Callable objects and submit them for execution
        for (int i=0;i<100;i++){
            //instantiate the Callable
            MyCallable oneCallable = new MyCallable();
            Future<Integer> oneFuture = executor.submit(oneCallable);
            listOfFutures.add(oneFuture);
            // test if the result is ready - at this point, the answer is probably not
            System.out.println("At this point, is the future object ready with the result?" + oneFuture.isDone());
                    }

        // Ok, now let's wait on the results to be done
        for (Future<Integer> oneFuture:listOfFutures) {
            Integer oneCallableResult = null;

            try{
                oneCallableResult = oneFuture.get();
                // this is the crucial call to retrieve the result of the callable.
                // Note that this is a blocking call, ie, if the result is not yet available,
                // this call will just sit there and wait
                System.out.println("Result of the callable="+oneCallableResult);
            } catch(InterruptedException e ){
                e.printStackTrace();
            } catch(ExecutionException e){
                e.printStackTrace();
            }
        }

        // At this point we are done with the execution, so let's shut down our executor
        executor.shutdown();
        // this is good practice as it frees up the resources held by the executor (in this case, 10 threads)
    }
}

































